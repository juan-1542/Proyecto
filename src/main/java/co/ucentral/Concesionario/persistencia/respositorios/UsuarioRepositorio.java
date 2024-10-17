package co.ucentral.Concesionario.persistencia.respositorios;

import co.ucentral.Concesionario.persistencia.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario,String> {
}
