package co.ucentral.Concesionario.persistencia.respositorios;

import co.ucentral.Concesionario.persistencia.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {


    @Query("SELECT p FROM Proveedor p LEFT JOIN FETCH p.pedidos WHERE p.id = :id")
    Optional<Proveedor> findByIdWithPedidos(@Param("id") Long id);
}
