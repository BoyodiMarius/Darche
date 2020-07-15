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
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
import tg.marcorp.Darche.entities.articles;
import tg.marcorp.Darche.entities.clients;
import tg.marcorp.Darche.entities.detailsVente;
import tg.marcorp.Darche.entities.ventes;
import tg.marcorp.Darche.services.articlesServiceBeanLocal;
import tg.marcorp.Darche.services.clientsServiceBeanLocal;
import tg.marcorp.Darche.services.detailsVenteServiceBeanLocal;
import tg.marcorp.Darche.services.ventesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class VenteMBean implements Serializable  {
    @EJB
    private articlesServiceBeanLocal articlesService;
    private List<articles> listeArticles, listeControle;
    private articles articles, formObjectArticles, selectObject, modifArticle;
    
    @EJB
    private clientsServiceBeanLocal clientService;
    private List<clients> listeClients;
    private clients client, formObjectClient;
    
    @EJB
    private detailsVenteServiceBeanLocal detailsVenteService;
    private List<detailsVente> listeDetailsVente, ListeControleDetail;
    private detailsVente detailVente, formObjectDetailsVente, selectObjectDetailsVente;
    
    @EJB
    private ventesServiceBeanLocal venteService;
    private List<ventes> listeVente, listeVenteFiltre;
    private ventes vente, formObjectVente;
    
    private int nbt=0;
    private BigDecimal total;
    private Date dateduJour;
    
    public VenteMBean(){
        System.out.println("debut");
        this.formObjectArticles = new articles();
        this.formObjectClient = new clients();
        this.formObjectDetailsVente = new detailsVente();
        this.formObjectVente = new ventes();
        this.listeDetailsVente = new ArrayList();
        
    }
    
      @PostConstruct
    public void chargerElement() {
        System.out.println("charger element");
        this.dateduJour = new Date();
        this.listeArticles = new ArrayList();
        this.listeClients = new ArrayList();
        this.listeVente = new ArrayList();
        listeVenteFiltre = new ArrayList();
        this.listeArticles = articlesService.selectionnerTout();
        this.listeClients = clientService.selectionnerTout();
        this.listeVente = venteService.selectionnerTout();
        total = new BigDecimal(0);
        
        
        int n=listeVente.size();
        if(n!=0){
            for(int i=0;i<=n-1;i++){
                if(dateToString(dateduJour).equals(dateToString(listeVente.get(i).getDateVente()))){
                    listeVenteFiltre.add(listeVente.get(i));
                }
            }
        }
        
    }

    
     public static String dateToString(Date date) {
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
    
    
    public void rowSelect() {
        detailVente = new detailsVente();
        BigDecimal q;
        q = new BigDecimal(1);
        detailVente.setIdArticles(selectObject);
        detailVente.setPrixNormal(selectObject.getPrixArticles());
        detailVente.setPrixVendu(selectObject.getPrixArticles());
        detailVente.setQuantitePayer(q);
        System.out.println("nb "+nbt);
        int n = listeDetailsVente.size();
        boolean veri=false;
        if(n!=0){
            for(int i=0;i<=n-1;i++){
                if(listeDetailsVente.get(i).getIdArticles().getIdArticles().equals(detailVente.getIdArticles().getIdArticles())){
                    veri=true;
                }
            }
        } 
        
        if(veri==true){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erreur!", "Cet article existe déjá dans les articles a vendre"));
        } else {
            BigDecimal aa;
            aa = new BigDecimal(0);
            if(selectObject.getQuantiteArticle().equals(aa)){
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erreur!", "Quantité insuffisante pour la vente"));
            } else {
                listeDetailsVente.add(0, detailVente);
            }
            
            
        }
        
        nbt = nbt+listeDetailsVente.size();
        //listeDetailsVente.add(detailVente);
    }
    
    
    public void validerVente(){
         total = new BigDecimal(0);
        int b;
        b=listeDetailsVente.size();
        int s1= 0;
        if(b!=0){
            boolean ver=false;
            for (int q=0;q<=b-1;q++){
                if(listeDetailsVente.get(q).getQuantitePayer().compareTo(listeDetailsVente.get(q).getIdArticles().getQuantiteArticle())==1){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erreur!", "Quantité de l'article : "+listeDetailsVente.get(q).getIdArticles().getLibelleArticles()+" est insuffisante pour la vente"));
                    ver=true;
                }
            }
                if(ver==false){
                    for (int i=0;i<=b-1;i++){
                        BigDecimal p, conf;
                        conf= new BigDecimal(0);
                        
                        p=listeDetailsVente.get(i).getPrixVendu().multiply(listeDetailsVente.get(i).getQuantitePayer());
                        if(conf.compareTo(p)==1){
                            s1=s1+1;
                             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erreur!", "Veuillez vérifié la quantité et le prix de l'article : "+listeDetailsVente.get(i).getIdArticles().getLibelleArticles()));
                        } else {
                             total = total.add(p);
                        }
                       
                    }
            }
          if(s1>0){
              total=new BigDecimal(0);
          }
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Aucun article selectionné pour la vente"));
        }
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
    }
    
    
     public void ajouterCLient() {
        try {
            boolean veri1=false, veri2=false, veri3=false, veri4=false;
            if(formObjectClient.getNomClient().trim().equals("") || formObjectClient.getNomClient().trim().length()==0 ){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "Le nom du client ne peut être vide"));
                veri1=true;
            }
            if(formObjectClient.getPrenomClient().trim().equals("") || formObjectClient.getPrenomClient().trim().length()==0 ){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "Le prénom du client ne peut être vide"));
                veri2=true;
            }
            
            if(formObjectClient.getContactClient().trim().equals("") || formObjectClient.getContactClient().trim().length()==0){
                veri4=false;
            } 
            if(formObjectClient.getContactClient().trim().length()>0){
                 if(formObjectClient.getContactClient().trim().length() ==8){
                    try {
                       
                        int conversion = Integer.parseInt(formObjectClient.getContactClient().trim());
                        veri4 = false;
                    } catch (NumberFormatException nfe) {
                        veri4 = true;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "Le contact n'est pas valide"));
                    }
                } else {
                      veri4 = true;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "Le contact n'est pas valide"));
                 }
            }
                
            
            int n=listeClients.size();
            if(n!=0){
                for (int i = 0; i <= n - 1; i++) {
                    if(listeClients.get(i).getNomClient().equals(formObjectClient.getNomClient().trim().toUpperCase())
                            && listeClients.get(i).getPrenomClient().equals(formObjectClient.getPrenomClient().trim().toUpperCase()) ) {
                        veri3=true;
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erreur!", "Ce client existe déja"));
                    }
                }
                
                
                if(veri1==false && veri2==false && veri3==false && veri4==false){
                    String nom,prenom;
                    nom=formObjectClient.getNomClient().trim().toUpperCase();
                    prenom=formObjectClient.getPrenomClient().trim().toUpperCase();
                    formObjectClient.setNomClient(nom);
                    formObjectClient.setPrenomClient(prenom);
                    this.clientService.ajouter(formObjectClient);
                    succes();
                    rafraichir();
                    this.formObjectClient= new clients();
                }
                
            } else {
                String nom,prenom;
                nom=formObjectClient.getNomClient().trim().toUpperCase();
                prenom=formObjectClient.getPrenomClient().trim().toUpperCase();
                formObjectClient.setNomClient(nom);
                formObjectClient.setPrenomClient(prenom);
                this.clientService.ajouter(formObjectClient);
                succes();
                rafraichir();
                this.formObjectClient= new clients();
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void enregistrerClient() {
         ajouterCLient();
     }
     
     public void annulerVente(){
         total = new BigDecimal(0);
        this.listeDetailsVente = new ArrayList();
        rafraichir();
    }
     
     public void annulerUneVente(){
         int n=listeDetailsVente.size();
         this.ListeControleDetail = new ArrayList();
         if(n!=0){
             for(int i=0;i<=n-1;i++){
                 if(listeDetailsVente.get(i).getIdArticles().getIdArticles().equals(selectObjectDetailsVente.getIdArticles().getIdArticles())){
                     System.out.println("remove");
                 } else {
                     ListeControleDetail.add(listeDetailsVente.get(i));
                 }
             }
             this.listeDetailsVente = new ArrayList();
             this.listeDetailsVente = ListeControleDetail;
         }
     }
     
    public void vente(){
        
       try {
           formObjectVente.setIdClients(client);
           formObjectVente.setPrixTotal(total);
           BigDecimal b;
           b = new BigDecimal(0);
           boolean ver1=false, ver2=false, ver3=false, ver4=false, ver5=false, ver6=false;
           if(client==null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Veuillez selectionné le client"));
               ver1=true;
           }
           if(total.equals(b)){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Le total de la vente ne peut pas être 0"));
               ver2=true;
           }
           if(formObjectVente.getPrixPayer()==null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Veuillez renseigner le prix payé"));
               ver5=true;
           }
           
           if(ver5==false){
               BigDecimal conf;
               conf = new BigDecimal(0);
               
               if(formObjectVente.getPrixPayer().compareTo(conf)==-1){
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Le prix payé ne peut pas être négatif "));
                   ver6=true;
                   ver3=true;
               }
               
                if(ver6==false){
                    if(total.compareTo(formObjectVente.getPrixPayer())==-1){
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                           "Erreur!", "Le prix payé ne peut pas être supérieur au prix total de la vente"));
                        ver3=true;
                    }
                }
           }
           
           //System.out.println("date "+formObjectVente.getDateVente().);
           if(formObjectVente.getDateVente()==null){
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                       "Erreur!", "Veuillez renseigné la date de vente"));
               ver4=true;
           }
           
           if(ver1==false && ver2==false && ver3==false && ver4==false){
               System.out.println("Vente");
               venteService.ajouter(formObjectVente);
                this.listeVente = new ArrayList();
                this.listeVente = venteService.selectionnerTout();
                int n=listeVente.size();
                vente = listeVente.get(n-1);
                int nb=listeDetailsVente.size();
                if(nb!=0){
                    for(int i=0;i<=nb-1;i++){
                        detailVente = new detailsVente();
                        detailVente.setIdVentes(vente);
                        detailVente.setIdArticles(listeDetailsVente.get(i).getIdArticles());
                        detailVente.setPrixNormal(listeDetailsVente.get(i).getPrixNormal());
                        detailVente.setPrixVendu(listeDetailsVente.get(i).getPrixVendu());
                        detailVente.setQuantitePayer(listeDetailsVente.get(i).getQuantitePayer());
                        BigDecimal reste;
                        reste = listeDetailsVente.get(i).getIdArticles().getQuantiteArticle().subtract(listeDetailsVente.get(i).getQuantitePayer());
                        modifArticle = new articles();
                        modifArticle = listeDetailsVente.get(i).getIdArticles();
                        modifArticle.setQuantiteArticle(reste);
                        detailsVenteService.ajouter(detailVente);
                        articlesService.modifier(modifArticle);
                    }
                }
                refresh();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vente éffectuer"));
        
               
               
           }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }
     
     
     public void succes(){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Enrégistrement éffectuer"));
     }
     
     public void succesModif(){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modification éffectuer"));
     }
     
     public void refresh(){
        this.chargerElement();
        selectObject = new articles();
        selectObjectDetailsVente = new detailsVente();
        formObjectDetailsVente = new detailsVente();
        formObjectVente = new ventes();
        client = new clients();
        annulerVente();
     }
     
      public void rafraichir() {
        this.chargerElement();
    }
    
    public articlesServiceBeanLocal getArticlesService() {
        return articlesService;
    }

    public void setArticlesService(articlesServiceBeanLocal articlesService) {
        this.articlesService = articlesService;
    }

    public List<articles> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(List<articles> listeArticles) {
        this.listeArticles = listeArticles;
    }

    public List<articles> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<articles> listeControle) {
        this.listeControle = listeControle;
    }

    public articles getArticles() {
        return articles;
    }

    public void setArticles(articles articles) {
        this.articles = articles;
    }

    public articles getFormObjectArticles() {
        return formObjectArticles;
    }

    public void setFormObjectArticles(articles formObjectArticles) {
        this.formObjectArticles = formObjectArticles;
    }

    public articles getSelectObject() {
        return selectObject;
    }

    public void setSelectObject(articles selectObject) {
        this.selectObject = selectObject;
    }

    public clientsServiceBeanLocal getClientService() {
        return clientService;
    }

    public void setClientService(clientsServiceBeanLocal clientService) {
        this.clientService = clientService;
    }

    public List<clients> getListeClients() {
        return listeClients;
    }

    public void setListeClients(List<clients> listeClients) {
        this.listeClients = listeClients;
    }

    public clients getClient() {
        return client;
    }

    public void setClient(clients client) {
        this.client = client;
    }

    public clients getFormObjectClient() {
        return formObjectClient;
    }

    public void setFormObjectClient(clients formObjectClient) {
        this.formObjectClient = formObjectClient;
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

    public detailsVente getDetailVente() {
        return detailVente;
    }

    public void setDetailVente(detailsVente detailVente) {
        this.detailVente = detailVente;
    }

    public detailsVente getFormObjectDetailsVente() {
        return formObjectDetailsVente;
    }

    public void setFormObjectDetailsVente(detailsVente formObjectDetailsVente) {
        this.formObjectDetailsVente = formObjectDetailsVente;
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

    public int getNbt() {
        return nbt;
    }

    public void setNbt(int nbt) {
        this.nbt = nbt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public detailsVente getSelectObjectDetailsVente() {
        return selectObjectDetailsVente;
    }

    public void setSelectObjectDetailsVente(detailsVente selectObjectDetailsVente) {
        this.selectObjectDetailsVente = selectObjectDetailsVente;
    }

    public articles getModifArticle() {
        return modifArticle;
    }

    public void setModifArticle(articles modifArticle) {
        this.modifArticle = modifArticle;
    }

    public List<detailsVente> getListeControleDetail() {
        return ListeControleDetail;
    }

    public void setListeControleDetail(List<detailsVente> ListeControleDetail) {
        this.ListeControleDetail = ListeControleDetail;
    }

    public List<ventes> getListeVenteFiltre() {
        return listeVenteFiltre;
    }

    public void setListeVenteFiltre(List<ventes> listeVenteFiltre) {
        this.listeVenteFiltre = listeVenteFiltre;
    }

    public Date getDateduJour() {
        return dateduJour;
    }

    public void setDateduJour(Date dateduJour) {
        this.dateduJour = dateduJour;
    }

    
    
}
