/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.DAO;

import cat.copernic.candyCo.model.Moviment;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Adrián
 */
public interface MovimentDAO extends CrudRepository<Moviment, Long>{
    
}
