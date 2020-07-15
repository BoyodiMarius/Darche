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
@Table(name = "creances")
public class creances extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CREANCES")
    private Long idCreance;
    @Column(name = "PRIX_PAYER")
    private BigDecimal prixPayer;
    @Column(name = "DATE_PAYE")
    @Temporal(TemporalType.DATE)
    private Date datePaye;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_CLIENTS", name="ID_CLIENTS")
    private clients idClients;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_VENTES", name="ID_VENTES")
    private ventes idVentes;

    public creances() {
        
    }

    public creances(Long idCreance, BigDecimal prixPayer, Date datePaye, clients idClients, ventes idVentes) {
        this.idCreance = idCreance;
        this.prixPayer = prixPayer;
        this.datePaye = datePaye;
        this.idClients = idClients;
        this.idVentes = idVentes;
    }

    public Long getIdCreance() {
        return idCreance;
    }

    public void setIdCreance(Long idCreance) {
        this.idCreance = idCreance;
    }

    public BigDecimal getPrixPayer() {
        return prixPayer;
    }

    public void setPrixPayer(BigDecimal prixPayer) {
        this.prixPayer = prixPayer;
    }

    public Date getDatePaye() {
        return datePaye;
    }

    public void setDatePaye(Date datePaye) {
        this.datePaye = datePaye;
    }

    public clients getIdClients() {
        return idClients;
    }

    public void setIdClients(clients idClients) {
        this.idClients = idClients;
    }

    public ventes getIdVentes() {
        return idVentes;
    }

    public void setIdVentes(ventes idVentes) {
        this.idVentes = idVentes;
    }
    
    
    
    
    
    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
