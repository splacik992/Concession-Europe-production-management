package pl.paliloza.concessioneurope.services;

import org.springframework.stereotype.Service;
import pl.paliloza.concessioneurope.dao.OrderStatusDAO;
import pl.paliloza.concessioneurope.entity.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServices {
    private final OrderStatusDAO orderStatusDAO;

    public OrderServices(OrderStatusDAO orderStatusDAO) {
        this.orderStatusDAO = orderStatusDAO;
    }

    public List<String> ordershow(){
        List<OrderStatus> orderStatuses = orderStatusDAO.findAll();
        List<String> statuses = new ArrayList<>();
        for (OrderStatus orderStatus : orderStatuses) {
            statuses.add(orderStatus.getName());
        }
        return statuses;
    }
}
