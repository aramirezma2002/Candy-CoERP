/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.copernic.candyCo.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Adrián
 */
@Data
@Entity
@Table(name = "magatzem")
public class Magatzem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_magatzem")
    private long id_magatzem;

    @NotNull
    @Column(name = "nom", nullable = false, length = 30)
    private String nom;

    @NotNull
    @Column(name = "ubicacio", nullable = false, length = 30)
    private String ubicacio;

    @NotNull
    @Column(name = "estat", nullable = false, length = 30)
    private String estat;

    @NotNull
    @Column(name = "valor_total", nullable = false)
    private double valor_total;
}
