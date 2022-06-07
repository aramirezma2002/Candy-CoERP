/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.controlador;

import cat.copernic.candyCo.model.Admin;
import cat.copernic.candyCo.model.Lot;
import cat.copernic.candyCo.model.Moviment;
import cat.copernic.candyCo.model.Producte;
import cat.copernic.candyCo.serveis.AdminServiceInterface;
import cat.copernic.candyCo.serveis.LotServiceInterface;
import cat.copernic.candyCo.serveis.MagatzemServiceInterface;
import cat.copernic.candyCo.serveis.MovimentServiceInterface;
import cat.copernic.candyCo.serveis.ProducteServiceInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adrián
 */
@Controller
@Slf4j
public class ControladorLot {

    @Autowired
    private LotServiceInterface lotService;
    
    @Autowired
    private MagatzemServiceInterface magatzemService;
    
    @Autowired
    private MovimentServiceInterface movimentService;
    
    @Autowired
    private AdminServiceInterface adminService;
    
    @Autowired
    private ProducteServiceInterface producteService;

    @GetMapping("/lots") //Arrel de l'aplicació localhost:8080
    public String lotsGet(@AuthenticationPrincipal User admin, Model model) {
        var lots = lotService.llistarLots();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date data = new Date(System.currentTimeMillis());
        
        var magatzems = magatzemService.llistarMagatzem();
        var productes = producteService.llistarProductes();
        
        Admin admi = adminService.cercarAdminByCorreu(admin.getUsername());
        
        model.addAttribute("lots", lots);
        model.addAttribute("lot", new Lot());
        model.addAttribute("data", formatter.format(data));
        model.addAttribute("magatzems", magatzems);
        model.addAttribute("moviment", new Moviment());
        model.addAttribute("productes", productes);
        model.addAttribute("producte", new Producte());
        model.addAttribute("adminName", admi.getNom()+" "+admi.getCognom());
        return "lots"; //Retorna la pàgina de lots
    }
    
    @PostMapping("/mcreate")
    public String mcreate(@AuthenticationPrincipal User admin, Moviment moviment,
        @RequestParam(value = "magtzemt") long magatzem, @RequestParam(value = "lotidinput") long lot, Model model) { 
        
        moviment.setId_magatzem(magatzemService.cercarMagatzem(magatzem));
        moviment.setDni_admin(adminService.cercarAdminByCorreu(admin.getUsername()));
        moviment.setId_lot(lotService.cercarLot(lot));
        movimentService.afegirMoviments(moviment);
        
        return "redirect:/lots";
    }
    
    @PostMapping("/lcreate")
    public String productesGet(@AuthenticationPrincipal User admin, Lot lot, @RequestParam(value = "idlotmagatzem") long magatzem,
        @RequestParam(value = "idlotproducte") long producte, @RequestParam(value = "data_f", required = false) String finalitzacio, Model model) throws ParseException {
        System.out.println("Aqui");
        if (null != finalitzacio && !"".equals(finalitzacio)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(finalitzacio);
            java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
            lot.setData_finalitzacio(sqlDate);
        }
        lot.setAutor(adminService.cercarAdminByCorreu(admin.getUsername()));
        lot.setId_magatzem(magatzemService.cercarMagatzem(magatzem));
        lot.setId_producte(producteService.cercarProducte(producte));
        System.out.println(lot);
        lotService.afegirLot(lot);
        return "redirect:/lots";
    }
    
    @GetMapping("lot/eliminar/{id_lot}")
    public String eliminar(Lot lot) {
        lotService.eliminarLotByObject(lot);
        return "redirect:/lots"; //Retornem a la pàgina inici mitjançant redirect
    }
    
    @PostMapping("lot/eliminartots")
    public String eliminar(@RequestParam(value = "deleteinput") String lots) {
        String arrayString[] = lots.split(",");
        for(String lot:arrayString){
            long mylong = Long.parseLong(lot);
            lotService.eliminarLotByObject(lotService.cercarLot(mylong));
        }
        return "redirect:/lots"; //Retornem a la pàgina inici mitjançant redirect
    }
}
