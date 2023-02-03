package cs309.ApiTutorial.service;


import cs309.ApiTutorial.api.model.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    private ArrayList<User> userList;

    public UserService() {
        userList = new ArrayList<User>();

        User user = new User(1,"Grace",21,"gbrickey@iastate.edu","Administer");
        User user1 = new User(2,"Dan",18,"dancoolio@iastate.edu","General");
        User user2 = new User(3,"Phil",11,"philyomama@iastate.edu","General");
        User user3 = new User(4,"Emily",28,"emilydickens@iastate.edu","Premium");
        User user4 = new User(5,"Leah",22,"lgiese@iastate.edu","Administer");

        userList.addAll(Arrays.asList(user,user1,user2,user3,user4));
    }

    public Optional<User> getUser(Integer id) {
        Optional optional = Optional.empty();
        for (User user : userList) {
            if(id == user.getId()) {
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
