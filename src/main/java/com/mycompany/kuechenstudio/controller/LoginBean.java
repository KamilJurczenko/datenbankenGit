/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.kuechenstudio.controller;

//import com.mycompany.kuechenstudio.model.User;
import com.mycompany.kuechenstudio.model.User;
import java.io.Serializable;
import java.util.List;
//import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * LoginBean dient zur Anmeldung eines Benutzers der bereits 
 * in der "Datenbank" vorhanden ist
 * @author kamil
 * 
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    private String loginName;
    private String password;
    private FacesContext context;
    private ServiceBean serviceBean;
    
    private UIComponent messageComponent;
    //private List<User> userList; //Simuliert Datenbank mit dummy Usern
    //private boolean ok; // bool für Nutzer Validierung
    //private static int id = 0;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext(); 
        serviceBean = (ServiceBean) FacesContext.getCurrentInstance().getApplication() 
            .getELResolver().getValue(elContext, null, "serviceBean");
    }
    
    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
    }
    // Prüfe ob Logindaten in "Datenbank"
    public void CheckLogin(){
        boolean ok = false;
        if(loginName.equals("admin") && password.equals("admin")){
            // Als Admin eingeloggt
            Login(true, new User(loginName,password));
            ok = true;
        }
        else{
            List<User> l = serviceBean.getUserList();
                for (User u : l){
                    if(u.getLoginName().equals(loginName) && u.getPassword().equals(password)){
                        Login(false, new User(loginName,password));
                        ok = true;
                        break;
                }
            } 
        }
        // Kein Nutzer mit dem Namen und Passwort in "Datenbank"
        if(ok == false){
            FacesMessage fm = new FacesMessage("Eingaben inkorrekt!");
            context.addMessage(messageComponent.getClientId(), fm);
        }
    }
    public void Login(boolean admin, User u){
        //System.out.println("Login...");

        // serviceBean Zustand setzen
        //serviceBean.setIsLoggedIn(true);
        serviceBean.setAdmin(admin);
        serviceBean.setNobody(!admin);
        serviceBean.setLoggedUser(u);
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

    public UIComponent getMessageComponent() {
        return messageComponent;
    }

    public void setMessageComponent(UIComponent messageComponent) {
        this.messageComponent = messageComponent;
    }
    
    
    
}
