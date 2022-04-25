/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.kuechenstudio.controller;

import com.mycompany.kuechenstudio.model.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author kamil
 */
@Named(value = "shoppingCartBean")
@SessionScoped
public class ShoppingCartBean implements Serializable{

    private List<Product> shoppingCartItems;
    //private int amount; // Ausgewählte Stückzahl
    
    private FacesContext context;
    
    private UIComponent productTableComponent;  
    private UIComponent shoppingCartTableComponent; 
    /**
     * Creates a new instance of ShoppingCartBean
     */
    public ShoppingCartBean() {
        shoppingCartItems = new ArrayList<>();
        context = FacesContext.getCurrentInstance();
    }

    public UIComponent getShoppingCartTableComponent() {
        return shoppingCartTableComponent;
    }

    public void setShoppingCartTableComponent(UIComponent shoppingCartTableComponent) {
        this.shoppingCartTableComponent = shoppingCartTableComponent;
    }

    public UIComponent getProductTableComponent() {
        return productTableComponent;
    }

    public void setProductTableComponent(UIComponent dataTableComponent) {
        this.productTableComponent = dataTableComponent;
    }

    /*public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }*/
    
    
    public List<Product> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<Product> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }
    
    public void changeAmount(ValueChangeEvent e){
        //amount = (int)e.getNewValue();
        //System.out.println(amount);
    }
    
    public void ValidateOrder(){
        ELContext elContext = FacesContext.getCurrentInstance().getELContext(); 
        ServiceBean serviceBean = (ServiceBean) FacesContext.getCurrentInstance().getApplication() 
            .getELResolver().getValue(elContext, null, "serviceBean");
        ProductBean productBean = (ProductBean) FacesContext.getCurrentInstance().getApplication() 
            .getELResolver().getValue(elContext, null, "productBean");
        if(serviceBean.getLoggedUser() != null){
            FacesMessage fm = new FacesMessage("Artikel bestellt!");
            FacesContext.getCurrentInstance().addMessage(shoppingCartTableComponent.getClientId(), fm);
            List<Product> pi = productBean.getItems();
            // Stueckzahl aktualisieren
            for (Product p : shoppingCartItems){
                int a = pi.get(p.getId()).getStueckZahl();
                pi.get(p.getId()).setStueckZahl(a-p.getReservedStueckZahl());
            }
                shoppingCartItems.clear();
            }
        else{
            FacesMessage fm = new FacesMessage("Bitte anmelden!");
            FacesContext.getCurrentInstance().addMessage(shoppingCartTableComponent.getClientId(), fm);
        }
    }
    
    // Füge ausgewähltes Produkt im Warenkorb hinzu
    public void ValidateAddToShoppingCart(Product item){
        //System.out.println(amount);
        if(item.getReservedStueckZahl() != 0){
            int newStueckZahl = item.getStueckZahl() - item.getReservedStueckZahl();
            //System.out.println(item.getReservedStueckZahl());
            Product p = new Product(item);
            shoppingCartItems.add(p);
            item.setStueckZahl(newStueckZahl);
            item.setArr(IntStream.rangeClosed(0, newStueckZahl).toArray());
            FacesMessage fm = new FacesMessage(item.getName() + " im Warenkorb gespeichert.");
            FacesContext.getCurrentInstance().addMessage(productTableComponent.getClientId(), fm);
        }
        else{
            FacesMessage fm = new FacesMessage("Bitte Stückanzahl auswählen!");
            FacesContext.getCurrentInstance().addMessage(productTableComponent.getClientId(), fm); 
        }
    }
    
    
    
    
}
