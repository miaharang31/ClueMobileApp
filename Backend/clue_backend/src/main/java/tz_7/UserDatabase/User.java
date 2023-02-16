package tz_7.UserDatabase;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Grace Brickey
 */

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    //@NotFound(action = NotFoundAction.IGNORE)
    private int id;
    @Column(name = "First_Name")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String firstname;
    @Column(name = "Last_Name")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String lastname;
    @Column(name = "Username")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String username;
    @Column(name = "Email")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String email;
    @Column(name = "Password")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String password;
    @Column(name = "User_type")
    //@NotFound(action = NotFoundAction.IGNORE)
    private String type; //use type can be a for administer, b for basic
                            // and p for premium
    //@Column(name = "WINS")
    //@NotFound(action = NotFoundAction.IGNORE)
    private int wins;

    public User() {}

    public User(int id, String firstname, String lastname, String email, String password, String type) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.type = type;
        this.wins = 0;
    }
    public User(int id, String firstname, String lastname, String email, String password, String type, int wins) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.type = type;
        this.wins = wins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
