package pl.paliloza.concessioneurope.services;

import org.springframework.stereotype.Service;
import pl.paliloza.concessioneurope.dao.OrderDAO;
import pl.paliloza.concessioneurope.dao.OrderStatusDAO;
import pl.paliloza.concessioneurope.dao.ProcessesDAO;
import pl.paliloza.concessioneurope.entity.Order;
import pl.paliloza.concessioneurope.entity.OrderStatus;
import pl.paliloza.concessioneurope.entity.Processes;

import java.time.LocalDate;
import java.util.*;

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

    public List<String> orderShow(){
        List<OrderStatus> orderStatuses = orderStatusDAO.findAll();
        List<String> statuses = new ArrayList<>();
        for (OrderStatus orderStatus : orderStatuses) {
            statuses.add(orderStatus.getName());
        }
        return statuses;
    }

    public List<String> processesShow(){
        List<Processes> processes = processesDAO.findAll();
        List<String> processesList = new ArrayList<>();
        for (Processes process : processes) {
            processesList.add(process.getName());
        }
        return processesList;
    }

    public void add(Order order, List<Processes> newProcessList) {
        order.setProcesses(newProcessList);
        order.setDate(LocalDate.now());
        orderDAO.save(order);
    }

    public List<Order> getAllOrders(){
        return orderDAO.findAll();
    }


}
