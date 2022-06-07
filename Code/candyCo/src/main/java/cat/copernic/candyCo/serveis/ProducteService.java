package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.DAO.ProducteDAO;
import cat.copernic.candyCo.model.Lot;
import cat.copernic.candyCo.model.Moviment;
import cat.copernic.candyCo.model.Producte;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marco
 */
@Service
public class ProducteService implements ProducteServiceInterface {

    @Autowired
    private ProducteDAO producte;

    @Autowired
    private LotServiceInterface lotService;

    @Autowired
    private MovimentServiceInterface movimentService;

    /*LListar productes de la taula producte de la BBDD empresaERP*/
    @Override
    @Transactional(readOnly = true)
    public List<Producte> llistarProductes() {
        return (List<Producte>) producte.findAll();
    }

    /*Afegir el producte passat per paràmetre de la taula producte de la BBDD empresaERP*/
    @Override
    @Transactional
    public void afegirProducte(Producte producte) {
        this.producte.save(producte);
    }

    /*Eliminar el producte per id passat per paràmetre de la taula producte de la BBDD empresaERP*/
    @Override
    @Transactional
    public void eliminarProducte(Producte producte) {
        this.producte.deleteById(producte.getId_producte());
    }

    /*Cercar el producte passat per paràmetre en la taula producte de la BBDD empresaERP*/
    @Override
    @Transactional(readOnly = true)
    public Producte cercarProducte(long producte) {
        return this.producte.findById(producte).orElse(null);
    }

    /*Eliminar el producte per id amb els lots i moviments passat per paràmetre de la taula producte de la BBDD empresaERP*/
    @Override
    @Transactional
    public void eliminarProducteByObject(Producte producte) {
        int sizeLot = lotService.llistarLots().size();
        int sizeMov = movimentService.llistarMoviments().size();

        ArrayList<Lot> arrayLot = new ArrayList<Lot>();
        ArrayList<Moviment> arrayMoviment = new ArrayList<Moviment>();

        if (!lotService.llistarLots().isEmpty() || (sizeLot != 0)) {
            for (int i = 0; i < sizeLot; i++) {
                if ((producte.getId_producte() == lotService.llistarLots().get(i).getId_producte().getId_producte())) {

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
        }

        for (int i = 0; i < arrayMoviment.size(); i++) {
            movimentService.eliminarMoviments(arrayMoviment.get(i).getId_moviments());
        }

        for (int i = 0; i < arrayLot.size(); i++) {
            lotService.eliminarLot(arrayLot.get(i).getId_lot());
        }

        if (this.producte.existsById(producte.getId_producte())) {
            System.out.println("Entra en el if ");
            this.producte.deleteById(producte.getId_producte());
        }
    }
}
