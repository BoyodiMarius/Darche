/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author BOYODI Wiyow Marius
 * @param <K>
 * @param <T>
 */
public interface BaseServiceBeanLocal<T, K extends Serializable> {
    
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
     * @param t
     *
     *
     */
    int compter();

    public List<T> selectionnerParTableAttribut(Object valeur, String attribut);

    public List<T> selectionnerParDeuxAttributs(String att1, String att2, Object val1, Object val2);

    List<T> selectionner(String att1, String att2, String att3, Object val1, Object val2);

    List<T> selectionnerTout(String propriete, String mot);

    public void ajouter(T t);

    /**
     * Persister une liste d'une entité
     *
     * @param list
     */
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

    public void modifierSet(Set<T> list);

    List<T> modifierListe(List<T> list);

    /**
     * Retourne la liste de toutes les entites
     *
     * @return
     */
    public List<T> selectionnerTout();

    /**
     * Supprime logiquement une entite
     *
     * @param t
     * @return
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

    public void supprimer(T t, K k);

    /**
     * Supprime tous les objets d'une classe entite
     */
    public void supprimerTout();

    /*
     * Supprime une liste d'objets d'une classe entité
     * 
     * @param list :: liste d'objets
     */
    public void delete(List<T> list);


    /*
     * Verifier l'existance d'une valeur
     * @param value
     * @param attribut
     */
    public boolean exists(String value, String attribut);

    public String getExecutedSql();

    public String getDatabaseName();

    public String formater(int pos, String value);
    /*Liste parametré" pour les Etats*/
}

