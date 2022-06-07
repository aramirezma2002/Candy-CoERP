package cat.copernic.candyCo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author marco
 */
@Data
@Entity
@Table(name="tipus_producte")
public class Producte implements Serializable{
    
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@Basic(optional = false)
    @Column(name = "id_producte")
    private long id_producte;

    @NotEmpty
    @Column(name = "nom")
    private String nom;
    
    @NotEmpty
    @Column(name = "tipus")
    private String tipus;

    @NotEmpty
    @Column(name = "descripcio")
    private String descripcio;
    
    @NotEmpty
    @Column(name = "pes")
    private String pes;
    
    @NotNull
    @Column(name = "preu")
    private double preu;
}
