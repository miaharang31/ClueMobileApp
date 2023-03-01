package com.example.loginactivity.api;

import retrofit2.http.POST;

public interface PlayerApi {

    @GET("/saveUser")
    Call<String> saveUser(@Body Player player);


//    @PostMapping("/saveUser")
//    public String saveUser(@RequestBody Player player) {
//        repository.save(player);
//        return "User saved:" + player.getUsername();
//    }
//    @PostMapping("/findUserUnP")
//    public Optional<Player> getUserByUsernameAndPassword(@RequestBody String username, @RequestBody String password) {
//        Optional<Player> user = repository.findByUsernameAndPassword(username, password);
//        return user;
//    }
//    @GetMapping("/getUser/{id}")
//    public Optional<Player> getUserById(@PathVariable("id") Integer id) {
//        //logger.info("Entered");
//        Optional<Player> user = repository.findById(id);
//        return user;
//    }
//    @PutMapping("/changePassword/{username}")
//    public Player changePassword(@PathVariable("username") String username, @RequestBody String password) {
//        //logger.info("Entered");
//        Player player = repository.findByUsername(username)
//                .orElseThrow();
//        player.setPassword(password);
//        return player;
//    }
//
//    @PutMapping("/updateType/{username}")
//    public Player upgradeToPremium(@PathVariable("username") String username, @RequestBody String account) {
//        //logger.info("Entered");
//        Player player = repository.findByUsername(username)
//                .orElseThrow();
//        if (player.getType().equals("Basic")) {
//            player.setType("Premium");
//        }
//        return player;
//    }

}
