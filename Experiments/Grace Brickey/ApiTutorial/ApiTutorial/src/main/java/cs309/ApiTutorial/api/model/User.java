package cs309.ApiTutorial.api.model;

public class User {
    private int id;
    private String name;
    private int age;
    private String email;
    private String usertype;
    private int wins;

    public User(int id, String name, int age, String email, String usertype) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.usertype = usertype;
        this.wins = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
