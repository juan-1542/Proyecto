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
@RequestMapping("/vehiculo")
public class VehiculoControlador {

    private final VehiculoServicio vehiculoServicio;


    @GetMapping
    public String mostrarListaVehiculos(Model model) {
        try {
            List<Vehiculo> vehiculos = vehiculoServicio.obtenerTodos();
            model.addAttribute("vehiculo", vehiculos);
            model.addAttribute("vehiculo", new Vehiculo());
            return "vehiculo";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @GetMapping("/consulta")
    public String consultarVehiculos(Model model) {
        try {
            List<Vehiculo> vehiculos = vehiculoServicio.obtenerTodos();
            model.addAttribute("vehiculo", vehiculos);
            return "consultarVehiculo";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @PostMapping("/registro")
    public String procesarRegistroVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        try {
            vehiculoServicio.guardar(vehiculo);
            return "redirect:/vehiculo";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable("id") Long id) {
        try {
            vehiculoServicio.eliminarPorId(id);
            return "redirect:/vehiculo/consulta";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        try {
            Vehiculo vehiculo = vehiculoServicio.obtenerPorId(id).orElseThrow(() -> new Exception("Vehículo no encontrado"));
            model.addAttribute("vehiculo", vehiculo);
            return "editarVehiculo"; // Vista para editar el vehículo
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    @PostMapping("/editar/{id}")
    public String actualizarVehiculo(@PathVariable("id") Long id, @ModelAttribute Vehiculo vehiculo) {
        try {
            Vehiculo vehiculoExistente = vehiculoServicio.obtenerPorId(id).orElseThrow(() -> new Exception("Vehículo no encontrado"));
            vehiculoExistente.setMarca(vehiculo.getMarca());
            vehiculoExistente.setModelo(vehiculo.getModelo());
            vehiculoExistente.setAno(vehiculo.getAno());
            vehiculoExistente.setPrecio(vehiculo.getPrecio());
            vehiculoServicio.guardar(vehiculoExistente);
            return "redirect:/vehiculo/consulta";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
