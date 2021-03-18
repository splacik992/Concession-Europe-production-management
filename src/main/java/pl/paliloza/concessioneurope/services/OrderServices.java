package pl.paliloza.concessioneurope.services;

import org.springframework.stereotype.Service;
import pl.paliloza.concessioneurope.dao.OrderStatusDAO;
import pl.paliloza.concessioneurope.dao.ProcessesDAO;
import pl.paliloza.concessioneurope.entity.OrderStatus;
import pl.paliloza.concessioneurope.entity.Processes;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServices {
    private final OrderStatusDAO orderStatusDAO;
    private final ProcessesDAO processesDAO;

    public OrderServices(OrderStatusDAO orderStatusDAO, ProcessesDAO processesDAO) {
        this.orderStatusDAO = orderStatusDAO;
        this.processesDAO = processesDAO;
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
}
