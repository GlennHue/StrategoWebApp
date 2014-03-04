/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/
package be.kdg.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "T_FRIENDS",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "friendId")}
    )
    private List<User> friends = new ArrayList<User>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_USERACHIEVEMENTS",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "achievementId")}
    )
    private List<Achievement> achievements = new ArrayList<Achievement>();

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

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User user) {
        friends.add(user);
    }

    public boolean equals(Object other){
        User otherUser = (User) other;
        if (otherUser == null)
            return false;
        else if (otherUser.getId() == this.getId())
            return true;
        return false;
    }
}
