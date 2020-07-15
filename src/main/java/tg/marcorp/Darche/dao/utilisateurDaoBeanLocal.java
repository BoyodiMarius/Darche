/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.dao;

import javax.ejb.Local;
import tg.marcorp.Darche.entities.utilisateurs;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@Local
public interface utilisateurDaoBeanLocal extends BaseDaoBeanLocal<utilisateurs, Long> {
    
}
