package co.ucentral.Concesionario.servicios;

import co.ucentral.Concesionario.persistencia.entidades.Vehiculo;
import co.ucentral.Concesionario.persistencia.respositorios.VehiculoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VehiculoServicio {

    private final VehiculoRepositorio vehiculoRepositorio;

    // Método para guardar un vehículo
    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepositorio.save(vehiculo);
    }

    // Método para obtener todos los vehículos
    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepositorio.findAll();
    }

    // Método para obtener un vehículo por su id
    public Optional<Vehiculo> obtenerPorId(Long id) {
        return vehiculoRepositorio.findById(id);
    }

    // Método para eliminar un vehículo por su id
    public void eliminarPorId(Long id) {
        vehiculoRepositorio.deleteById(id);
    }
}
