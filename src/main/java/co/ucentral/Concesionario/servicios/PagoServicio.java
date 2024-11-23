package co.ucentral.Concesionario.servicios;

import co.ucentral.Concesionario.persistencia.entidades.Pago;
import co.ucentral.Concesionario.persistencia.respositorios.PagoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PagoServicio {

    private final PagoRepositorio pagoRepositorio;

    // Guardar un pago
    public Pago guardar(Pago pago) {
        return pagoRepositorio.save(pago);
    }

    // Obtener todos los pagos
    public List<Pago> obtenerTodos() {
        return pagoRepositorio.findAll();
    }

    // Obtener un pago por ID
    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepositorio.findById(id);
    }

    // Eliminar un pago por ID
    public void eliminarPorId(Long id) {
        pagoRepositorio.deleteById(id);
    }
    public void eliminarTodos() {
        pagoRepositorio.deleteAll(); // Elimina todos los registros de la tabla
    }


}
