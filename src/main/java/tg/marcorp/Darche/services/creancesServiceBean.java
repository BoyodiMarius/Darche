/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.marcorp.Darche.dao.BaseDaoBeanLocal;
import tg.marcorp.Darche.dao.creancesDaoBeanLocal;
import tg.marcorp.Darche.entities.creances;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class creancesServiceBean extends BaseServiceBean<creances, Long> implements creancesServiceBeanLocal {
 
    @EJB
    private creancesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<creances, Long> getDao(){
        return this.dao;
    }
    
}
