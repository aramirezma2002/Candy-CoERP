/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.copernic.candyCo.serveis;

import cat.copernic.candyCo.model.Admin;
import cat.copernic.candyCo.DAO.AdminDAO;
import cat.copernic.candyCo.model.Moviment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cat.copernic.candyCo.repository.AdminRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marco
 */
@Service("userDetailsService")
@Slf4j
public class AdminService implements UserDetailsService,AdminServiceInterface {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private MovimentServiceInterface movimentService;
    
    @Autowired
    private AdminDAO adminDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String correu) throws UsernameNotFoundException {

        Admin admin = adminRepository.findAdminByCorreu(correu);

        if (admin == null) {
            throw new UsernameNotFoundException(correu);
        }
        var role = new ArrayList<GrantedAuthority>();
        role.add(new SimpleGrantedAuthority(admin.getAdmin_erp()));

        return new org.springframework.security.core.userdetails.User(admin.getCorreu(), admin.getPassword(), role);
    }
    
    
    
    /*LListar admins de la taula admin de la BBDD empresaERP*/
    @Override
    @Transactional(readOnly=true) 
    public List<Admin> llistarAdmins() {
        return (List<Admin>) adminDAO.findAll(); 
    }

    /*Afegir el admin passat per paràmetre de la taula admin de la BBDD empresaERP*/
    @Override
    @Transactional
    public void afegirAdmin(Admin admin) {
        this.adminDAO.save(admin);
    }

    /*Eliminar el admin passat per paràmetre de la taula admin de la BBDD empresaERP*/
    @Override
    @Transactional
    public void eliminarAdmin(Admin admin) {
        this.adminDAO.delete(admin);
    }

    /*Cercar el admin passat per paràmetre en la taula admin de la BBDD empresaERP*/
    @Override
    @Transactional(readOnly=true)
    public Admin cercarAdminByCorreu(String correu) {
        return this.adminDAO.findByMail(correu);
    }

    @Override
    @Transactional(readOnly=true)
    public Admin cercarAdmin(long admin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateAdmin(Admin admin) {
        this.adminDAO.save(admin);
    }

    @Override
    @Transactional
    public void eliminarAdminByObject(Admin admin) {
        admin.setEstat(false);
        this.adminDAO.save(admin);
    }

    @Override
    public Admin cercarAdminByDni(String dni) {
        return this.adminDAO.findByDni(dni);

    }
}
