/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/
package be.kdg.model;


import javax.persistence.*;

@Entity
@Table(name = "T_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String eMail;
    private String uuid;
    private boolean verified;

    public User() {
    }

    public User(String username, String password, String eMail) {
        this.username = username;
        this.password = password;
        this.eMail = eMail;
        this.verified = false;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean isVerified) {
        this.verified = isVerified;
    }
}
