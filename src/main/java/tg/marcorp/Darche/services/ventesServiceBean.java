/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.marcorp.Darche.dao.BaseDaoBeanLocal;
import tg.marcorp.Darche.dao.ventesDaoBeanLocal;
import tg.marcorp.Darche.entities.ventes;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class ventesServiceBean extends BaseServiceBean<ventes, Long> implements ventesServiceBeanLocal {
 
    @EJB
    private ventesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<ventes, Long> getDao(){
        return this.dao;
    }
    
}
