package co.ucentral.Concesionario.persistencia.respositorios;

import co.ucentral.Concesionario.persistencia.entidades.Pedidos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidosRepositorio extends JpaRepository<Pedidos, Long> {

    List<Pedidos> findByVehiculoId(Long vehiculoId);
}
