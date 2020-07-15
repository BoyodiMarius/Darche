/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "detailsVente")
public class detailsVente extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETAILS_VENTE")
    private Long idDetailsVentes;
    @Column(name = "PRIX_NORMAL")
    private BigDecimal prixNormal;
    @Column(name = "PRIX_VENDU")
    private BigDecimal prixVendu;
    @Column(name = "QUANTITE_PAYER")
    private BigDecimal quantitePayer;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_VENTES", name="ID_VENTES")
    private ventes idVentes;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_ARTICLES", name="ID_ARTICLES")
    private articles idArticles;

    public detailsVente() {
    }

    public detailsVente(Long idDetailsVentes, BigDecimal prixNormal, BigDecimal prixVendu, BigDecimal quantitePayer, ventes idVentes, articles idArticles) {
        this.idDetailsVentes = idDetailsVentes;
        this.prixNormal = prixNormal;
        this.prixVendu = prixVendu;
        this.quantitePayer = quantitePayer;
        this.idVentes = idVentes;
        this.idArticles = idArticles;
    }

    public Long getIdDetailsVentes() {
        return idDetailsVentes;
    }

    public void setIdDetailsVentes(Long idDetailsVentes) {
        this.idDetailsVentes = idDetailsVentes;
    }

    public BigDecimal getPrixNormal() {
        return prixNormal;
    }

    public void setPrixNormal(BigDecimal prixNormal) {
        this.prixNormal = prixNormal;
    }

    public BigDecimal getPrixVendu() {
        return prixVendu;
    }

    public void setPrixVendu(BigDecimal prixVendu) {
        this.prixVendu = prixVendu;
    }

    public BigDecimal getQuantitePayer() {
        return quantitePayer;
    }

    public void setQuantitePayer(BigDecimal quantitePayer) {
        this.quantitePayer = quantitePayer;
    }

    public ventes getIdVentes() {
        return idVentes;
    }

    public void setIdVentes(ventes idVentes) {
        this.idVentes = idVentes;
    }

    public articles getIdArticles() {
        return idArticles;
    }

    public void setIdArticles(articles idArticles) {
        this.idArticles = idArticles;
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
