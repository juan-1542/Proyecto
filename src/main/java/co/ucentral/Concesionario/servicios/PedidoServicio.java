package co.ucentral.Concesionario.servicios;

import co.ucentral.Concesionario.persistencia.entidades.Pedido;
import co.ucentral.Concesionario.persistencia.respositorios.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    // Obtener todos los pedidos
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepositorio.findAll();
    }

    // Obtener un pedido por su ID
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepositorio.findById(id);
    }

    // Guardar un nuevo pedido
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    // Actualizar un pedido
    public Pedido actualizarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    // Eliminar un pedido
    public void eliminarPedido(Long id) {
        pedidoRepositorio.deleteById(id);
    }
}
