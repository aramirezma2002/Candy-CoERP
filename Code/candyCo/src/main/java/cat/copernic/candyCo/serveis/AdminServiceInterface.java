package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.model.Admin;
import java.util.List;

/**
 *
 * @author marco
 */
//Interface on definirem els mètodes CRUD personalitzats per la nostra aplicació
public interface AdminServiceInterface {

    public List<Admin> llistarAdmins(); //Mètode que implementarem per llistar admins

    public void afegirAdmin(Admin admin); //Mètode que implementarem per afegir un admin

    public void updateAdmin(Admin admin); //Mètode que emplementarem per modificar una admin

    public Admin cercarAdmin(long admin); //Mètode que implementarem per cercar un admin

    public Admin cercarAdminByCorreu(String correu); //Mètode que implementarem per cercar un admin
    
    public Admin cercarAdminByDni(String dni); //Mètode que implementarem per cercar un admin per dni

    public void eliminarAdmin(Admin admin); //Mètode que implementarem per eliminar un admin

    public void eliminarAdminByObject(Admin admin); //Mètode que implementarem per eliminar un admin
}
