package cat.copernic.candyCo.controlador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/**
 *
 * @author pedro
 */
@Controller
@Slf4j
public class ControladorCrearMagatzem {
    
    @PostMapping("/crearMagatzem") //Arrel de l'aplicació localhost:8080
    public String crearMagatzem() {
        return "crearMagatzem"; //Retorna la pàgina home
    }
    
    @GetMapping("/crearMagatzem") //Arrel de l'aplicació localhost:8080
    public String magatzemGet() {
        return "crearMagatzem"; //Retorna la pàgina home
    }
}