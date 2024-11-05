package co.ucentral.Concesionario.servicios;

import co.ucentral.Concesionario.persistencia.entidades.Usuario;
import co.ucentral.Concesionario.persistencia.respositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;


    public List<Usuario> obtenerTodos() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }


    public boolean borrar(Usuario usuario) {
        try {
            usuarioRepositorio.delete(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void guardar(Usuario usuario) {
        try {
            usuarioRepositorio.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario", e);
        }
    }
}
