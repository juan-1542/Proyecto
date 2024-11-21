package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Pedido;
import co.ucentral.Concesionario.servicios.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;


    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoServicio.obtenerTodosLosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    // Obtener un pedido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoServicio.obtenerPedidoPorId(id);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoServicio.guardarPedido(pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    // Actualizar un pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (pedidoServicio.obtenerPedidoPorId(id).isPresent()) {
            pedido.setId(id);
            Pedido pedidoActualizado = pedidoServicio.actualizarPedido(pedido);
            return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        if (pedidoServicio.obtenerPedidoPorId(id).isPresent()) {
            pedidoServicio.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
