/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tg.marcorp.Darche.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import javax.faces.view.ViewScoped;

/**
 *
 * @author BOYODI Wiyow Marius
 */
@ManagedBean
@ViewScoped
public class imageViewMBean implements Serializable {
    
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        for (int i = 1; i <= 17; i++) {
            images.add("image" + i + ".jpg");
        }
        for (String image : images) {
            System.out.println("images " + image);
        }
        
    }
 
    public List<String> getImages() {
        return images;
    }
    /**
     * Creates a new instance of ImagesViewManagedBean
     */
    public imageViewMBean() {
        
        
    }
    
    
}
