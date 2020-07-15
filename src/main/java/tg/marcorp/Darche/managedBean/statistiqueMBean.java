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
import org.omnifaces.util.Ajax;
import tg.marcorp.Darche.entities.creances;
import tg.marcorp.Darche.entities.detailsVente;
import tg.marcorp.Darche.entities.ventes;
import tg.marcorp.Darche.services.creancesServiceBeanLocal;
import tg.marcorp.Darche.services.detailsVenteServiceBeanLocal;
import tg.marcorp.Darche.services.ventesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class statistiqueMBean implements Serializable {
    @EJB
    private ventesServiceBeanLocal venteService;
    private List<ventes> listeVente, listeVenteFiltre;
    private ventes vente, selectObjectVente;
    
    @EJB
    private creancesServiceBeanLocal creanceService;
    private List<creances> listeCreance, ListeCreanceFiltre;
    
    @EJB
    private detailsVenteServiceBeanLocal detailsVenteService;
    private List<detailsVente> listeDetailsVente, listeDetailsVenteFiltre;
    
    private Date dateStat;
    private BigDecimal totalVente, totalCreance, totalVentePercue, totalCreancePercue, ChiffreDAffaire;
    
    
    public statistiqueMBean(){
        selectObjectVente = new ventes();
    }
    
     @PostConstruct
    public void chargerElement() {
       totalVente = new BigDecimal(0);
       totalCreance = new BigDecimal(0);
       totalVentePercue = new BigDecimal(0);
       totalCreancePercue = new BigDecimal(0);
       ChiffreDAffaire = new BigDecimal(0);
       
       this.listeVente = new ArrayList();
       this.listeDetailsVente = new ArrayList();
       this.listeCreance = new ArrayList();
       
       this.listeVente = venteService.selectionnerTout();
       this.listeDetailsVente = detailsVenteService.selectionnerTout();
       this.listeCreance = creanceService.selectionnerTout();
    }
    
    public  BigDecimal reste(BigDecimal total, BigDecimal paye){
        BigDecimal re;
        re=total.subtract(paye);
        return (re);
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
    
    public void etablisementStatistique(){
       totalVente = new BigDecimal(0);
       totalCreance = new BigDecimal(0);
       totalVentePercue = new BigDecimal(0);
       totalCreancePercue = new BigDecimal(0);
       ChiffreDAffaire = new BigDecimal(0);
       
       this.listeVenteFiltre = new ArrayList();
       this.ListeCreanceFiltre = new ArrayList();
       
       if(dateStat==null){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "Veuillez renseigner la date"));
       } else {
           
        int nb1, nb2;
        nb1=listeVente.size();
        nb2=listeCreance.size();
        
        if(nb1!=0){
            for(int i=0;i<= nb1 - 1;i++){
                if(dateToString(listeVente.get(i).getDateVente()).equals(dateToString(dateStat))){
                    listeVenteFiltre.add(listeVente.get(i));
                    totalVente = totalVente.add(listeVente.get(i).getPrixTotal());
                    totalCreance = totalCreance.add(reste(listeVente.get(i).getPrixTotal(), listeVente.get(i).getPrixPayer()));
                    totalVentePercue = totalVentePercue.add(listeVente.get(i).getPrixPayer());
                }
            }
        }
        
        if(nb2!=0){
            for(int p=0;p<= nb2 - 1;p++){
                if(dateToString(listeCreance.get(p).getDatePaye()).equals(dateToString(dateStat))){
                    ListeCreanceFiltre.add(listeCreance.get(p));
                    totalCreancePercue = totalCreancePercue.add(listeCreance.get(p).getPrixPayer());
                }
            }
        }
          
        ChiffreDAffaire = totalVentePercue.add(totalCreancePercue);
        
       }
    }
    
    public void rowSelect(){
        vente = new ventes();
        this.listeDetailsVenteFiltre = new ArrayList();
        vente = selectObjectVente;
        int n=listeDetailsVente.size();
        if(n!=0){
            for(int i=0;i<=n-1;i++){
                if(listeDetailsVente.get(i).getIdVentes().getIdVentes().equals(vente.getIdVentes())){
                    listeDetailsVenteFiltre.add(listeDetailsVente.get(i));
                }
            }
        }
        Ajax.oncomplete("PF('detailVenteDlg').show()");
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

    public ventes getSelectObjectVente() {
        return selectObjectVente;
    }

    public void setSelectObjectVente(ventes selectObjectVente) {
        this.selectObjectVente = selectObjectVente;
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

    public detailsVenteServiceBeanLocal getDetailsVenteService() {
        return detailsVenteService;
    }

    public void setDetailsVenteService(detailsVenteServiceBeanLocal detailsVenteService) {
        this.detailsVenteService = detailsVenteService;
    }

    public List<detailsVente> getListeDetailsVente() {
        return listeDetailsVente;
    }

    public void setListeDetailsVente(List<detailsVente> listeDetailsVente) {
        this.listeDetailsVente = listeDetailsVente;
    }

    public List<detailsVente> getListeDetailsVenteFiltre() {
        return listeDetailsVenteFiltre;
    }

    public void setListeDetailsVenteFiltre(List<detailsVente> listeDetailsVenteFiltre) {
        this.listeDetailsVenteFiltre = listeDetailsVenteFiltre;
    }

    public Date getDateStat() {
        return dateStat;
    }

    public void setDateStat(Date dateStat) {
        this.dateStat = dateStat;
    }

    public BigDecimal getTotalVente() {
        return totalVente;
    }

    public void setTotalVente(BigDecimal totalVente) {
        this.totalVente = totalVente;
    }

    public BigDecimal getTotalCreance() {
        return totalCreance;
    }

    public void setTotalCreance(BigDecimal totalCreance) {
        this.totalCreance = totalCreance;
    }

    public BigDecimal getTotalVentePercue() {
        return totalVentePercue;
    }

    public void setTotalVentePercue(BigDecimal totalVentePercue) {
        this.totalVentePercue = totalVentePercue;
    }

    public BigDecimal getTotalCreancePercue() {
        return totalCreancePercue;
    }

    public void setTotalCreancePercue(BigDecimal totalCreancePercue) {
        this.totalCreancePercue = totalCreancePercue;
    }

    public BigDecimal getChiffreDAffaire() {
        return ChiffreDAffaire;
    }

    public void setChiffreDAffaire(BigDecimal ChiffreDAffaire) {
        this.ChiffreDAffaire = ChiffreDAffaire;
    }
    
    
    
}
