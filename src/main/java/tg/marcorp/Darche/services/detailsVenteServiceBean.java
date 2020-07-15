/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.marcorp.Darche.dao.BaseDaoBeanLocal;
import tg.marcorp.Darche.dao.detailsVenteDaoBeanLocal;
import tg.marcorp.Darche.entities.detailsVente;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class detailsVenteServiceBean extends BaseServiceBean<detailsVente, Long> implements detailsVenteServiceBeanLocal {
 
    @EJB
    private detailsVenteDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<detailsVente, Long> getDao(){
        return this.dao;
    }
    
}
