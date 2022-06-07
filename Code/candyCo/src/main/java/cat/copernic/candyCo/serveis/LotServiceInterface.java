/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.model.Lot;
import java.util.List;

/**
 *
 * @author Adrián
 */
public interface LotServiceInterface {

    public List<Lot> llistarLots(); //Mètode que emplementarem per llistar lots

    public void afegirLot(Lot lot); //Mètode que emplementarem per afegir un lot

    public Lot cercarLot(long lot); //Mètode que emplementarem per cercar lots
    
    public void eliminarLot(long lot); //Mètode que emplementarem per eliminar un lot per id
    
    public void eliminarLotByObject(Lot lot); //Mètode que emplementarem per eliminar un lot amb el seus moviments

}
