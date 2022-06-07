/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.DAO.MagatzemDAO;
import cat.copernic.candyCo.model.Lot;
import cat.copernic.candyCo.model.Magatzem;
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
public class MagatzemService implements MagatzemServiceInterface {

    @Autowired
    private MagatzemDAO magatzem;

    @Autowired
    private LotServiceInterface lotService;

    @Autowired
    private MovimentServiceInterface movimentService;

    @Override
    @Transactional(readOnly = true)
    public List<Magatzem> llistarMagatzem() {
        return (List<Magatzem>) magatzem.findAll();
    }

    @Override
    public void afegirMagatzem(Magatzem magatzem) {
        this.magatzem.save(magatzem);
    }

    @Override
    @Transactional
    public void eliminarMagatzem(Magatzem magatzem) {
        int sizeLot = lotService.llistarLots().size();
        int sizeMov = movimentService.llistarMoviments().size();

        ArrayList<Lot> arrayLot = new ArrayList<Lot>();
        ArrayList<Moviment> arrayMoviment = new ArrayList<Moviment>();

        if (!lotService.llistarLots().isEmpty() || (sizeLot != 0)) {
            //Aqui entra
            for (int i = 0; i < sizeLot; i++) {
                if ((magatzem.getId_magatzem() == lotService.llistarLots().get(i).getId_magatzem().getId_magatzem())) {

                    if (!movimentService.llistarMoviments().isEmpty() || (sizeMov != 0)) {
                        for (int j = 0; j < sizeMov; j++) {
                            if ((movimentService.llistarMoviments().get(j).getId_lot().getId_lot() == lotService.llistarLots().get(i).getId_lot())) {

                                Moviment moviment = movimentService.cercarMoviment(movimentService.llistarMoviments().get(j).getId_moviments());
                                arrayMoviment.add(moviment);

                                Lot lot = lotService.cercarLot(lotService.llistarLots().get(i).getId_lot());

                                arrayLot.add(lot);

                                
                            }
                        }
                    } else {
                        Lot lot = lotService.cercarLot(lotService.llistarLots().get(i).getId_lot());
                        arrayLot.add(lot);
                    }
                }
            }
        } else {        }

        for (int i = 0; i < arrayMoviment.size(); i++) {
            movimentService.eliminarMoviments(arrayMoviment.get(i).getId_moviments());
        }

        for (int i = 0; i < arrayLot.size(); i++) {
            lotService.eliminarLot(arrayLot.get(i).getId_lot());
        }

        if (this.magatzem.existsById(magatzem.getId_magatzem())) {
                System.out.println("Entra en el if ");
                this.magatzem.deleteById(magatzem.getId_magatzem());
            }
    }

    @Override
    public Magatzem cercarMagatzem(long magatzem) {
        return this.magatzem.findById(magatzem).orElse(null);
    }
}
