package pl.paliloza.concessioneurope.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.paliloza.concessioneurope.dao.OrderDAO;
import pl.paliloza.concessioneurope.dao.OrderStatusDAO;
import pl.paliloza.concessioneurope.dao.ProcessesDAO;
import pl.paliloza.concessioneurope.entity.Order;
import pl.paliloza.concessioneurope.entity.OrderStatus;
import pl.paliloza.concessioneurope.entity.Processes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    private final OrderStatusDAO orderStatusDAO;
    private final ProcessesDAO processesDAO;
    private final OrderDAO orderDAO;

    public OrderService(OrderStatusDAO orderStatusDAO, ProcessesDAO processesDAO, OrderDAO orderDAO) {
        this.orderStatusDAO = orderStatusDAO;
        this.processesDAO = processesDAO;
        this.orderDAO = orderDAO;
    }

    public List<String> orderShow() {
        List<OrderStatus> orderStatuses = orderStatusDAO.findAll();
        List<String> statuses = new ArrayList<>();
        for (OrderStatus orderStatus : orderStatuses) {
            statuses.add(orderStatus.getName());
        }
        return statuses;
    }

    public List<String> processesShow() {
        List<Processes> processes = processesDAO.findAll();
        List<String> processesList = new ArrayList<>();
        for (Processes process : processes) {
            processesList.add(process.getName());
        }
        return processesList;
    }

    public void add(Order order, List<Processes> newProcessList) {
        OrderStatus orderStatus = orderStatusDAO.findByName("W trakcie");
        order.setOrderStatus(orderStatus);
        order.setProcesses(newProcessList);
        order.setNotes("");
        order.setDate(LocalDate.now());
        orderDAO.save(order);
    }

    public List<Order> getAllOrders() {
        return orderDAO.findAllDesc();
    }

    public Object getAllOrdersByName(String name) {
        List<Order> allOrders = orderDAO.findAll();
        List<Order> ordersByName = new ArrayList<>();
        for (Order allOrder : allOrders) {
            if (allOrder.getProcesses().get(0).getName().equals(name)) {
                ordersByName.add(allOrder);
            }
        }
        Collections.reverse(ordersByName);
        return ordersByName;
    }

    public void goToAnotherStep(String nextStep, String id) {
            Order byId = orderDAO.findById(Long.valueOf(id)).get();
            List<Processes> processes = byId.getProcesses();
            int index = processes.indexOf(processesDAO.findByName(nextStep));
            List<Processes> processesListAfterChange = new ArrayList<>(processes.subList(index, processes.size()));
            processes.add(processesDAO.findByName(nextStep));
            byId.setProcesses(processesListAfterChange);
            orderDAO.save(byId);
    }

    @Transactional
    public void goToAnotherStepPop(String nextStep, String id) {
        Order order = orderDAO.findById(Long.valueOf(id)).get();
        List<Processes> processes = order.getProcesses();
        int index = processes.indexOf(processesDAO.findByName(nextStep));
        System.out.println(index);
        if(index == -1){
            Processes processNew = processesDAO.findByName(nextStep);
            processes.add(0,processNew);
            orderDAO.save(order);
        }else {
            List<Processes> processesListAfterChange = new ArrayList<>(processes.subList(index, processes.size()));
            processes.add(processesDAO.findByName(nextStep));
            order.setProcesses(processesListAfterChange);
        }
    }

    public List<Order> listAll(){
        return orderDAO.findAll(Sort.by("date").ascending());
    }


    public void commentsAdd(String comments, String id) {
        Order order = orderDAO.findById(Long.valueOf(id)).get();
        order.setNotes(comments);
        orderDAO.save(order);
    }

    public void commentsRemove(String id) {
        Order order = orderDAO.findById(Long.valueOf(id)).get();
        order.setNotes("");
        orderDAO.save(order);
    }
}
