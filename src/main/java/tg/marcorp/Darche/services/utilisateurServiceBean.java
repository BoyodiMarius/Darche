/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.marcorp.Darche.dao.BaseDaoBeanLocal;
import tg.marcorp.Darche.dao.utilisateurDaoBeanLocal;
import tg.marcorp.Darche.entities.utilisateurs;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class utilisateurServiceBean extends BaseServiceBean<utilisateurs, Long> implements utilisateurServiceBeanLocal {
 
    @EJB
    private utilisateurDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<utilisateurs, Long> getDao(){
        return this.dao;
    }
    
}
