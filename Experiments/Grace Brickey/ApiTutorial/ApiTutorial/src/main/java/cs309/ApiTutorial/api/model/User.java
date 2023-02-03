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
}
