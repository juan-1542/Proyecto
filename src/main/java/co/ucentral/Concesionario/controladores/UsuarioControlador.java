package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Usuario;
import co.ucentral.Concesionario.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;


    public List<Usuario> obtenerTodos() {
        return usuarioServicio.obtenerTodos();
    }


    @GetMapping("/")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }


    @PostMapping("/registro")
    public String procesarRegistroUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioServicio.guardar(usuario);
        return "redirect:/registroExitoso";
    }
}
