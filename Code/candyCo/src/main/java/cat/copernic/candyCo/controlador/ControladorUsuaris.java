package cat.copernic.candyCo.controlador;

import cat.copernic.candyCo.model.Admin;
import cat.copernic.candyCo.repository.PasswordEncrypt;
import cat.copernic.candyCo.serveis.AdminServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author marco
 */
@Controller
@Slf4j
public class ControladorUsuaris {
    
    @Autowired
    private AdminServiceInterface usuarisService;

    @GetMapping("/usuaris")
    public String productes(Model model) {
        var usuaris = usuarisService.llistarAdmins();
        model.addAttribute("user", new Admin());
        model.addAttribute("usuaris", usuaris);
        return "usuaris";
    }
    
    @PostMapping("/ucreate")
    public String productesGet(Admin admin, @RequestParam(value = "passwd") String password, Model model) {
        admin.setPassword(PasswordEncrypt.passwordEncrypter(password));
        usuarisService.afegirAdmin(admin);
        return "redirect:/usuaris";
    }
    
    @GetMapping("/usuaris/eliminar/{dni}")
    public String eliminar(Admin admin) {
        usuarisService.eliminarAdminByObject(usuarisService.cercarAdminByDni(admin.getDni()));
        return "redirect:/usuaris"; //Retornem a la pàgina inici mitjançant redirect
    }
    
    @PostMapping("usuaris/eliminartots")
    public String eliminar(@RequestParam(value = "deleteinput") String admins) {
        String arrayString[] = admins.split(",");
        for(String admin:arrayString){
            usuarisService.eliminarAdmin(usuarisService.cercarAdminByDni(admin));
        }
        return "redirect:/usuaris"; //Retornem a la pàgina inici mitjançant redirect
    }
}
