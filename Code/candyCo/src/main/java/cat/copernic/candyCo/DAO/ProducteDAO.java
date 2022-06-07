package cat.copernic.candyCo.DAO;

import cat.copernic.candyCo.model.Producte;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author marco
 */
public interface ProducteDAO extends CrudRepository<Producte,Long>{ 
    //Aqui podrem afegir altres mètodes que necessitem i que no estiguin definits a la interface CrudRepository
}
