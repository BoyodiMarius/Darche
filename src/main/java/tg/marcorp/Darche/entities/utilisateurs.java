/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Entity
@Table(name = "utilisateurs")
public class utilisateurs extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UTILISATEUR")
    private Long idUtilisateurs;
     @Column(name = "LOGIN")
    private String login;
    @Column(name = "MOT_DE_PASSE")
    private String motDePasse;
    @Column(name = "PRIVILEGE")
    private String privilege;

    public utilisateurs() {
    }

    public utilisateurs(Long idUtilisateurs, String login, String motDePasse, String privilege) {
        this.idUtilisateurs = idUtilisateurs;
        this.login = login;
        this.motDePasse = motDePasse;
        this.privilege = privilege;
    }

    public Long getIdUtilisateurs() {
        return idUtilisateurs;
    }

    public void setIdUtilisateurs(Long idUtilisateurs) {
        this.idUtilisateurs = idUtilisateurs;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
    
    
    

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
