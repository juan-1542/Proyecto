package co.ucentral.Concesionario.servicios;

import co.ucentral.Concesionario.persistencia.entidades.Proveedor;
import co.ucentral.Concesionario.persistencia.respositorios.ProveedorRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProveedorServicio {

    private final ProveedorRepositorio proveedorRepositorio;

    public List<Proveedor> obtenerTodos() {
        return proveedorRepositorio.findAll();
    }

    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }
}
