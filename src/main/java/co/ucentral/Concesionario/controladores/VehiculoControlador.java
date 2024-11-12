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
            model.addAttribute("vehiculo", new Vehiculo());
            return "vehiculo"; // Página que contiene el listado de vehículos y el formulario
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Página de error si no se pueden obtener los vehículos
        }
    }

    // Página para consultar vehículos (mostrar la lista de vehículos registrados)
    @GetMapping("/consulta")
    public String consultarVehiculos(Model model) {
        try {
            List<Vehiculo> vehiculos = vehiculoServicio.obtenerTodos();
            model.addAttribute("vehiculos", vehiculos);
            return "consultarVehiculo"; // Vista para consultar vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Página de error si no se pueden obtener los vehículos
        }
    }

    // Procesar el registro de un vehículo
    @PostMapping("/registro")
    public String procesarRegistroVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        try {
            vehiculoServicio.guardar(vehiculo);
            return "redirect:/vehiculos"; // Redirigir a la página principal de vehículos
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Página de error si no se puede guardar el vehículo
        }
    }

    // Método para eliminar un vehículo por su id
    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable("id") Long id) {
        try {
            vehiculoServicio.eliminarPorId(id);
            return "redirect:/vehiculos/consulta"; // Redirigir a la página de consulta después de eliminar
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Página de error si no se puede eliminar el vehículo
        }
    }

    // Método para editar un vehículo
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        try {
            Vehiculo vehiculo = vehiculoServicio.obtenerPorId(id).orElseThrow(() -> new Exception("Vehículo no encontrado"));
            model.addAttribute("vehiculo", vehiculo);
            return "editarVehiculo"; // Vista para editar el vehículo
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Página de error si no se puede encontrar el vehículo
        }
    }

    // Procesar la actualización de un vehículo
    @PostMapping("/editar/{id}")
    public String actualizarVehiculo(@PathVariable("id") Long id, @ModelAttribute Vehiculo vehiculo) {
        try {
            Vehiculo vehiculoExistente = vehiculoServicio.obtenerPorId(id).orElseThrow(() -> new Exception("Vehículo no encontrado"));
            vehiculoExistente.setMarca(vehiculo.getMarca());
            vehiculoExistente.setModelo(vehiculo.getModelo());
            vehiculoExistente.setAno(vehiculo.getAno());
            vehiculoExistente.setPrecio(vehiculo.getPrecio());
            vehiculoServicio.guardar(vehiculoExistente);
            return "redirect:/vehiculos/consulta"; // Redirigir a la página de consulta después de actualizar
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Página de error si no se puede actualizar el vehículo
        }
    }
}
