package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.model.Producte;
import java.util.List;

/**
 *
 * @author marco
 */
//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface ProducteServiceInterface {

    public List<Producte> llistarProductes(); //Mètode que implementarem per llistar productes

    public void afegirProducte(Producte producte); //Mètode que implementarem per afegir un producte

    public Producte cercarProducte(long producte); //Mètode que implementarem per cercar un producte

    public void eliminarProducte(Producte producte); //Mètode que implementarem per eliminar un producte per id

    public void eliminarProducteByObject(Producte producte); //Metode per eliminar un producte amb els lots
}
