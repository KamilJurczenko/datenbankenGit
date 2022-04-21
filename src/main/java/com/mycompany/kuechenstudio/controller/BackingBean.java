/*
 * 
 * April 2022
 */
package com.mycompany.kuechenstudio.controller;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import com.mycompany.kuechenstudio.model.User;

/**
 *
 * @author 
 */
@Named(value = "backingBean")
@RequestScoped
public class BackingBean {

    private String title;
    private String pwd;
    private FacesContext context;
    private List<User> userList;
    private boolean ok;
    private static int id = 0;

    /**
     * Creates a new instance of BackingBean
     */
    public BackingBean() {
    }

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        userList = new ArrayList<>();
        userList.add(new User(getId(),"admin", "secret"));
        userList.add(new User(getId(),"user1", "secret1"));
    }

    public void process() {
        FacesMessage fm = null;
        ok = false;

        if (pwd.length() >= 5) {

            for (User u : userList) {
                if (u.getPassword().equals(pwd)) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                fm = new FacesMessage("Ok.");

                //Ermittlung der Aktionskomponente und Message an diese
                UIComponent uic = UIComponent.getCurrentComponent(context);
                context.addMessage(uic.getClientId(), fm);

                //Message an eine Eingabekomponente
                fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Korrekt", //Summary
                        ": Passwort korrekt!");   //Detail);
                context.addMessage("password", fm);
            } else {
                //Message an eine Eingabekomponente
                fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Passwortfehler", //Summary
                        ": Passwort nicht korrekt!");   //Detail);
                context.addMessage("password", fm);
            }

        } else {
            //Message an globale Messages-Komponente
            fm = new FacesMessage(
                    FacesMessage.SEVERITY_WARN,
                    "Passwortfehler", //Summary
                    ": Passwort zu kurz!");   //Detail
            context.addMessage(null, fm);
        }
    }
    
    /**
     * Der Listener wird erst aufgerufen, wenn die Eingabe nicht leer ist und
     * die Validierung erfolgreich war!
     *
     * @param ev
     */
    public void inputAjaxListener(AjaxBehaviorEvent ev) {
        FacesMessage fm;
        if (!context.isValidationFailed()) {
            fm = new FacesMessage("Passwort: "+pwd + " (Länge ok.).");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            fm.setDetail("Passwort formal korrekt!");
            //Fehler: Message wird nicht angezeigt!!!
            context.addMessage(null, fm);
        }
    }

    /**
     * Get the value of pwd
     *
     * @return the value of pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Set the value of pwd
     *
     * @param pwd new value of pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return "Passwort-Eingabe";
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the ok
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * @param ok the ok to set
     */
    public void setOk(boolean ok) {
        this.ok = ok;
    }

    /**
     * @return the id
     */
    public static int getId() {
        return ++id;
    }

    /**
     * @param aId the id to set
     */
    public static void setId(int aId) {
        id = aId;
    }
}
