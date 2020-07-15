/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.marcorp.Darche.dao.BaseDaoBeanLocal;
import tg.marcorp.Darche.dao.clientsDaoBeanLocal;
import tg.marcorp.Darche.entities.clients;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class clientsServiceBean extends BaseServiceBean<clients, Long> implements clientsServiceBeanLocal {
 
    @EJB
    private clientsDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<clients, Long> getDao(){
        return this.dao;
    }
    
}
