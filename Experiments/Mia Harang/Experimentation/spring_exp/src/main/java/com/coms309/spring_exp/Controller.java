package com.coms309.spring_exp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;

@RestController
public class Controller {
    private final ArrayList<User> users = new ArrayList<>();

    @GetMapping("/")
    public String index() {
        return "Greetings";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user/new")
    public String newUser(String name, String number) {
        User tmp = new User(name, number, users.size()+1);
        users.add(tmp);
        return "New User Added: id number is " + tmp.getID();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/get")
    public String getUser(int id) {
        if(users.size() >= id) {
            User tmp = users.get(id -1);
            if(tmp.getID() > 0) {
                return "Your name is " + tmp.getName() + ". Your number is " + tmp.getNumber();
            }
            return "That User has been deleted!";
        }
        return "No user found with that ID";
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/user/delete")
    public String deleteUser(int id) {
        if(users.size() >= id) {
            User tmp = users.get(id-1);
            if(tmp.getID() > 0) {
                tmp.changeID(-1);
                return "User has been deleted";
            }
            return "User has already been previously deleted";
        }
        return "No user by that id was found";
    }
}
