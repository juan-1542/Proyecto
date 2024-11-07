package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Vehiculo;
import co.ucentral.Concesionario.servicios.VehiculoServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/vehiculos")
public class VehiculoControlador {

    private final VehiculoServicio vehiculoServicio;

    // Mostrar lista de vehículos y el formulario de registro en la misma página
    @GetMapping
    public String mostrarListaVehiculos(Model model) {
        try {
            List<Vehiculo> vehiculos = vehiculoServicio.obtenerTodos();
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("vehiculo", new Vehiculo()); // Para el formulario
            return "vehiculo"; // Vista que contiene la lista y el formulario
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Procesar registro de un nuevo vehículo
    @PostMapping("/registro")
    public String procesarRegistroVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        try {
            vehiculoServicio.guardar(vehiculo);
            return "redirect:/vehiculos"; // Redirige para mostrar la lista actualizada
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
