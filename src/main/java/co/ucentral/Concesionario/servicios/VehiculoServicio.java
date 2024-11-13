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


    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepositorio.save(vehiculo);
    }


    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepositorio.findAll();
    }


    public Optional<Vehiculo> obtenerPorId(Long id) {
        return vehiculoRepositorio.findById(id);
    }


    public void eliminarPorId(Long id) {
        vehiculoRepositorio.deleteById(id);
    }
}
