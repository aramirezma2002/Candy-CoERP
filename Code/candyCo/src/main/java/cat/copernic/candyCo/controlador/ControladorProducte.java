package cat.copernic.candyCo.controlador;

import cat.copernic.candyCo.model.Producte;
import cat.copernic.candyCo.serveis.ProducteServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author marco
 */
@Controller
@Slf4j
public class ControladorProducte {
    
    @Autowired
    private ProducteServiceInterface producteService;

    @GetMapping("/productes")
    public String productes(Model model) {
        var productes = producteService.llistarProductes();
        model.addAttribute("product", new Producte());
        model.addAttribute("productes", productes);
        return "productes";
    }
    
    @PostMapping("/pcreate")
    public String productesGet(Producte producte, Model model) {
        producteService.afegirProducte(producte);
        return "redirect:/productes";
    }
     
    @GetMapping("productes/eliminar/{idProducte}")
    public String eliminar(Producte producte) {
        producteService.eliminarProducteByObject(producte);
        return "redirect:/productes"; //Retornem a la pàgina inici mitjançant redirect
    }
    
}
