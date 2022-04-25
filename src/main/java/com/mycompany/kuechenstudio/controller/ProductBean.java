/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.kuechenstudio.controller;

import com.mycompany.kuechenstudio.model.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "productBean")
@SessionScoped
public class ProductBean implements Serializable{

    private List<Product> items;
    
    public ProductBean(){

    }
    
    // Garantierter einmaliger Aufruf
    @PostConstruct
    public void init() {
        System.out.println("TEST");
        items = new ArrayList<>();
        items.add(new Product("Kühlschrank",400f,"weiß",4));
        items.add(new Product("Küchentisch",200f,"schwarz",1));
        items.add(new Product("Herd",250f,"weiß",2));
        items.add(new Product("Theke",150f,"Holz",3));
    }
    
    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
    
}
