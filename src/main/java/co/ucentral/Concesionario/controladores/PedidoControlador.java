package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Pedidos;
import co.ucentral.Concesionario.persistencia.entidades.Pago;
import co.ucentral.Concesionario.persistencia.entidades.Vehiculo;
import co.ucentral.Concesionario.servicios.PagoServicio;
import co.ucentral.Concesionario.servicios.PedidoServicio;
import co.ucentral.Concesionario.servicios.VehiculoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/pedido")
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;
    private final VehiculoServicio vehiculoServicio;
    private final PagoServicio pagoServicio; // Servicio para gestionar pagos

    // Mostrar todos los pedidos
    @GetMapping("/listar")
    public String listarPedidos(Model model) {
        List<Pedidos> pedidos = pedidoServicio.obtenerTodos();
        model.addAttribute("pedidos", pedidos);
        return "listarPedidos"; // Vista donde se muestran los pedidos
    }

    // Mostrar formulario de creación de pedido
    @GetMapping("/crear")
    public String crearPedido(Model model) {
        model.addAttribute("vehiculos", vehiculoServicio.obtenerTodos()); // Mostrar vehículos disponibles
        model.addAttribute("pedido", new Pedidos());
        return "crearPedido"; // Vista con formulario de creación de pedido
    }

    @PostMapping("/crear")
    public String guardarPedido(@RequestParam Long vehiculoId, @ModelAttribute Pedidos pedido) {
        // Obtener el Vehículo usando el ID proporcionado
        Vehiculo vehiculo = vehiculoServicio.obtenerPorId(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        pedido.setVehiculo(vehiculo);

        if (pedido.getEstado() == null || pedido.getEstado().isEmpty()) {
            pedido.setEstado("Pendiente");  // Valor predeterminado
        }

        pedidoServicio.guardar(pedido);

        return "redirect:/pedido/listar"; // Redirige a la lista de pedidos
    }

    // Procesar la compra de un pedido
    @PostMapping("/comprar")
    public String procesarCompra(@RequestParam Long pedidoId, RedirectAttributes redirectAttributes) {
        // Obtener el pedido
        Pedidos pedido = pedidoServicio.obtenerPorId(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Crear un nuevo pago relacionado
        Pago nuevoPago = new Pago();
        nuevoPago.setMonto(pedido.getCantidad() * 100.0); // Ejemplo: monto basado en cantidad
        nuevoPago.setEstado("Pagado");

        // Guardar el pago
        pagoServicio.guardar(nuevoPago);

        // Eliminar el pedido de la base de datos
        System.out.println("Intentando eliminar el pedido con ID: " + pedidoId);
        pedidoServicio.eliminarPorId(pedidoId);

        // Agregar mensaje de éxito
        redirectAttributes.addFlashAttribute("mensajeExito", "¡Reserva comprada con éxito!");

        // Redirigir a la pantalla de pagos
        return "redirect:/pago/listar";
    }




    // Eliminar un pedido
    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable("id") Long id) {
        pedidoServicio.eliminarPorId(id);
        return "redirect:/pedido/listar"; // Redirigir a la lista de pedidos
    }

    // Ver detalles de un pedido
    @GetMapping("/ver/{id}")
    public String verPedido(@PathVariable("id") Long id, Model model) {
        Pedidos pedido = pedidoServicio.obtenerPorId(id).orElse(null);
        model.addAttribute("pedido", pedido);
        return "verPedido"; // Vista que muestra los detalles del pedido
    }
}
