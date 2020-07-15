/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import tg.marcorp.Darche.entities.creances;
import tg.marcorp.Darche.entities.ventes;
import tg.marcorp.Darche.services.creancesServiceBeanLocal;
import tg.marcorp.Darche.services.ventesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class creanceMBean implements Serializable {
  @EJB
  private ventesServiceBeanLocal venteService;
  private List<ventes> listeVente, listeVenteFiltre;
  private ventes vente, formObjectVente, selectObjectVente;
  
  @EJB
  private creancesServiceBeanLocal creanceService;
  private List<creances> listeCreance, ListeCreanceFiltre;
  private creances formObjectCreance;
  
  private String dateVente;
  private BigDecimal resteAffiche, creanceTotal, creancePaye;
  
  public creanceMBean(){
      this.formObjectCreance = new creances();
      this.formObjectVente = new ventes();
  }
  
  @PostConstruct
    public void chargerElement() {
        //reste = new BigDecimal(0);
        this.listeCreance = new ArrayList();
        this.ListeCreanceFiltre = new ArrayList();
        this.listeVente = new ArrayList();
        this.listeVenteFiltre = new ArrayList();
        this.listeCreance = creanceService.selectionnerTout();
        this.listeVente = venteService.selectionnerTout();
        int nb= this.listeVente.size();
        if(nb!=0){
            for(int i=0;i <=nb-1;i++){
                if(listeVente.get(i).getPrixPayer().compareTo(listeVente.get(i).getPrixTotal())==-1){
                    listeVenteFiltre.add(listeVente.get(i));
                }
            }
        }
        resteAffiche = new BigDecimal(0);
         creanceTotal = new BigDecimal(0);
         creancePaye = new BigDecimal(0);
    }

    public  BigDecimal reste(BigDecimal total, BigDecimal paye){
        BigDecimal re;
        re=total.subtract(paye);
        return (re);
    }
    
    public void rowSelect(){
        creanceTotal = new BigDecimal(0);
         creancePaye = new BigDecimal(0);
        formObjectVente = selectObjectVente;
        resteAffiche = reste(selectObjectVente.getPrixTotal(), selectObjectVente.getPrixPayer());
        dateVente = dateToString(selectObjectVente.getDateVente());
        ListeCreanceFiltre = new ArrayList();
        int nb = listeCreance.size();
        if(nb!=0){
            for(int i=0; i<=nb-1;i++){
                if(listeCreance.get(i).getIdVentes().getIdVentes().equals(formObjectVente.getIdVentes())){
                    ListeCreanceFiltre.add(listeCreance.get(i));
                   creanceTotal = creanceTotal.add(listeCreance.get(i).getPrixPayer());
                   creancePaye = creancePaye.add(listeCreance.get(i).getPrixPayer());
                }
            }
        }
        creanceTotal = creanceTotal.add(reste(formObjectVente.getPrixTotal(), formObjectVente.getPrixPayer()));
        System.out.println("total "+creanceTotal);
        System.out.println("paye "+creancePaye);
    }
    
     public  String dateToString(Date date) {
        String jour = "" + date.getDate();
        String mois = "" + (date.getMonth() + 1);
        String annee = "" + (date.getYear() + 1900);
        if (jour.length() == 1) {
            jour = "0" + jour;
        }
        if (mois.length() == 1) {
            mois = "0" + mois;
        }

        return (jour + "/" + mois + "/" + annee);
    }
    
     public void validerCreance(){
         try {
             boolean veri1=false, veri2=false, veri3=false, veri4=false, veri5=false, veri6=false;
             BigDecimal cor;
             cor = new BigDecimal(0);
             
             if(selectObjectVente == null){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "Veuillez selectionner une créance"));
                 veri1=true;
             }
             
             if(formObjectCreance.getDatePaye()==null){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "Veuillez renseigner la date"));
                 veri2=true;
             }
             
             if(veri1==false && veri2==false){
                 if(formObjectCreance.getDatePaye().compareTo(selectObjectVente.getDateVente())==-1){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "La date de payement de la créance ne peut pas être inférieur á la date de vente "));
                 veri3=true;
                 }
             }
             
             if(formObjectCreance.getPrixPayer()==null){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Veuillez renseigner le montant payé"));
                 veri6=true;
             }
             
                if(veri6==false){
                    if(formObjectCreance.getPrixPayer().compareTo(cor)==-1){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                           "Erreur!", "Le montant ne peut pas être négatif "));
                    veri4=true;
                }

                if(veri4==false && veri1==false){
                    if(formObjectCreance.getPrixPayer().compareTo(reste(selectObjectVente.getPrixTotal(), selectObjectVente.getPrixPayer()))==1){
                       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Le montant ne peut pas être supérieur au reste de la créance "));
                       veri5=true;
                    }
                }
             }
             
             
             
             if(veri1==false && veri2==false && veri3==false && veri4==false && veri5==false && veri6==false){
                formObjectCreance.setIdClients(formObjectVente.getIdClients());
                formObjectCreance.setIdVentes(formObjectVente);
                creanceService.ajouter(formObjectCreance);

                vente=new ventes();
                vente = formObjectVente;
                BigDecimal newPrixPaye= vente.getPrixPayer().add(formObjectCreance.getPrixPayer());
                vente.setPrixPayer(newPrixPaye);
                venteService.modifier(vente);
                rafraichir();
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", 
                         "Enrégistrement de la créance éffectuer"));
             }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
     public void rafraichir(){
         chargerElement();
         vente=new ventes();
         formObjectVente =new ventes();
         selectObjectVente = new ventes();
         formObjectCreance = new creances();
         ListeCreanceFiltre = new ArrayList();
         dateVente = "";
     }
     
     
    public ventesServiceBeanLocal getVenteService() {
        return venteService;
    }

    public void setVenteService(ventesServiceBeanLocal venteService) {
        this.venteService = venteService;
    }

    public List<ventes> getListeVente() {
        return listeVente;
    }

    public void setListeVente(List<ventes> listeVente) {
        this.listeVente = listeVente;
    }

    public List<ventes> getListeVenteFiltre() {
        return listeVenteFiltre;
    }

    public void setListeVenteFiltre(List<ventes> listeVenteFiltre) {
        this.listeVenteFiltre = listeVenteFiltre;
    }

    public ventes getVente() {
        return vente;
    }

    public void setVente(ventes vente) {
        this.vente = vente;
    }

    public ventes getFormObjectVente() {
        return formObjectVente;
    }

    public void setFormObjectVente(ventes formObjectVente) {
        this.formObjectVente = formObjectVente;
    }

    public creancesServiceBeanLocal getCreanceService() {
        return creanceService;
    }

    public void setCreanceService(creancesServiceBeanLocal creanceService) {
        this.creanceService = creanceService;
    }

    public List<creances> getListeCreance() {
        return listeCreance;
    }

    public void setListeCreance(List<creances> listeCreance) {
        this.listeCreance = listeCreance;
    }

    public List<creances> getListeCreanceFiltre() {
        return ListeCreanceFiltre;
    }

    public void setListeCreanceFiltre(List<creances> ListeCreanceFiltre) {
        this.ListeCreanceFiltre = ListeCreanceFiltre;
    }

    public creances getFormObjectCreance() {
        return formObjectCreance;
    }

    public void setFormObjectCreance(creances formObjectCreance) {
        this.formObjectCreance = formObjectCreance;
    }

    public ventes getSelectObjectVente() {
        return selectObjectVente;
    }

    public void setSelectObjectVente(ventes selectObjectVente) {
        this.selectObjectVente = selectObjectVente;
    }

    public String getDateVente() {
        return dateVente;
    }

    public void setDateVente(String dateVente) {
        this.dateVente = dateVente;
    }

    public BigDecimal getResteAffiche() {
        return resteAffiche;
    }

    public void setResteAffiche(BigDecimal resteAffiche) {
        this.resteAffiche = resteAffiche;
    }

    public BigDecimal getCreanceTotal() {
        return creanceTotal;
    }

    public void setCreanceTotal(BigDecimal creanceTotal) {
        this.creanceTotal = creanceTotal;
    }

    public BigDecimal getCreancePaye() {
        return creancePaye;
    }

    public void setCreancePaye(BigDecimal creancePaye) {
        this.creancePaye = creancePaye;
    }

    
    
    
    
}
