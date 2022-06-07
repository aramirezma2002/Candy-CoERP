package cat.copernic.candyCo.DAO;

import cat.copernic.candyCo.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author marco
 */
public interface AdminDAO extends CrudRepository<Admin,Long>{ 
    //Aqui podrem afegir altres mètodes que necessitem i que no estiguin definits a la interface CrudRepository
    @Query("select a from Admin a where a.correu = :correu")//busqueda por un solo campo
    Admin findByMail(@Param("correu") String correu);
    
    @Query("select a from Admin a where a.dni = :dni")//busqueda por un solo campo
    Admin findByDni(@Param("dni") String dni);
}
