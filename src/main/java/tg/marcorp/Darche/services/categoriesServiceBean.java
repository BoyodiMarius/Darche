/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import tg.marcorp.Darche.dao.BaseDaoBeanLocal;
import tg.marcorp.Darche.dao.categoriesDaoBeanLocal;
import tg.marcorp.Darche.entities.categories;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class categoriesServiceBean extends BaseServiceBean<categories, Long> implements categoriesServiceBeanLocal {
 
    @EJB
    private categoriesDaoBeanLocal dao;
    
    @Override
    protected BaseDaoBeanLocal<categories, Long> getDao(){
        return this.dao;
    }
    
}
