/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/
package be.kdg.model;


import javax.persistence.Entity;

@Entity
public class User {
    private String username;
    private String password;
    private String eMail;

    public User(String username, String password, String eMail) {
        this.username = username;
        this.password = password;
        this.eMail = eMail;
    }
}
