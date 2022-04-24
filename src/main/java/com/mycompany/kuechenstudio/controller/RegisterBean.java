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
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 * Backing-Bean für Registrierungs Prozess
 * @author kamil
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean implements Serializable {
    
    private String gender; //Geschlecht
    private String registerName; //Loginname
    private String pwd; //Passwort
    private String firstName; //Vorname
    private String lastName; //Nachname
    private boolean ok; //bool zur Validierung
    private FacesContext context;
    
    private UIComponent passwordComponent;    
    private UIComponent loginNameComponent;
    //private LoginBean loginBean;

    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
        
    }
    @PostConstruct
    void init(){
        context = FacesContext.getCurrentInstance();
    }

    public UIComponent getPasswordComponent() {
        return passwordComponent;
    }

    public void setPasswordComponent(UIComponent passwordComponent) {
        this.passwordComponent = passwordComponent;
    }

    public UIComponent getLoginNameComponent() {
        return loginNameComponent;
    }

    public void setLoginNameComponent(UIComponent loginNameComponent) {
        this.loginNameComponent = loginNameComponent;
    }
    
    
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getLoginName() {
        return registerName;
    }

    public String getPwd() {
        return pwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLoginName(String loginName) {
        this.registerName = loginName;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    // Überprüfe ob alle Eingaben den Anforderungen entsprechen
    // Füge neuen Nutzer in "Datenbank" falls Erfolgreiche Validierung
    public void ValidateRegister(){
        
        FacesMessage fm = null;
        ok = true;
        // Serverseitige Validierung!
        // Überprüfe ob Vorname und Nachname nur das Alphabet enthält
        /*
        if(!firstName.matches("[a-zA-Z]+")){
            fm = new FacesMessage("Ungültige Zeichen!");
            context.addMessage("firstName", fm);
            ok = false;
        }
        if(!lastName.matches("[a-zA-Z]+")){
            fm = new FacesMessage("Ungültige Zeichen!");
            context.addMessage("lastName", fm);
            ok = false;
        }    
        */
        // Überprüfe ob Loginname bereits vorhanden
        ELContext elContext = FacesContext.getCurrentInstance().getELContext(); 
        ServiceBean serviceBean = (ServiceBean) FacesContext.getCurrentInstance().getApplication() 
            .getELResolver().getValue(elContext, null, "serviceBean");
        // Durch jeden User iterieren und überprüfen ob loginname identisch zu loginName
        if(registerName.equals("admin")){
            ok = false;
        }
        else{
            List<User> l = serviceBean.getUserList();
            for (User u : l){
                if(u.getLoginName().equals(registerName)){
                    ok = false;
                    break;
                }
            } 
        }

        // Registrierung ist Ok. Öffne Startseite und zeige an das der User angemeldet ist
        if(ok){
            //fm = new FacesMessage("Ok.");
            //UIComponent uic = UIComponent.getCurrentComponent(context);
            //context.addMessage(uic.getClientId(), fm);
            elContext = FacesContext.getCurrentInstance().getELContext(); 
            LoginBean loginBean = (LoginBean) FacesContext.getCurrentInstance().getApplication() 
                .getELResolver().getValue(elContext, null, "loginBean");
            User u = new User(registerName,pwd);
            loginBean.Login(false, u);
            serviceBean.addNewUser(registerName,pwd);
            context.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");
        }   
        else{
            fm = new FacesMessage("Loginname bereits vorhanden!");
            context.addMessage(loginNameComponent.getClientId(), fm);
        }
    }
    
    public void inputAjaxListener(AjaxBehaviorEvent ev) {
        FacesMessage fm;
        if (!context.isValidationFailed()) {
            fm = new FacesMessage("Passwort Ok.");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fm.setDetail("Passwort formal korrekt!");
            //Methode wird aufgerufen aber keine Message angezeigt
            context.addMessage(passwordComponent.getClientId(), fm);
        }
    }
}
