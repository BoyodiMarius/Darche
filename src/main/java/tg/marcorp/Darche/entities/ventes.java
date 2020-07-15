/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "ventes")
public class ventes extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTES")
    private Long idVentes;
    @Column(name = "PRIX_TOTAL")
    private BigDecimal prixTotal;
    @Column(name = "PRIX_PAYER")
    private BigDecimal prixPayer;
    @Column(name = "DATE_VENTE")
    @Temporal(TemporalType.DATE)
    private Date dateVente;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_CLIENTS", name="ID_CLIENTS")
    private clients idClients;

    public ventes() {
    }

    public ventes(Long idVentes, BigDecimal prixTotal, BigDecimal prixPayer, Date dateVente, clients idClients) {
        this.idVentes = idVentes;
        this.prixTotal = prixTotal;
        this.prixPayer = prixPayer;
        this.dateVente = dateVente;
        this.idClients = idClients;
    }

    public Long getIdVentes() {
        return idVentes;
    }

    public void setIdVentes(Long idVentes) {
        this.idVentes = idVentes;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public BigDecimal getPrixPayer() {
        return prixPayer;
    }

    public void setPrixPayer(BigDecimal prixPayer) {
        this.prixPayer = prixPayer;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public clients getIdClients() {
        return idClients;
    }

    public void setIdClients(clients idClients) {
        this.idClients = idClients;
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
