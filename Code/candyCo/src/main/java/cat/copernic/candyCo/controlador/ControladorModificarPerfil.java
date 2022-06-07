/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.controlador;

import cat.copernic.candyCo.DAO.AdminDAO;
import cat.copernic.candyCo.model.Admin;
import cat.copernic.candyCo.repository.PasswordEncrypt;
import cat.copernic.candyCo.serveis.AdminServiceInterface;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Adrián
 */
@Controller
@Slf4j
public class ControladorModificarPerfil {

    @Autowired
    private AdminServiceInterface adminService;

    @Autowired
    private AdminDAO adminDAO;

    //Funció per carregar la pantalla de perfil
    @GetMapping("/perfil")
    public String perfil(Model model, @AuthenticationPrincipal User currentAdmin) {

        // Agafem l'usuari que ha iniciat sessió
        Admin adminCurrent = adminService.cercarAdminByCorreu(currentAdmin.getUsername());

        //Affegim al model l'usuari actual
        model.addAttribute("user", adminCurrent);

        //Si l'usuari actual no té foto de perfil, s'assignará una foto predeterminada. Si l'usuari si que té foto, es pasará aquesta foto.
        if (adminCurrent.getImatge_usuari() == null || adminCurrent.getImatge_usuari().equals("")) {
            String path = "images/userImages/null.png";
            model.addAttribute("imgUser", path);
        } else {
            String path = "images/userImages/" + adminCurrent.getImatge_usuari();
            model.addAttribute("imgUser", path);
        }

        //Retornem la pantalla de perdil
        return "perfil";
    }

    //Funció per actualitzar les noves dades del usuari. Agafem els camps de text de la vista amb el @RequestParam
    @PostMapping("/perfil/guardar/{dni}")
    public String save(@AuthenticationPrincipal User currentAdmin,
            @RequestParam(value = "nomUser") String nom,
            @RequestParam(value = "cognomUser") String cognom,
            @RequestParam(value = "correuUser") String correu,
            @RequestParam(value = "contrassenyaUser") String contrassenya,
            @RequestParam(value = "repeteixContrassenya") String repeteixContrassenya,
            @RequestParam(value = "fotoUser") MultipartFile foto) {

        // Agafem l'usuari que ha iniciat sessió
        Admin admin = adminService.cercarAdminByCorreu(currentAdmin.getUsername());

        //Si el nom no es buit i no es igual al que ja hi es a la base de dades, s'actualitzará al nom que hi ha en el camp de text a la vista.
        if (!nom.isEmpty()) {
            if (!nom.equals(admin.getNom())) {
                admin.setNom(nom);
            }
        }

        //Si el cognom no es buit i no es igual al que ja hi es a la base de dades, s'actualitzará al cognom que hi ha en el camp de text a la vista.
        if (!cognom.isEmpty()) {
            if (!cognom.equals(admin.getCognom())) {
                admin.setCognom(cognom);
            }
        }

        //Si el correu no es buit i no es igual al que ja hi es a la base de dades, s'actualitzará al correu que hi ha en el camp de text a la vista.
        if (!correu.isEmpty()) {
            if (!correu.equals(admin.getCorreu())) {
                admin.setCorreu(correu);
            }
        }

        //Si la contrassenya no es buit, no es igual al que ja hi es a la base de dades i els dos camp de la contrassenya son iguals, s'actualitzará a la contrassenya que hi ha en el camp de text a la vista.
        if (!contrassenya.isEmpty() && !repeteixContrassenya.isEmpty()) {
            if (!PasswordEncrypt.passwordDecrypter(contrassenya, admin.getPassword())) {
                if (contrassenya.equals(repeteixContrassenya)) {
                    admin.setPassword(PasswordEncrypt.passwordEncrypter(contrassenya));
                }
            }
        }

        if (!correu.isEmpty()) {
            if (!correu.equals(admin.getCorreu())) {
                admin.setCorreu(correu);
            }
        }

        //Si en el selector de arxius hi ha algun arxiu seleccionat, s'esborrará l'imatge del resources, es guardará la nova i es guardará el nom del arxiu en la base de dades.
        if (!foto.isEmpty()) {
            try {
                String path = "src/main/resources/static/images/userImages/";
                File imatges = new File(path);
                if (!imatges.exists()) {
                    imatges.mkdir();
                }
                String docName = admin.getDni() + foto.getContentType().replace("/", ".");
                InputStream inputStream = foto.getInputStream();
                File imatge = new File(path + docName);

                if (docName.replace(".png", "").replace(".jpg", "").replace(".jpeg", "").equals(admin.getImatge_usuari().replace(".png", "").replace(".jpg", "").replace(".jpeg", ""))) {
                    File del = new File(path + admin.getImatge_usuari());
                    del.delete();
                }
                Files.copy(inputStream, imatge.toPath(), StandardCopyOption.REPLACE_EXISTING);

                admin.setImatge_usuari(docName);
            } catch (Exception e) {
            }
        }

        //Guarden l'admin que hem anat creant durant la funció.
        adminDAO.save(admin);

        //Retornem a la pantalla de perfil.
        return "redirect:/perfil";
    }
}
