package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Pedidos;
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
@RequestMapping("/vehiculo")
public class VehiculoControlador {

    private final VehiculoServicio vehiculoServicio;
    private final PedidoServicio pedidoServicio;

    // Mostrar lista de vehículos
    @GetMapping
    public String mostrarListaVehiculos(Model model) {
        try {
            // Obtener todos los vehículos desde el servicio
            List<Vehiculo> vehiculos = vehiculoServicio.obtenerTodos();
            // Agregar la lista de vehículos al modelo
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("vehiculo", new Vehiculo()); // Asegura que el modelo vehiculo esté disponible para agregar nuevos vehículos
            return "vehiculo";  // Vista para mostrar los vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // En caso de error
        }
    }

    // Consultar vehículos
    @GetMapping("/consulta")
    public String consultarVehiculos(Model model) {
        try {
            // Obtener todos los vehículos desde el servicio
            List<Vehiculo> vehiculos = vehiculoServicio.obtenerTodos();
            // Pasar la lista de vehículos al modelo para mostrarla en la vista
            model.addAttribute("vehiculos", vehiculos);
            return "consultarVehiculo";  // Vista para consultar vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Registrar un vehículo
    @PostMapping("/registro")
    public String procesarRegistroVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        try {
            vehiculoServicio.guardar(vehiculo); // Guardar el nuevo vehículo
            return "redirect:/vehiculo";  // Redirige a la lista de vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Eliminar un vehículo
    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable("id") Long id) {
        try {
            // Primero, obtener todos los pedidos asociados al vehículo
            List<Pedidos> pedidos = pedidoServicio.obtenerPedidosPorVehiculo(id);

            // Eliminar los pedidos asociados (si es necesario)
            for (Pedidos pedido : pedidos) {
                pedidoServicio.eliminarPorId(pedido.getId());
            }

            // Ahora, eliminar el vehículo
            vehiculoServicio.eliminarPorId(id); // Eliminar el vehículo
            return "redirect:/vehiculo/consulta";  // Redirige a la consulta de vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // En caso de error
        }
    }


    // Mostrar formulario de edición de vehículo
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        try {
            Vehiculo vehiculo = vehiculoServicio.obtenerPorId(id).orElseThrow(() -> new Exception("Vehículo no encontrado"));
            model.addAttribute("vehiculo", vehiculo); // Cargar el vehículo para editar
            return "editarVehiculo";  // Vista para editar el vehículo
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Actualizar un vehículo
    @PostMapping("/editar/{id}")
    public String actualizarVehiculo(@PathVariable("id") Long id, @ModelAttribute Vehiculo vehiculo) {
        try {
            Vehiculo vehiculoExistente = vehiculoServicio.obtenerPorId(id).orElseThrow(() -> new Exception("Vehículo no encontrado"));
            vehiculoExistente.setMarca(vehiculo.getMarca());
            vehiculoExistente.setModelo(vehiculo.getModelo());
            vehiculoExistente.setAno(vehiculo.getAno());
            vehiculoExistente.setPrecio(vehiculo.getPrecio());
            vehiculoServicio.guardar(vehiculoExistente); // Guardar el vehículo actualizado
            return "redirect:/vehiculo/consulta";  // Redirige a la consulta de vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Mostrar formulario para registrar un pedido
    @GetMapping("/pedido")
    public String crearPedido(@PathVariable("vehiculoId") Long vehiculoId, Model model) {
        try {
            Vehiculo vehiculo = vehiculoServicio.obtenerPorId(vehiculoId).orElseThrow(() -> new Exception("Vehículo no encontrado"));
            model.addAttribute("vehiculo", vehiculo); // Pasar el vehículo para crear el pedido
            model.addAttribute("pedido", new Pedidos()); // Crear un nuevo pedido vacío
            return "crearPedido";  // Vista para crear un pedido
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Procesar el registro del pedido
    @PostMapping("/pedido")
    public String registrarPedido(@RequestParam Long vehiculoId, @RequestParam int cantidad, @RequestParam String descripcion, Model model) {
        try {
            // Obtener el vehículo relacionado con el pedido
            Vehiculo vehiculo = vehiculoServicio.obtenerPorId(vehiculoId).orElseThrow(() -> new Exception("Vehículo no encontrado"));

            // Crear el nuevo pedido
            Pedidos pedido = new Pedidos();
            pedido.setVehiculo(vehiculo);
            pedido.setCantidad(cantidad);
            pedido.setDescripcion(descripcion);
            pedido.setEstado("Pendiente");  // Estado inicial del pedido
            pedidoServicio.guardar(pedido); // Guardar el pedido

            // Redirigir a la página de pedidos o vehículos
            model.addAttribute("pedido", pedido);
            return "redirect:/vehiculo/consulta";  // Redirige a la consulta de vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // En caso de error
        }
    }

    // Eliminar un pedido
    @GetMapping("/pedido/eliminar/{id}")
    public String eliminarPedido(@PathVariable("id") Long id) {
        try {
            pedidoServicio.eliminarPorId(id); // Eliminar el pedido
            return "redirect:/vehiculo/consulta";  // Redirige a la consulta de vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
