/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author kamil
 */
@Named(value = "backingBean")
@Dependent
public class BackingBean {

    private String string;

    private FacesContext context;

    @PostConstruct
    public void init(){
        context = FacesContext.getCurrentInstance();

    }

    public void process(){
        FacesMessage fm;
        if(string.length() >= 5 ){
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success","Ok.");
            //Ermittlung der Aktionskomponente
            UIComponent uic = UIComponent.getCurrentComponent(context);
            context.addMessage(uic.getClientId(), fm);
        }
        else{
            //Message an die globale Messages-Komponente
            fm = new FacesMessage(FacesMessage.SEVERITY_WARN,"Passwortfehler","Passwort zu kurz!");
            context.addMessage(null,fm);
        }

    }
    public void inputAjaxListener(AjaxBehaviorEvent ev){ 
        // Verwendet um einzelne Komponenten neu zu laden und nicht die komplette Seite
        process();
    }
    /**
     * 
     * Get the value of string
     *
     * @return the value of string
     */
    public String getString() {
        return string;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Creates a new instance of BackingBean
     */
    public BackingBean() {
    }
    
}
