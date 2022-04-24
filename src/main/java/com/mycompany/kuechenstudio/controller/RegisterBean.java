/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.kuechenstudio.controller;

import java.io.Serializable;
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
    
    private String gender;
    private String loginName;
    private String pwd;
    private String firstName;
    private String lastName;
    private boolean ok;
    private FacesContext context;
    //private LoginBean loginBean;

    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }
    
    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getLoginName() {
        return loginName;
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
        this.loginName = loginName;
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
    public void ValidateRegister(){
        
        FacesMessage fm = null;
        ok = true;
        
        // Überprüfe ob Vorname und Nachname nur das Alphabet enthält
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
        // Überprüfe ob Loginname bereits vorhanden
        if(loginName.equals("admin")){
            fm = new FacesMessage("Loginname bereits vorhanden!");
            context.addMessage("loginName", fm);
            ok = false;
        }
        // Registrierung ist Ok. Öffne Startseite und zeige an das der User angemeldet ist
        if(ok){
            fm = new FacesMessage("Ok.");
            UIComponent uic = UIComponent.getCurrentComponent(context);
            context.addMessage(uic.getClientId(), fm);
            StoreUserInfo();
            context.getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");
        }      
    }
    
    // Loge Nutzer ein
    public void StoreUserInfo() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext(); 
        LoginBean loginBean = (LoginBean) FacesContext.getCurrentInstance().getApplication() 
            .getELResolver().getValue(elContext, null, "loginBean");
        loginBean.Login(false);
    }
    
    public void inputAjaxListener(AjaxBehaviorEvent ev) {
        FacesMessage fm;
        if (!context.isValidationFailed()) {
            fm = new FacesMessage("Passwort Ok.");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fm.setDetail("Passwort formal korrekt!");
            //Methode wird aufgerufen aber keine Message angezeigt
            context.addMessage("password", fm);
        }
    }
}
