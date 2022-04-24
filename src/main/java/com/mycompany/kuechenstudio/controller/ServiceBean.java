/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.kuechenstudio.controller;

import com.mycompany.kuechenstudio.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * Zustand des Nutzers und Registrierte Nutzer dataholder
 * @author kamil
 */
@Named(value = "serviceBean")
@SessionScoped
public class ServiceBean implements Serializable{

    private List<User> userList; //Simuliert Datenbank mit dummy Usern
    private static int userId;
    private User loggedUser; //Eingeloggter User, null falls nicht eingeloggt
    //private boolean isLoggedIn = false;
    private boolean nobody = false;
    private boolean admin = false;
    FacesContext context;
    /**
     * Creates a new instance of ServiceBean
     */
    public ServiceBean() {
        //System.out.println("TEST");
        context = FacesContext.getCurrentInstance();
        userList = new ArrayList<>();
        userList.add(new User(getId(),"admin", "secret"));
    }

    public List<User> getUserList() {
        return userList;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
    
    public void addNewUser(String name, String pwd){
        User newUser = new User(getId(),name,pwd);
        userList.add(newUser);
    }
    
    /*public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }*/

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
    
    public static int getId() {
        return ++userId;
    }

    public static void setId(int aId) {
        userId = aId;
    }
}
