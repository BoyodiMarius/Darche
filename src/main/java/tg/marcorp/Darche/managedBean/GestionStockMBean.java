/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
import org.omnifaces.util.Ajax;
import tg.marcorp.Darche.entities.articles;
import tg.marcorp.Darche.entities.categories;
import tg.marcorp.Darche.services.articlesServiceBeanLocal;
import tg.marcorp.Darche.services.categoriesServiceBeanLocal;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class GestionStockMBean implements Serializable {
    
    @EJB
    private categoriesServiceBeanLocal categoriesService;
    private List<categories> listeCategories;
    private categories categories, formObjectCategories;
    
    @EJB
    private articlesServiceBeanLocal articlesService;
    private List<articles> listeArticles, listeControle;
    private articles articles, formObjectArticles, selectObject;
    
    private categories var;
    private long test;
    
    public GestionStockMBean() {
        this.formObjectCategories = new categories();
        this.formObjectArticles = new articles();
        this.categories = new categories();
        this.var = new categories();
        
    }
    
    @PostConstruct
    public void chargerElement() {
	this.listeArticles = new ArrayList();
        this.listeCategories = new ArrayList();
        this.listeArticles = this.articlesService.selectionnerTout();
        this.listeCategories = this.categoriesService.selectionnerTout();
        //this.categories = new categories();
        
    }

     public void test(){
        System.out.println("test");
    }
    
    public void rowSelect() {
        formObjectArticles = selectObject;
        test = selectObject.getIdCategorie().getIdCategorie();
    }
    
    public void ajouter() {
        try {
            String des;
            categories = new categories();
            categories = categoriesService.selectionner(test);
            formObjectArticles.setIdCategorie(categories);
            des=formObjectArticles.getLibelleArticles().trim().toUpperCase();
            formObjectArticles.setLibelleArticles(des);
            this.articlesService.ajouter(formObjectArticles);
            succes();
            rafraichir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modifier() {
        try {
            //selectObject = formObjectArticles;
            categories = categoriesService.selectionner(test);
            selectObject.setIdCategorie(categories);
            selectObject.setLibelleArticles(formObjectArticles.getLibelleArticles().trim().toUpperCase());
            selectObject.setPrixArticles(formObjectArticles.getPrixArticles());
            selectObject.setQuantiteArticle(formObjectArticles.getQuantiteArticle());
            System.out.println("cat "+selectObject.getIdCategorie().getLibelleCategorie());
            System.out.println("des "+selectObject.getLibelleArticles());
            System.out.println("pri "+selectObject.getPrixArticles());
            System.out.println("qua "+selectObject.getQuantiteArticle());
            this.articlesService.modifier(selectObject);
            succesModif();
            rafraichir();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
     public void ajouterCat() {
        try {
            String lib;
            lib = formObjectCategories.getLibelleCategorie().trim().toUpperCase();
            formObjectCategories.setLibelleCategorie(lib);
            this.categoriesService.ajouter(formObjectCategories);
            succes();
            rafraichir();
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
     
     public void rafraichir() {
        this.chargerElement();
        formObjectArticles = new articles();
         categories = new categories();
         test=0;
         formObjectCategories = new categories();
    }

     
     public void enregistrer() {
         boolean veri1=false, veri2=false, veri3=false;
         if(test==0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez choisir une catégorie"));
             veri1=true;
         }
         if(formObjectArticles.getLibelleArticles().equals("") || formObjectArticles.getLibelleArticles().length() == 0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez remplir la désignation de l'article"));
             veri2=true;
         }
         
         this.listeControle = new ArrayList();
         //listeControle = listeArticles;
         if(selectObject != null){
             int n=listeArticles.size();
             if(n!=0){
                  for (int i = 0; i <= n - 1; i++) {
                       if(listeArticles.get(i).getLibelleArticles().equals(selectObject.getLibelleArticles().trim().toUpperCase()) &&
                    listeArticles.get(i).getIdCategorie().getIdCategorie().equals(test)){
                           System.out.println("");
                    } else {
                           listeControle.add(listeArticles.get(i));
                       }
                  }
             }
         }
         
         int nb= listeControle.size();
         if(nb!=0){
             for (int i = 0; i <= nb - 1; i++) {
                 if(listeControle.get(i).getLibelleArticles().equals(formObjectArticles.getLibelleArticles().trim().toUpperCase()) &&
                    listeControle.get(i).getIdCategorie().getIdCategorie().equals(test)){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Cette désignation d'article existe déjà pour la catégorie choisi"));
                    veri3=true;
                 }
             }
         }
         
         if(veri1==false && veri2 == false && veri3 == false){
            if(selectObject!=null){
                modifier();
            } else {
                ajouter();
            }
            rafraichir();
         }
         
        
     }
     
     public void enregistrerCat() {
         boolean veri1=false, veri2=false;
         if(formObjectCategories.getLibelleCategorie().equals("") || formObjectCategories.getLibelleCategorie().length() == 0){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez renseigner la désignation de la catégorie"));
             veri1=true;
         }
         
         int nb2;
         nb2 = listeCategories.size();
         if(nb2 !=0 ){
             for (int i = 0; i <= nb2 - 1; i++) {
                 if(listeCategories.get(i).getLibelleCategorie().equals(formObjectCategories.getLibelleCategorie().trim().toUpperCase())){
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Cette désignation de catégorie existe déjà pour la catégorie choisi"));
                    veri2=true;
                 }
             }
         }
         
         if(veri1==false && veri2==false){
            ajouterCat();
            rafraichir();
         }
         
     }
     
     
    
    
    public categoriesServiceBeanLocal getCategoriesService() {
        return categoriesService;
    }

    public void setCategoriesService(categoriesServiceBeanLocal categoriesService) {
        this.categoriesService = categoriesService;
    }

    public List<categories> getListeCategories() {
        return listeCategories;
    }

    public void setListeCategories(List<categories> listeCategories) {
        this.listeCategories = listeCategories;
    }

    public categories getCategories() {
        return categories;
    }

    public void setCategories(categories categories) {
        this.categories = categories;
    }

    public categories getFormObjectCategories() {
        return formObjectCategories;
    }

    public void setFormObjectCategories(categories formObjectCategories) {
        this.formObjectCategories = formObjectCategories;
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

    public categories getVar() {
        return var;
    }

    public void setVar(categories var) {
        this.var = var;
    }

    public long getTest() {
        return test;
    }

    public void setTest(long test) {
        this.test = test;
    }

    public List<articles> getListeControle() {
        return listeControle;
    }

    public void setListeControle(List<articles> listeControle) {
        this.listeControle = listeControle;
    }
    
    
    
    
    
    
}
