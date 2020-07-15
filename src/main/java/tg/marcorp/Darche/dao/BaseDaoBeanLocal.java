/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import tg.marcorp.Darche.entities.BaseEntity;

/**
 *
 * @author BOYODI Wiyow Marius
 * @param <T>
 * @param <K>
 */
@Local
public interface BaseDaoBeanLocal<T extends BaseEntity, K extends Serializable> {
    
     /**
     * Permet de selectionner une entite en prenant en parametre son Identifiant
     *
     * @param k
     * @return T
     */
    public T selectionner(K k);

    T selectionner(String propriete, Object valeur);

    /**
     * Permet de persister une entite
     *
     * @param valeur
     * @param attribut
     * @return
     */
    public List<T> selectionnerParTableAttribut(Object valeur, String attribut);

    public List<T> selectionnerParDeuxAttributs(String att1, String att2, Object val1, Object val2);

    public List<T> selectionner(String att1, String att2, String att3, Object val1, Object val2);

    public void ajouter(T t);

    public void ajouter(List<T> list);

    /**
     * Permet de modifier une entite en prenant en parametre une autre du meme
     * identifiant
     *
     * @param t
     * @return
     */
    public T modifier(final T t);

    /**
     * Permet de modifier une liste d' entite
     *
     * @param list
     */
    public List<T> modifier(List<T> list);

    public List<T> modifierListe(List<T> list);

    /**
     * Retourne la liste de toutes les entites
     *
     * @return
     */
    public List<T> selectionnerTout();

    /**
     * Retourne la liste de toutes les entites commencants par un mot
     *
     * @param propriete
     * @param mot
     * @return
     */
    public List<T> selectionnerTout(String propriete, String mot);

    /**
     * Supprime logiquement une entite
     *
     * @param t
     */
    public void supprimerLogique(T t);

    /**
     * Supprime logiquement une entite
     *
     * @param t
     */
    public void supprimer(T t);

    /**
     * Supprime logiquement une entite à partir de son identifiant
     *
     * @param k
     */
    public void supprimer(K k);

    public void supprimer(T t, K k);  // à enlever

    //Nbre d'enregistrement
    public int compter(); // changer le type de retour en Long

    /**
     * Supprime tous les objets d'une classe entite
     */
    public void supprimerTout();

    /*
     * Verifier l'existance d'une valeur
     */
    public boolean exists(String value, String attribut);

    public String getExecutedSql();

    public String getDatabaseName();

    public List<Object[]> executerProcedure(String nomProcedure, Map<String, Object> parametres);
}

