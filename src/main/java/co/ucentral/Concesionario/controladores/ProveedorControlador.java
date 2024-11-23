package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Proveedor;
import co.ucentral.Concesionario.servicios.ProveedorServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {

    private final ProveedorServicio proveedorServicio;

    // Método para mostrar la lista de proveedores
    @GetMapping
    public String mostrarProveedores(Model model) {
        try {
            List<Proveedor> proveedores = proveedorServicio.obtenerTodos();
            model.addAttribute("proveedores", proveedores);
            return "proveedor"; // Vista que muestra la lista de proveedores
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Vista en caso de error
        }
    }

    // Método para registrar un nuevo proveedor
    @PostMapping("/registro")
    public String registrarProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
        try {
            proveedorServicio.guardar(proveedor);
            return "redirect:/proveedor"; // Redirige a la lista de proveedores después de guardar
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Vista en caso de error
        }
    }

    // Método para eliminar un proveedor
    @PostMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") Long id) {
        try {
            proveedorServicio.eliminarProveedor(id);
            return "redirect:/proveedor"; // Redirige a la lista de proveedores después de eliminar
        } catch (Exception i) {
            i.printStackTrace();
            return "error"; // Vista en caso de error
        }
    }

    // Método para mostrar el formulario de edición de un proveedor
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        try {
            Proveedor proveedor = proveedorServicio.obtenerPorId(id)
                    .orElseThrow(() -> new Exception("Proveedor no encontrado"));
            model.addAttribute("proveedor", proveedor);
            return "editarProveedor"; // Vista para editar el proveedor
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Vista en caso de error
        }
    }

    // Método para actualizar un proveedor
    @PostMapping("/editar/{id}")
    public String actualizarProveedor(@PathVariable("id") Long id, @ModelAttribute Proveedor proveedorActualizado) {
        try {
            Proveedor proveedorExistente = proveedorServicio.obtenerPorId(id)
                    .orElseThrow(() -> new Exception("Proveedor no encontrado"));
            proveedorExistente.setNombre(proveedorActualizado.getNombre());
            proveedorExistente.setContacto(proveedorActualizado.getContacto());
            proveedorServicio.guardar(proveedorExistente); // Guarda los cambios
            return "redirect:/proveedor"; // Redirige a la lista de proveedores después de actualizar
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Vista en caso de error
        }
    }

    // Método para redirigir a la página de vehículos
    @GetMapping("/vehiculo")
    public String mostrarVehiculos(Model model) {
        // Lógica para obtener los vehículos si es necesario
        // model.addAttribute("vehiculos", vehiculos);
        return "vehiculo"; // Asegúrate de que "vehiculo" sea el nombre del archivo HTML correspondiente
    }
}
