package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Pago;
import co.ucentral.Concesionario.servicios.PagoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/pago")
public class PagoControlador {

    private final PagoServicio pagoServicio;

    // Mostrar todos los pagos
    @GetMapping("/listar")
    public String listarPagos(Model model) {
        model.addAttribute("pagos", pagoServicio.obtenerTodos());
        return "pago"; // Pantalla de pagos
    }



    // Crear un nuevo pago
    @GetMapping("/crear")
    public String crearPago(Model model) {
        model.addAttribute("pago", new Pago());
        return "crearPago"; // Vista con formulario para crear un pago
    }

    @PostMapping("/crear")
    public String guardarPago(@ModelAttribute Pago pago) {
        if (pago.getEstado() == null || pago.getEstado().isEmpty()) {
            pago.setEstado("Pendiente"); // Valor predeterminado
        }
        pagoServicio.guardar(pago);
        return "redirect:/pago/listar"; // Redirige a la lista de pagos
    }

    // Eliminar un pago
    @GetMapping("/eliminar/{id}")
    public String eliminarPago(@PathVariable("id") Long id) {
        pagoServicio.eliminarPorId(id);
        return "redirect:/pago/listar"; // Redirige a la lista de pagos
    }

    // Mostrar detalles de un pago
    @GetMapping("/ver/{id}")
    public String verPago(@PathVariable("id") Long id, Model model) {
        Pago pago = pagoServicio.obtenerPorId(id).orElse(null);
        model.addAttribute("pago", pago);
        return "verPago"; // Vista que muestra los detalles del pago
    }
    @PostMapping("/eliminarTodos")
    public String eliminarTodosLosPagos(RedirectAttributes redirectAttributes) {
        pagoServicio.eliminarTodos(); // Llama al servicio para borrar los registros
        redirectAttributes.addFlashAttribute("mensajeExito", "Todos los pagos han sido eliminados exitosamente.");
        return "redirect:/pago/listar"; // Redirige a la lista de pagos
    }


}
