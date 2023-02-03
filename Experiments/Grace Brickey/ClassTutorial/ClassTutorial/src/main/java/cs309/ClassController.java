package cs309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import student.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
public class ClassController {
    @GetMapping("/")
    public List<Student> hello() {
        return List.of(
                new Student(1L,
                        "Grace",
                        "grace.brickey@gmail.com",
                        LocalDate.of(2001, Month.DECEMBER, 1),
                        21)
        );
    }


}
