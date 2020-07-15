/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Ajax;
import org.omnifaces.util.Faces;
import tg.marcorp.Darche.entities.utilisateurs;
import tg.marcorp.Darche.services.utilisateurServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class connexionMBean implements Serializable {
    @EJB
    private utilisateurServiceBeanLocal utilisateurService;
    private List<utilisateurs> listeUtilisateur;
    private utilisateurs formObjectUtilisateur;
    
    private String login,mdp;
    
    @PostConstruct
    public void chargerElement() {
        this.listeUtilisateur = new ArrayList();
        this.listeUtilisateur = utilisateurService.selectionnerTout();
        login="";
        mdp="";
    }

     public void rafraichir(){
        login = "";
        mdp = "";
    }
     
        public void submit() throws IOException {
        System.out.println("l:" + login);
        System.out.println("p:" + mdp);
        boolean controle1 = false;
        utilisateurs user = new utilisateurs();
        if(login != null && mdp != null){
            controle1 = true;
        }
        if(controle1 == true){
            System.out.println("1");
            try{
                List<utilisateurs> listU = this.utilisateurService.selectionnerTout("login", login);
                for (utilisateurs u : listU) {
                    if(u.getLogin().equals(login)){
                        user = u;
                        break;
                    }
                }
//                int nb=listeUtilisateur.size();
//                System.out.println("nb "+nb);
//                if(nb!=0){
//                    for(int i=0;i<=nb-1;i++){
//                        if(listeUtilisateur.get(i).getLogin().equals(login) &&
//                            listeUtilisateur.get(i).getMotDePasse().equals(mdp)) {
//                            user = listeUtilisateur.get(i);
//                            System.out.println("ok");
//                        }
//                    }
//                }
                String privilege1="ADMIN";
                String privilege2="REVENDEUSE";
                //System.out.println("Utilisateur : " + user.getLogin() + " - " + user.getMotDePasse()); 
                if(user != null){
                     System.out.println("Utilisateur : " + user.getLogin() + " - " + user.getMotDePasse()); 
                    if (user.getMotDePasse().equalsIgnoreCase(mdp) && user.getPrivilege().equalsIgnoreCase(privilege1)) {
                        Faces.redirect("Acceuil.xhtml");
                    } else  if (user.getMotDePasse().equalsIgnoreCase(mdp) && user.getPrivilege().equalsIgnoreCase(privilege2)) {
                        Faces.redirect("Acceuil2.xhtml");
                    }  
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Erreur d'authentification "));
                }
               
            } catch(NullPointerException e){
                System.out.println("null pointer");
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Erreur d'authentification "));
                
            }
             
        }
        if(controle1 == false){
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Erreur d'authentification "));
        }
    } 

    public void deconnexion() throws IOException{
         Faces.redirect("connexion.xhtml");
    }
        
    public utilisateurServiceBeanLocal getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(utilisateurServiceBeanLocal utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public List<utilisateurs> getListeUtilisateur() {
        return listeUtilisateur;
    }

    public void setListeUtilisateur(List<utilisateurs> listeUtilisateur) {
        this.listeUtilisateur = listeUtilisateur;
    }

    public utilisateurs getFormObjectUtilisateur() {
        return formObjectUtilisateur;
    }

    public void setFormObjectUtilisateur(utilisateurs formObjectUtilisateur) {
        this.formObjectUtilisateur = formObjectUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
     
     
     
    
}
