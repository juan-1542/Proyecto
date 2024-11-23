package co.ucentral.Concesionario.servicios;


import co.ucentral.Concesionario.persistencia.entidades.Pedidos;
import co.ucentral.Concesionario.persistencia.respositorios.PedidosRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PedidoServicio {

    private final PedidosRepositorio pedidoRepositorio;

    // Guardar un pedido
    public Pedidos guardar(Pedidos pedido) {
        return pedidoRepositorio.save(pedido);
    }

    // Obtener todos los pedidos
    public List<Pedidos> obtenerTodos() {
        return pedidoRepositorio.findAll();
    }

    // Obtener un pedido por ID
    public Optional<Pedidos> obtenerPorId(Long id) {
        return pedidoRepositorio.findById(id);
    }

    // Eliminar un pedido por ID
    public void eliminarPorId(Long id) {
        pedidoRepositorio.deleteById(id);
    }
    public List<Pedidos> obtenerPedidosPorVehiculo(Long vehiculoId) {
        return pedidoRepositorio.findByVehiculoId(vehiculoId);
    }

}
