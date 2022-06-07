/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.DAO.LotDAO;
import cat.copernic.candyCo.model.Lot;
import cat.copernic.candyCo.model.Moviment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adrián
 */
@Service
public class LotService implements LotServiceInterface {

    @Autowired
    private LotDAO lot;

    @Autowired
    private MovimentServiceInterface movimentService;

    @Override
    @Transactional(readOnly = true)
    public List<Lot> llistarLots() {
        return (List<Lot>) lot.findAll();
    }

    @Override
    public void afegirLot(Lot lot) {
        this.lot.save(lot);
    }

    @Override
    @Transactional
    public void eliminarLot(long lot) {
        this.lot.deleteById(lot);
    }

    @Override
    @Transactional(readOnly = true)
    public Lot cercarLot(long lot) {
        return this.lot.findById(lot).orElse(null);
    }

    @Override
    @Transactional
    public void eliminarLotByObject(Lot lot) {
        int sizeMov = movimentService.llistarMoviments().size();
        ArrayList<Moviment> arrayMoviment = new ArrayList<Moviment>();

        if (!movimentService.llistarMoviments().isEmpty() || (sizeMov != 0)) {
            for (int i = 0; i < sizeMov; i++) {
                if ((movimentService.llistarMoviments().get(i).getId_lot().getId_lot() == lot.getId_lot())) {

                    Moviment moviment = movimentService.cercarMoviment(movimentService.llistarMoviments().get(i).getId_moviments());
                    arrayMoviment.add(moviment);
                }
            }
        }

        for (int i = 0; i < arrayMoviment.size(); i++) {
            movimentService.eliminarMoviments(arrayMoviment.get(i).getId_moviments());
        }

        if (this.lot.existsById(lot.getId_lot())) {
            System.out.println("Entra en el if ");
            this.lot.deleteById(lot.getId_lot());
        }
    }

}
