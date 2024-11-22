package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Pedidos;
import co.ucentral.Concesionario.persistencia.entidades.Vehiculo;
import co.ucentral.Concesionario.servicios.PedidoServicio;
import co.ucentral.Concesionario.servicios.VehiculoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/pedido")
public class PedidoControlador {

    private final PedidoServicio pedidoServicio;
    private final VehiculoServicio vehiculoServicio;

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
