/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.model.Moviment;
import java.util.List;

/**
 *
 * @author Adrián
 */
public interface MovimentServiceInterface {
    
    public List<Moviment> llistarMoviments();
    
    public void afegirMoviments(Moviment moviment);
    
    public void eliminarMoviments(long moviment);
    
    public Moviment cercarMoviment(long moviment);
    
}
