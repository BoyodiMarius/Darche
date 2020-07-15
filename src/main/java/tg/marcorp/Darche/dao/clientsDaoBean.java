/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.dao;

import javax.ejb.Stateless;
import tg.marcorp.Darche.entities.clients;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Stateless
public class clientsDaoBean extends BaseDaoBean<clients, Long> implements clientsDaoBeanLocal {
  
    public clientsDaoBean(){
        super(clients.class);
    }
    
}
