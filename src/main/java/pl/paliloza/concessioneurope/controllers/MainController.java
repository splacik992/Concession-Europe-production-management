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
import pl.paliloza.concessioneurope.services.PlanOfTheDayService;
import pl.paliloza.concessioneurope.services.ProcessService;
import pl.paliloza.concessioneurope.utils.OrderToExcelExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("session")
public class MainController {
    private final OrderService orderService;
    private final ProcessService processService;
    private final PlanOfTheDayService planOfTheDayService;


    public MainController(OrderService orderService, ProcessService processService, PlanOfTheDayService planOfTheDayService) {
        this.orderService = orderService;
        this.processService = processService;
        this.planOfTheDayService = planOfTheDayService;
    }
    //PANEL GŁÓWNY ==================================================================
    @GetMapping("/")
    public String viewMainModel(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "index";
    }
    @PostMapping("/")
    public String addNewOrder(Order order,@RequestParam String orderListener) {
        String[] split = orderListener.split(",");
        List<Processes> newProcessList = new ArrayList<>();
        for (String s : split) {
            Processes processByNa = processService.getProcessByName(s);
            newProcessList.add(processByNa);
        }

        orderService.add(order,newProcessList);
        return "redirect:/";
    }
    //========================================================================


    // PIŁA PANELOWA ======================================================
    @GetMapping("/pila")
    public String viewSawPage(Model model) {
        model.addAttribute("sawOrdersPerDay",planOfTheDayService.showAllPlansByName("Piła panelowa"));
        model.addAttribute("sawOrdersDesc",orderService.getAllOrdersByName("Piła panelowa"));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "panelSaw";
    }
    @PostMapping("/pilaAdd")
    public String sawPagePostAdd(@RequestParam String id){
        planOfTheDayService.addToTheList(id,"Piła panelowa");
        return "redirect:/pila";
    }

    @PostMapping("/pilaRemove")
    public String sawPagePostRemove(@RequestParam String id){
        planOfTheDayService.removeFromTheList(id,"Piła panelowa");
        return "redirect:/pila";
    }

    @PostMapping("/pilaSelect")
    public String sawPagePostSelect(@RequestParam String nextStep, @RequestParam String id){
        if(!nextStep.equals("Zgłoś uwagi")) {
            orderService.goToAnotherStep(nextStep, id);
            planOfTheDayService.removeFromTheList(id, "Piła panelowa");
        }
        return "redirect:/pila";
    }

    @PostMapping("/pilaSelectPop")
    public String sawPagePostSelectPop(@RequestParam String nextStep, @RequestParam String id){
            orderService.goToAnotherStepPop(nextStep, id);
            planOfTheDayService.removeFromTheList(id, "Piła panelowa");

        return "redirect:/pila";
    }
    // ===================================================================================

    //OKLEINIARKA ====================================================================================
    @GetMapping("/okleiniarka")
    public String viewEdgebander(Model model) {
        model.addAttribute("edgebanderOrdersPerDay",planOfTheDayService.showAllPlansByName("Okleiniarka"));
        model.addAttribute("edgebanderOrders",orderService.getAllOrdersByName("Okleiniarka"));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "edgebander";
    }

    @PostMapping("/edgebanderAdd")
    public String edgebanderAdd(@RequestParam String id){
        planOfTheDayService.addToTheList(id,"Okleiniarka");
        return "redirect:/okleiniarka";
    }

    @PostMapping("/edgebanderRemove")
    public String edgebanderRemove(@RequestParam String id){
        planOfTheDayService.removeFromTheList(id,"Okleiniarka");
        return "redirect:/okleiniarka";
    }

    @PostMapping("/edgebanderSelect")
    public String edgebanderSelect(@RequestParam String nextStep, @RequestParam String id){
        orderService.goToAnotherStep(nextStep,id);
        planOfTheDayService.removeFromTheList(id,"Okleiniarka");
        return "redirect:/okleiniarka";
    }

    // ===================================================================================


}


