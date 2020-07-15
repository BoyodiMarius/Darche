/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import tg.marcorp.Darche.dao.BaseDaoBeanLocal;
import tg.marcorp.Darche.entities.BaseEntity;

/**
 *
 * @author BOYODI Wiyow Marius
 * @param <T>
 * @param <K>
 */
public abstract class BaseServiceBean<T extends BaseEntity, K extends Serializable> implements BaseServiceBeanLocal<T, K> {
   
     protected abstract BaseDaoBeanLocal<T, K> getDao();

    @Override
    public void ajouter(T t) {
        this.getDao().ajouter(t);
    }

    @Override
    public void ajouter(List<T> list) {

        for (T t : list) {
            this.getDao().ajouter(t);
        }
    }

    @Override
    public List<T> modifier(List<T> list) {
        List<T> liste = new ArrayList<T>();
        for (T t : list) {
            this.getDao().modifier(t);
            liste.add(t);
        }
        return liste;
    }

    @Override
    public void modifierSet(Set<T> list) {
        for (T t : list) {
            this.getDao().modifier(t);

        }

    }

    @Override
    public List<T> modifierListe(List<T> list) {
        return this.getDao().modifierListe(list);
    }

    @Override
    public T modifier(T t) {
        return this.getDao().modifier(t);
    }

    @Override
    public List<T> selectionnerTout() {
        return this.getDao().selectionnerTout();
    }

    @Override
    public T selectionner(K k) {
        return this.getDao().selectionner(k);
    }

    @Override
    public List<T> selectionnerParTableAttribut(Object valeur, String attribut) {
        return this.getDao().selectionnerParTableAttribut(valeur, attribut);
    }

    @Override
    public List<T> selectionnerParDeuxAttributs(String att1, String att2, Object val1, Object val2) {
        return this.getDao().selectionnerParDeuxAttributs(att1, att2, val1, val2);
    }

    @Override
    public List<T> selectionner(String att1, String att2, String att3, Object val1, Object val2) {
        return this.getDao().selectionner(att1, att2, att3, val1, val2);
    }

    @Override
    public void supprimer(T t) {
        this.getDao().supprimer(t);
    }

    @Override
    public void supprimer(K k) {
        this.getDao().supprimer(k);
    }

    @Override
    public void supprimerLogique(T t) {
        this.getDao().supprimerLogique(t);
    }

    @Override
    public void supprimerTout() {
        this.getDao().supprimerTout();
    }

    @Override
    public void delete(List<T> list) {
        if (list != null) {
            for (T t : list) {
                this.getDao().supprimer(t);
            }
        }
    }

    @Override
    public void supprimer(T t, K k) {
        this.getDao().supprimer(t, k);
    }

    ;

    @Override
    public boolean exists(String value, String attribut) {
        return this.getDao().exists(value, attribut);
    }

    @Override
    public String getExecutedSql() {
        return this.getDao().getExecutedSql();
    }

    @Override
    public List<T> selectionnerTout(String propriete, String mot) {
        return this.getDao().selectionnerTout(propriete, mot);
    }

    @Override
    public int compter() {
        return this.getDao().compter();
    }

    @Override
    public T selectionner(String propriete, Object valeur) {
        return this.getDao().selectionner(propriete, valeur);
    }

    @Override
    public String getDatabaseName() {
        return this.getDao().getDatabaseName();
    }

    @Override
    public String formater(int pos, String value) {

        String val = new String();
        for (int i = 0; i < pos; i++) {
            val = val + "0";
        }
        if (pos > value.length()) {
            val = val.substring(0, pos - value.length()).concat(value);
        } else if (pos == value.length()) {
            val = value;
        }

        return val;
    }
}

