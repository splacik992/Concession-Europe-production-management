package pl.paliloza.concessioneurope.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @GetMapping
    public String viewMainModel(){

        return "index";
    }
}
