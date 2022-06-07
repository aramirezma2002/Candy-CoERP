package cat.copernic.candyCo.model;

import java.io.FileInputStream;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author marco
 */
@Data
@Entity
@Table(name = "administrador")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //L'atribut idUsuari és la clau primària de la BBDD
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //Generació autonumèrica de l'id
    //@Basic(optional = false)
    @Column(name = "dni")
    private String dni;

    @NotEmpty
    @Column(name = "nom")
    private String nom;

    @NotEmpty
    @Column(name = "cognom")
    private String cognom;

    @NotEmpty
    @Column(name = "correu")
    private String correu;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "admin_erp")
    private String admin_erp;

    @Lob
    @Column(name = "imatge_usuari")
    private String imatge_usuari;

    @NotNull
    @Column(name = "estat")
    private boolean estat;
}
