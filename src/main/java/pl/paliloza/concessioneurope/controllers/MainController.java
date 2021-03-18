package pl.paliloza.concessioneurope.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.paliloza.concessioneurope.entity.Order;
import pl.paliloza.concessioneurope.services.OrderService;
import pl.paliloza.concessioneurope.services.ProcessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public String viewMainModel(Model model, HttpSession httpSession) {
        List<String> messages = (List<String>) httpSession.getAttribute("orderSess");

        if(messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessager", messages);
        for (String message : messages) {
            System.out.println(message + "dupa");
        }
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "index";
    }

    @PostMapping("/")

    public String addNewOrder(Order order,@RequestParam String processName, HttpServletRequest request) {
        List<String> messages = (List<String>) request.getSession().getAttribute("orderSess");
        if(messages == null){
            messages = new ArrayList<>();
            request.getSession().setAttribute("sessionMessager",messages);
        }
        messages.add(processName);
        request.getSession().setAttribute("sessionMessager",messages);

        for (String message : messages) {
            System.out.println(message);
        }


//        Prmeocesses processByNa = processService.getProcessByName(processName);
//        Set<Processes> newProcessList = processService.getNewProcessList(processByName);
        orderService.add(order);
        return "redirect:/";
    }

}

