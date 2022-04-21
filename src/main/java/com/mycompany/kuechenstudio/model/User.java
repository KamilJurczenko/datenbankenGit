/*
 * Seminar: Messaging, Validieren
 * April 2022
 */
package com.mycompany.kuechenstudio.model;

/**
 *
 * @author lgrue
 */
public class User {

    private int uid;
    private String loginName;
    private String password;

    public User() {
    }

    public User(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public User(int uid, String loginName, String password) {
        this.uid = uid;
        this.loginName = loginName;
        this.password = password;
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

    /**
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(int uid) {
        this.uid = uid;
    }
}
