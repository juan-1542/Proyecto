package co.ucentral.Concesionario.persistencia.respositorios;

import co.ucentral.Concesionario.persistencia.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    // Aquí puedes agregar consultas personalizadas si es necesario
}
