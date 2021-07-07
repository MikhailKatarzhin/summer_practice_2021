package summer2021.mathelementaryschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PagesController {

    @GetMapping
    public String defaultPage(){
        return "signin";
    }
    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }
    @GetMapping("/signin")
    public String signinPage(){
        return "signin";
    }
    @GetMapping("/menu")
    public String getMenuPage(){
        return "menu";
    }
    /*@GetMapping
    public String getGamePage(){
        return GAMEPAGE;*/

}
