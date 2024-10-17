package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Usuario;
import co.ucentral.Concesionario.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class UsuarioControlador {

    UsuarioServicio usuarioServicio;

    public List<Usuario> obtenerTodos() {
        return usuarioServicio.obtenerTodos();
    }
}
