package pl.paliloza.concessioneurope.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.paliloza.concessioneurope.services.OrderServices;

@Controller
public class MainController {
    private final OrderServices orderServices;

    public MainController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping("/")
    public String viewMainModel(Model model){
        model.addAttribute("statuses",orderServices.ordershow());
        return "index";
    }

}

