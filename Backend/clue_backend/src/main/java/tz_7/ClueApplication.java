package tz_7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClueApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClueApplication.class, args);
    }

//    @Bean
//    CommandLineRunner initUser(UserRepository userRepository) {
//        return args -> {
//            User user1 = new User(1, "Grace", "Brickey", "gbrickey", "gbri@alf.com", "Administer");
//            User user2 = new User(2, "Gwyn", "Brickey", "gwynbrickey", "gqyn@alf.com", "Premium");
//            User user3 = new User(3, "Amy", "Brick", "gwynbrickey", "gqyn@alf.com", "Premium");
//            userRepository.save(user1);
//            userRepository.save(user2);
//            userRepository.save(user3);
//        };
//    }

}
