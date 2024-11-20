package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.servicios.ProveedorServicio;
import co.ucentral.Concesionario.persistencia.entidades.Proveedor;
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

    @GetMapping
    public String mostrarProveedores(Model model) {
        try {
            List<Proveedor> proveedores = proveedorServicio.obtenerTodos();
            model.addAttribute("proveedores", proveedores); // Aquí asignamos la lista de proveedores
            return "proveedor"; // Nombre de la vista Thymeleaf
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/registro")
    public String registrarProveedor(@ModelAttribute("proveedor") Proveedor proveedor) {
        try {
            proveedorServicio.guardar(proveedor);
            return "redirect:/proveedor";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    @PostMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") Long id) {
        proveedorServicio.eliminarProveedor(id);
        return "redirect:/proveedor";
    }
}
