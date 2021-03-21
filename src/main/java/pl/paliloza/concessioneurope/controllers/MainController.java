package pl.paliloza.concessioneurope.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.paliloza.concessioneurope.entity.Order;
import pl.paliloza.concessioneurope.entity.Processes;
import pl.paliloza.concessioneurope.services.OrderService;
import pl.paliloza.concessioneurope.services.ProcessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Scope("session")
public class MainController {
    private final OrderService orderService;
    private final ProcessService processService;


    public MainController(OrderService orderService, ProcessService processService) {
        this.orderService = orderService;
        this.processService = processService;
    }

    @GetMapping("/")
    public String viewMainModel(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "index";
    }

    @GetMapping("/pila")
    public String viewSawPage(Model model) {
        model.addAttribute("sawOrders",orderService.getAllOrdersByName("Piła panelowa"));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "panelSaw";
    }
    @PostMapping("/post")
    public String viewSawPagePost(@RequestParam String id){
        return "redirect:/pila";
    }

    @PostMapping("/")
    public String addNewOrder(Order order,@RequestParam String orderListener) {
        System.out.println(orderListener);
        String[] split = orderListener.split(",");
        List<Processes> newProcessList = new ArrayList<>();
        for (String s : split) {
            System.out.println(s);
            Processes processByNa = processService.getProcessByName(s);
            newProcessList.add(processByNa);
        }

        orderService.add(order,newProcessList);
        return "redirect:/";
    }

}

