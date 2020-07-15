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
@Table(name = "clients")
public class clients extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTS")
    private Long idClients;
    @Column(name = "NOM_CLIENT")
    private String nomClient;
    @Column(name = "PRENOM_CLIENT")
    private String prenomClient;
    @Column(name = "CONTACT_CLIENT")
    private String contactClient;

    public clients() {
    }

    public clients(Long idClients, String nomClient, String prenomClient, String contactClient) {
        this.idClients = idClients;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.contactClient = contactClient;
    }

    public Long getIdClients() {
        return idClients;
    }

    public void setIdClients(Long idClients) {
        this.idClients = idClients;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getContactClient() {
        return contactClient;
    }

    public void setContactClient(String contactClient) {
        this.contactClient = contactClient;
    }

    @Override
    public String getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
