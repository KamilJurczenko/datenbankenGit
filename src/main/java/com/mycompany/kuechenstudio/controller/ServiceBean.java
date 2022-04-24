/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.kuechenstudio.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author kamil
 */
@Named(value = "serviceBean")
@SessionScoped
public class ServiceBean implements Serializable{

    private boolean isLoggedIn = false;
    private boolean nobody = false;
    private boolean admin = false;
    /**
     * Creates a new instance of ServiceBean
     */
    public ServiceBean() {
    }

    @PostConstruct
    public void init() {
        
    }
    
    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isNobody() {
        return nobody;
    }

    public void setNobody(boolean nobody) {
        this.nobody = nobody;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
}
