/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.controlador;

import cat.copernic.candyCo.serveis.MovimentServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Adrián
 */
@Controller
@Slf4j
public class ControladorMoviment {
    
    //@Autowired
    //private MovimentServiceInterface movimentService;

    @GetMapping("/moviment")
    public String productes() {
        return "moviment";
    }
    
    @PostMapping("/moviment")
    public String productesGet() {
        return "moviment";
    }
}
