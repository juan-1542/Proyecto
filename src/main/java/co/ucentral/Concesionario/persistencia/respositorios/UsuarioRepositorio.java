package co.ucentral.Concesionario.persistencia.respositorios;

import co.ucentral.Concesionario.persistencia.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, String> {

}
