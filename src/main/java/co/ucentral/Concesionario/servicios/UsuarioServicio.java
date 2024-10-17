package co.ucentral.Concesionario.servicios;

import co.ucentral.Concesionario.persistencia.entidades.Usuario;
import co.ucentral.Concesionario.persistencia.respositorios.UsuarioRepositorio;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioServicio {

    UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> obtenerTodos(){
        List<Usuario> listado = (List<Usuario>) usuarioRepositorio.findAll();
        return listado;
    }

    public boolean borrar(Usuario usuario){
        try{
            usuarioRepositorio.delete(usuario);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}