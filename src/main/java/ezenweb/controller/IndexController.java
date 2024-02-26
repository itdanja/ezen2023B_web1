package ezenweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")// localhost:80
    public String Index(){
        return "ezenweb/index";
    }
}
