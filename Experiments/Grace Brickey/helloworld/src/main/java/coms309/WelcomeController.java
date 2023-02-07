package coms309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello and welcome to COMS 309";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }

    @GetMapping("/benice/{name}")
    public String benice(@PathVariable String name) {
        return name + ", you are so beautiful!";
    }

    @GetMapping("/crazy")
    public String crazy() {
        return "IT IS SO LOUD IN HERE";
    }
}
