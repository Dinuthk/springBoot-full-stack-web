package springbootacademy.security.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/longs")
@CrossOrigin
public class LongController {
    @GetMapping("/my-long")
    public String getLongDetails(){
        return "long details";
    }
}
