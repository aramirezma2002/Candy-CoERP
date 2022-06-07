package cat.copernic.candyCo.controlador;

import cat.copernic.candyCo.model.Lot;
import cat.copernic.candyCo.model.Magatzem;
import cat.copernic.candyCo.serveis.LotServiceInterface;
import cat.copernic.candyCo.serveis.MagatzemServiceInterface;
import cat.copernic.candyCo.serveis.MovimentServiceInterface;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ControladorMagatzem {

    @Autowired
    private MagatzemServiceInterface magatzemService;
    private LotServiceInterface lotService;
    private MovimentServiceInterface movimentService;

    @PostMapping("/magatzem")
    public String magatzem() {

        return "magatzem";
    }

    @GetMapping("/magatzem") //Arrel de l'aplicació localhost:8080
    public String magatzemGet(Model model) {
        var magatzems = magatzemService.llistarMagatzem();

        model.addAttribute("magatzems", magatzems);
        return "magatzem"; //Retorna la pàgina de magatzem
    }

    @GetMapping("/magatzem#") //Arrel de l'aplicació localhost:8080
    public String magatzemPop(Model model) {
        var magatzems = magatzemService.llistarMagatzem();

        model.addAttribute("magatzems", magatzems);
        return "magatzem#"; //Retorna la pàgina de magatzem
    }

    @GetMapping("magatzem/eliminar/{id_magatzem}")
    public String eliminar(Magatzem magatzem) {
        magatzemService.eliminarMagatzem(magatzem);
        return "redirect:/magatzem"; //Retornem a la pàgina inici mitjançant redirect
    }
    
    @PostMapping("magatzem/eliminartots")
    public String eliminar(@RequestParam(value = "deleteinput") String magatzems) {
        String arrayString[] = magatzems.split(",");
        for(String magatzem:arrayString){
            long mylong = Long.parseLong(magatzem);
            magatzemService.eliminarMagatzem(magatzemService.cercarMagatzem(mylong));
        }
        return "redirect:/magatzem"; //Retornem a la pàgina inici mitjançant redirect
    }
}
