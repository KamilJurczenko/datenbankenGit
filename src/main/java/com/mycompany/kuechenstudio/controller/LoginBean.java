/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.kuechenstudio.controller;

import com.mycompany.kuechenstudio.model.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * LoginBean dient zur Anmeldung eines Benutzers der bereits 
 * in der "Datenbank" vorhanden ist
 * @author kamil
 * 
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String loginName;
    private String password;
    private FacesContext context;
    private List<User> userList; //Simuliert Datenbank mit dummy Usern
    //private boolean ok; // bool für Nutzer Validierung
    private static int id = 0;
    
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    @PostConstruct
    public void init() {
        
    }
    public void CheckLogin(){
        if(loginName.equals("admin") && password.equals("admin")){
            // Als Admin eingeloggt
            Login(true);
        }
        else{
            
        }
    }
    public void Login(boolean admin){
        System.out.println("Login...");
        ELContext elContext = FacesContext.getCurrentInstance().getELContext(); 
        ServiceBean serviceBean = (ServiceBean) FacesContext.getCurrentInstance().getApplication() 
            .getELResolver().getValue(elContext, null, "serviceBean");
        serviceBean.setIsLoggedIn(true);
        serviceBean.setAdmin(admin);
        serviceBean.setNobody(!admin);
    }
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
