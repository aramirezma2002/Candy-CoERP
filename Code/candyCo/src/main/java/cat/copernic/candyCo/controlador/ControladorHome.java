package cat.copernic.candyCo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author marco
 */
@Controller
public class ControladorHome {

    @PostMapping("/home") //Arrel de l'aplicació localhost:8080 amb home
    public String home() {
        return "home"; //Retorna la pàgina home
    }

    @GetMapping("/home") //Arrel de l'aplicació localhost:8080
    public String homeGet() {
        return "home"; //Retorna la pàgina home
    }
}
