package cat.copernic.candyCo.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author marco
 */
@Controller
public class ControladorLogin {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
