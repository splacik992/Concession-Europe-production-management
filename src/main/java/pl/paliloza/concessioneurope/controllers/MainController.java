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
            orderService.commentsRemove(id);
            planOfTheDayService.removeFromTheList(id, "Piła panelowa");
        }
        return "redirect:/pila";
    }

    @PostMapping("/pilaSelectPop")
    public String sawPagePostSelectPop(@RequestParam String nextStep, @RequestParam String id, @RequestParam String comments){
            orderService.goToAnotherStepPop(nextStep, id);
            orderService.commentsAdd(comments,id);
            planOfTheDayService.removeFromTheList(id, "Piła panelowa");

        return "redirect:/pila";
    }
    // ===================================================================================

    //OKLEINIARKA ====================================================================================
    @GetMapping("/edgebander")
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
        return "redirect:/edgebander";
    }

    @PostMapping("/edgebanderRemove")
    public String edgebanderRemove(@RequestParam String id){
        planOfTheDayService.removeFromTheList(id,"Okleiniarka");
        return "redirect:/edgebander";
    }

    @PostMapping("/edgebanderSelect")
    public String edgebanderSelect(@RequestParam String nextStep, @RequestParam String id){
        if(!nextStep.equals("Zgłoś uwagi")) {
            orderService.goToAnotherStep(nextStep, id);
            orderService.commentsRemove(id);
            planOfTheDayService.removeFromTheList(id, "Okleiniarka");
        }
        return "redirect:/edgebander";
    }
    @PostMapping("/edgebanderSelectPop")
    public String edgebanderSelectPop(@RequestParam String nextStep, @RequestParam String id, @RequestParam String comments){
        orderService.goToAnotherStepPop(nextStep, id);
        orderService.commentsAdd(comments, id);
        planOfTheDayService.removeFromTheList(id, "Okleiniarka");

        return "redirect:/edgebander";
    }
    // ===================================================================================

    //CNC ====================================================================================
    @GetMapping("/cnc")
    public String viewCNC(Model model) {
        model.addAttribute("cncOrdersPerDay",planOfTheDayService.showAllPlansByName("CNC"));
        model.addAttribute("cncOrders",orderService.getAllOrdersByName("CNC"));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "cnc";
    }

    @PostMapping("/cncAdd")
    public String cncAdd(@RequestParam String id){
        planOfTheDayService.addToTheList(id,"CNC");
        return "redirect:/cnc";
    }

    @PostMapping("/cncRemove")
    public String cncRemove(@RequestParam String id){
        planOfTheDayService.removeFromTheList(id,"CNC");
        return "redirect:/cnc";
    }

    @PostMapping("/cncSelect")
    public String cncSelect(@RequestParam String nextStep, @RequestParam String id){
        if(!nextStep.equals("Zgłoś uwagi")) {
            orderService.goToAnotherStep(nextStep, id);
            orderService.commentsRemove(id);
            planOfTheDayService.removeFromTheList(id, "CNC");
        }
        return "redirect:/cnc";
    }
    @PostMapping("/cncSelectPop")
    public String cncSelectPop(@RequestParam String nextStep, @RequestParam String id, @RequestParam String comments){
        orderService.goToAnotherStepPop(nextStep, id);
        orderService.commentsAdd(comments, id);
        planOfTheDayService.removeFromTheList(id, "CNC");

        return "redirect:/cnc";
    }

    //Press ====================================================================================
    @GetMapping("/press")
    public String viewPress(Model model) {
        model.addAttribute("pressOrdersPerDay",planOfTheDayService.showAllPlansByName("Prasa"));
        model.addAttribute("pressOrders",orderService.getAllOrdersByName("Prasa"));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "press";
    }

    @PostMapping("/pressAdd")
    public String pressAdd(@RequestParam String id){
        planOfTheDayService.addToTheList(id,"Prasa");
        return "redirect:/press";
    }

    @PostMapping("/pressRemove")
    public String pressRemove(@RequestParam String id){
        planOfTheDayService.removeFromTheList(id,"Prasa");
        return "redirect:/cnc";
    }

    @PostMapping("/pressSelect")
    public String pressSelect(@RequestParam String nextStep, @RequestParam String id){
        if(!nextStep.equals("Zgłoś uwagi")) {
            orderService.goToAnotherStep(nextStep, id);
            orderService.commentsRemove(id);
            planOfTheDayService.removeFromTheList(id, "Prasa");
        }
        return "redirect:/press";
    }
    @PostMapping("/pressSelectPop")
    public String pressSelectPop(@RequestParam String nextStep, @RequestParam String id, @RequestParam String comments){
        orderService.goToAnotherStepPop(nextStep, id);
        orderService.commentsAdd(comments, id);
        planOfTheDayService.removeFromTheList(id, "Prasa");

        return "redirect:/press";
    }

    // Szlifiernia ======================================================
    @GetMapping("/grindery")
    public String viewGrindery(Model model) {
        model.addAttribute("grinderyOrdersPerDay",planOfTheDayService.showAllPlansByName("Szlifiernia"));
        model.addAttribute("grinderyOrdersDesc",orderService.getAllOrdersByName("Szlifiernia"));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "grindery";
    }
    @PostMapping("/grinderyAdd")
    public String grinderyPostAdd(@RequestParam String id){
        planOfTheDayService.addToTheList(id,"Szlifiernia");
        return "redirect:/grindery";
    }

    @PostMapping("/grinderyRemove")
    public String grinderyPostRemove(@RequestParam String id){
        planOfTheDayService.removeFromTheList(id,"Szlifiernia");
        return "redirect:/grindery";
    }

    @PostMapping("/grinderySelect")
    public String grinderyPostSelect(@RequestParam String nextStep, @RequestParam String id){
        if(!nextStep.equals("Zgłoś uwagi")) {
            orderService.goToAnotherStep(nextStep, id);
            orderService.commentsRemove(id);
            planOfTheDayService.removeFromTheList(id, "Szlifiernia");
        }
        return "redirect:/grindery";
    }

    @PostMapping("/grinderySelectPop")
    public String grinderyPostSelectPop(@RequestParam String nextStep, @RequestParam String id, @RequestParam String comments){
        orderService.goToAnotherStepPop(nextStep, id);
        orderService.commentsAdd(comments,id);
        planOfTheDayService.removeFromTheList(id, "Szlifiernia");

        return "redirect:/grindery";
    }
    // ===================================================================================

    // Szlifiernia ======================================================
    @GetMapping("/paint")
    public String viewPaint(Model model) {
        model.addAttribute("paintOrdersPerDay",planOfTheDayService.showAllPlansByName("Lakiernia"));
        model.addAttribute("paintOrdersDesc",orderService.getAllOrdersByName("Lakiernia"));
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("statuses", orderService.orderShow());
        model.addAttribute("processesList", orderService.processesShow());
        model.addAttribute("order", new Order());
        return "paint";
    }
    @PostMapping("/paintAdd")
    public String paintPostAdd(@RequestParam String id){
        planOfTheDayService.addToTheList(id,"Lakiernia");
        return "redirect:/paint";
    }

    @PostMapping("/paintRemove")
    public String paintPostRemove(@RequestParam String id){
        planOfTheDayService.removeFromTheList(id,"Lakiernia");
        return "redirect:/paint";
    }

    @PostMapping("/paintSelect")
    public String paintPostSelect(@RequestParam String nextStep, @RequestParam String id){
        if(!nextStep.equals("Zgłoś uwagi")) {
            orderService.goToAnotherStep(nextStep, id);
            orderService.commentsRemove(id);
            planOfTheDayService.removeFromTheList(id, "Lakiernia");
        }
        return "redirect:/paint";
    }

    @PostMapping("/paintSelectPop")
    public String paintPostSelectPop(@RequestParam String nextStep, @RequestParam String id, @RequestParam String comments){
        orderService.goToAnotherStepPop(nextStep, id);
        orderService.commentsAdd(comments,id);
        planOfTheDayService.removeFromTheList(id, "Lakiernia");

        return "redirect:/paint";
    }
    // ===================================================================================
}


