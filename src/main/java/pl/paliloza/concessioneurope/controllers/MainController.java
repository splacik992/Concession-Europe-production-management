package pl.paliloza.concessioneurope.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.paliloza.concessioneurope.entity.Order;
import pl.paliloza.concessioneurope.services.OrderServices;

@Controller
public class MainController {
    private final OrderServices orderServices;

    public MainController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping("/")
    public String viewMainModel(Model model){
        model.addAttribute("orders" , orderServices.getAllOrders());
        model.addAttribute("statuses",orderServices.orderShow());
        model.addAttribute("processesList",orderServices.processesShow());
        model.addAttribute("order", new Order());
        return "index";
    }

    @PostMapping("/")
    public String addNewOrder(Order order){
        orderServices.add(order);
        return "redirect:/";
    }

}

