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
@Table(name = "articles")
public class articles extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARTICLES")
    private Long idArticles;
    @Column(name = "LIBELLE_ARTICLES")
    private String libelleArticles;
    @Column(name = "PRIX_ARTICLES")
    private BigDecimal prixArticles;
    @Column(name = "QUANTITE_ARTICLES")
    private BigDecimal quantiteArticle;
    @ManyToOne
    @JoinColumn(referencedColumnName ="ID_CATEGORIE", name="ID_CATEGORIE")
    private categories idCategorie;

    public articles() {
    }

    public articles(Long idArticles, String libelleArticles, BigDecimal prixArticles, BigDecimal quantiteArticle, categories idCategorie) {
        this.idArticles = idArticles;
        this.libelleArticles = libelleArticles;
        this.prixArticles = prixArticles;
        this.quantiteArticle = quantiteArticle;
        this.idCategorie = idCategorie;
    }

    public Long getIdArticles() {
        return idArticles;
    }

    public void setIdArticles(Long idArticles) {
        this.idArticles = idArticles;
    }

    public String getLibelleArticles() {
        return libelleArticles;
    }

    public void setLibelleArticles(String libelleArticles) {
        this.libelleArticles = libelleArticles;
    }

    public BigDecimal getPrixArticles() {
        return prixArticles;
    }

    public void setPrixArticles(BigDecimal prixArticles) {
        this.prixArticles = prixArticles;
    }

    public BigDecimal getQuantiteArticle() {
        return quantiteArticle;
    }

    public void setQuantiteArticle(BigDecimal quantiteArticle) {
        this.quantiteArticle = quantiteArticle;
    }

    public categories getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(categories idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
