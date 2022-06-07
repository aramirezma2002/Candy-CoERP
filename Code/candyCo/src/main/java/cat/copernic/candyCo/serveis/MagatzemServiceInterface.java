/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.model.Magatzem;
import java.util.List;

/**
 *
 * @author Adrián
 */
public interface MagatzemServiceInterface {

    public List<Magatzem> llistarMagatzem(); //Mètode que emplementarem per llistar magatzems

    public void afegirMagatzem(Magatzem magatzem); //Mètode que emplementarem per afegir un magatzem

    public void eliminarMagatzem(Magatzem magatzem); //Mètode que emplementarem per eliminar un magatzem

    public Magatzem cercarMagatzem(long magatzem); //Mètode que emplementarem per cercar magatzem
    
}
