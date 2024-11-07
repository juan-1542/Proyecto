package co.ucentral.Concesionario.controladores;

import co.ucentral.Concesionario.persistencia.entidades.Usuario;
import co.ucentral.Concesionario.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;


    @GetMapping("/")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro"; // Vista de registro
    }


    @PostMapping("/registro")
    public String procesarRegistroUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioServicio.guardar(usuario);
        return "iniciosesion";
    }


    @GetMapping("/iniciosesion")
    public String mostrarInicioSesion(Model model) {

        return "iniciosesion";
    }


    @PostMapping("/iniciosesion")
    public String procesarInicioSesion(@ModelAttribute("usuario") Usuario usuario, Model model) {
        Usuario usuarioAutenticado = usuarioServicio.validarUsuario(usuario.getNombre(), usuario.getContraseña());
        if (usuarioAutenticado != null) {
            return "vehiculo";
        } else {

            model.addAttribute("error", "Credenciales incorrectas");
            return "iniciosesion";
        }
    }

}
