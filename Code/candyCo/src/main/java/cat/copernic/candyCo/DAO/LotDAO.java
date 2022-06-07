/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.DAO;

import cat.copernic.candyCo.model.Lot;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Adrián
 */
public interface LotDAO extends CrudRepository<Lot, Long> {
    
}
