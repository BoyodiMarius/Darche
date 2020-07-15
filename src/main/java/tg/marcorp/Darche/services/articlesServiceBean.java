/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.marcorp.Darche.dao.BaseDaoBeanLocal;
import tg.marcorp.Darche.dao.articlesDaoBeanLocal;
import tg.marcorp.Darche.entities.articles;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class articlesServiceBean extends BaseServiceBean<articles, Long> implements articlesServiceBeanLocal {
 
    @EJB
    private articlesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<articles, Long> getDao(){
        return this.dao;
    }
}
