/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.DAO.MovimentDAO;
import cat.copernic.candyCo.model.Moviment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adrián
 */
@Service
public class MovimentService implements MovimentServiceInterface {

    @Autowired
    private MovimentDAO moviment;

    @Override
    @Transactional(readOnly = true)
    public List<Moviment> llistarMoviments() {
        return (List<Moviment>) moviment.findAll();
    }

    @Override
    public void afegirMoviments(Moviment moviment) {
        this.moviment.save(moviment);
    }

    @Override
    @Transactional
    public void eliminarMoviments(long moviment) {
        this.moviment.deleteById(moviment);
    }

    @Override
    public Moviment cercarMoviment(long moviment) {
        return this.moviment.findById(moviment).orElse(null);
    }
}
