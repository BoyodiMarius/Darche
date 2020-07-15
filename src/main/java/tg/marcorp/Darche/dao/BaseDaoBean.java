/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import tg.marcorp.Darche.entities.BaseEntity;

/**
 *
 * @author BOYODI Wiyow Marius
 * @param <T>
 * @param <K>
 */
public abstract class BaseDaoBean<T extends BaseEntity, K extends Serializable> implements BaseDaoBeanLocal<T, K> {
    
    @PersistenceContext(unitName = "Darche1.1_PU")
    protected EntityManager em;
    private Class<T> type;
    private String executedSql;
    EntityManager emf;
    
    public BaseDaoBean() {
    }

    public BaseDaoBean(Class<T> type) {
        this.type = type;
    }

    @Override
    public void ajouter(T t) {
        try {
            //recuperer l'id de la table
            // s'il est vide, appeler la methode de séquencaget
            this.em.persist(t);
//            this.em.flush();
//        String id = null;
//        try {
//            id = t.getId();
//        } catch (Exception e) {
//            System.out.println("id null");
//        }
//
//        if (id == null) {
//            this.ajouterAvecSequence(t);
//        } else {
//            this.em.persist(t);
//        }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("");
        }
    }

    @Override
    public void ajouter(List<T> list) {
        for (T t : list) {
            this.em.persist(t);
        }
    }

    @Override
    public T modifier(T t) {
        return this.em.merge(t);

    }

    @Override
    public List<T> modifier(List<T> list) {
        List<T> liste = new ArrayList<T>();
        for (T t : list) {
            this.em.merge(t);
            liste.add(t);
        }
        return liste;
    }

    @Override
    public List<T> modifierListe(List<T> list) {
        List<T> l = new ArrayList();
        for (T t : list) {
            l.add(this.em.merge(t));
        }
        return l;
    }

    @Override
    public List<T> selectionnerTout() {

        Query query;
        query = (Query) em.createQuery("SELECT t FROM  " + this.type.getSimpleName() + " t ");
        //System.out.println("SELECT t FROM  " + this.type.getSimpleName() + " t");
        return query.getResultList();
    }
    

    public String genererCode(T t, String codeAgence) {
        String numGene = "";
        Integer taille;
        Query query;
        try {
            query = (Query) em.createQuery("SELECT t FROM  " + this.type.getSimpleName() + " t");
            taille = query.getResultList().size() + 1;
        } catch (NoResultException e) {
            taille = 1;
        }
        numGene = codeAgence.concat("-").concat(taille.toString());
        return numGene;
    }

    @Override
    public void supprimerLogique(T t) {
        throw new UnsupportedOperationException("Not supported yet.");
        //return this.em.merge(t);
    }

    @Override
    public List<T> selectionnerParTableAttribut(Object valeur, String attribut) {
        Query q;
        try {
            String req = "select  t from " + this.type.getSimpleName() + " t where t." + attribut + " = :valeur ";
            System.out.println("REQUETE" + req);
            q = em.createQuery(req);
            q.setParameter("valeur", valeur);
            return (List<T>) q.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<T> selectionnerParDeuxAttributs(String att1, String att2, Object val1, Object val2) {
        Query query;
        try {
            query = em.createQuery //   ("SELECT e FROM " +  this.type.getSimpleName() + " e  WHERE e."+att1+" = '"+val1.toString()+"'
                    ("SELECT e FROM " + this.type.getSimpleName() + " e  WHERE e." + att1 + " = '" + val1 + "' AND e." + att2 + " = '" + val2 + "'");
            System.out.println("REQUETE" + query);
            return (List<T>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public List<T> selectionner(String att1, String att2, String att3, Object val1, Object val2) {
        Query query;
        query = em.createQuery("SELECT e FROM " + this.type.getSimpleName() + "  e WHERE concat(e." + att1 + ",' ',e." + att2 + ") = '" + val1.toString() + "' and e." + att3 + "='" + val2.toString() + "'");
        return (List<T>) query.getResultList();
    }

    @Override
    public List<T> selectionnerTout(String propriete, String mot) {
        try {
            Query q = em.createQuery("SELECT t FROM " + this.type.getSimpleName() + " t WHERE t." + propriete + " LIKE :mot");
            q.setParameter("mot", mot);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public T selectionner(K k) {
        return (T) em.find(this.type, k);

    }

    @Override
    public void supprimerTout() {
        Query query;
        query = em.createQuery("DELETE  FROM  " + this.type.getSimpleName() + " t ");
        query.executeUpdate();
    }

    @Override
    public void supprimer(K k) {
        this.em.remove(selectionner(k));
    }

    @Override
    public void supprimer(T t) {
        this.em.remove(em.merge(t));

    }

    @Override
    public void supprimer(T t, K k) {
        t = em.getReference(type, k);
        em.remove(t);
        em.refresh(t);
    }

    @Override
    public boolean exists(String value, String attribut) {
        try {
            String req = "select  t from " + this.type.getSimpleName() + " t where t." + attribut + " =:valeur";
            Query query = em.createQuery(req);
            query.setParameter("valeur", value);
            T t = (T) query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public List<T> selectionnerParTableAttribut(Object o, String table, String attribut) {
        Query q;
        try {
            String req = "select  t from " + this.type.getSimpleName() + " t where t." + attribut + " ='" + o.toString() + "' and t.supprimer='0'";
            System.out.println("REQUETE" + req);
            q = em.createQuery(req);
            return (List<T>) q.getResultList();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public String getExecutedSql() {
        return executedSql;
    }

    public void setExecutedSql(String executedSql) {
        this.executedSql = executedSql;
    }

    @Override
    public int compter() {
        int compte = 0;
        List<T> entite = this.selectionnerTout();
        for (T t : entite) {
            compte++;
        }
        return compte;
    }

    @Override
    public T selectionner(String propriete, Object valeur) {
        try {
            Query q = em.createQuery("SELECT t FROM " + this.type.getSimpleName() + " t WHERE t." + propriete + "=:valeur");
            q.setParameter("valeur", valeur);
            return (T) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public String getDatabaseName() {

        try {
            return "";
            //return this.em.getProperties().get("databaseName").toString();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Object[]> executerProcedure(String nomProcedure, Map<String, Object> parametres) {
        StoredProcedureQuery query = this.em.createStoredProcedureQuery(nomProcedure);
        Set<String> cles = parametres.keySet();
        List tmpResultat = null, tableaux;
        Class classe;
        for (String tmp : cles) {
            System.out.println("--tmp = " + tmp);
            System.out.println("--val = " + (parametres.get(tmp) == null ? "" : parametres.get(tmp)));
            classe = parametres.get(tmp) == null ? String.class : parametres.get(tmp).getClass();
            System.out.println("classe = " + classe);
            query.registerStoredProcedureParameter(tmp, classe, ParameterMode.IN);
            query.setParameter(tmp, (parametres.get(tmp) == null ? "" : parametres.get(tmp)));
        }
        query.execute();
        do {
            tableaux = query.getResultList();
            if (tmpResultat == null) {
                tmpResultat = tableaux;
            } else {
                tmpResultat.addAll(tableaux);
            }
        } while (query.hasMoreResults() != false || query.getUpdateCount() != -1);
        System.out.println("----tmpResultat = " + tmpResultat);
        return tmpResultat;
    }
    
}

