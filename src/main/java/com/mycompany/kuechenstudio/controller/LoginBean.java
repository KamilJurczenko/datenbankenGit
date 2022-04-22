/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.kuechenstudio.controller;

import com.mycompany.kuechenstudio.model.User;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;

/**
 * LoginBean dient zur Anmeldung eines Benutzers der bereits 
 * in der "Datenbank" vorhanden ist
 * @author kamil
 * 
 */
@Named(value = "loginBean")
@Dependent
public class LoginBean {

    private String title;
    private String pwd;
    private FacesContext context;
    private List<User> userList; //Simuliert Datenbank mit dummy Usern
    private boolean ok;
    private static int id = 0;
    
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
}
