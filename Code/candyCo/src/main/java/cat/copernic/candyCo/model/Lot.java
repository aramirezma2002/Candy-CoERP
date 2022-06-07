package cat.copernic.candyCo.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Adrián
 */
@Data
@Entity
@Table(name = "lot")
public class Lot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lot")
    private long id_lot;

    @NotNull
    @Column(name = "data_creacio")
    private Date data_creacio;

    @NotNull
    @Column(name = "data_ultima_modificacio")
    private Date data_ultima_modificacio;

    @Column(name = "data_finalitzacio")
    private Date data_finalitzacio;

    @NotNull
    @Column(name = "data_caducitat")
    private Date data_caducitat;

    @NotNull
    @Column(name = "quantitat")
    private int quantitat;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Admin autor;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_magatzem")
    private Magatzem id_magatzem;

    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producte")
    private Producte id_producte;
}
