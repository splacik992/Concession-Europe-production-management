package pl.paliloza.concessioneurope.services;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import pl.paliloza.concessioneurope.dao.OrderDAO;
import pl.paliloza.concessioneurope.dao.PlanOfTheDayDAO;
import pl.paliloza.concessioneurope.entity.Order;
import pl.paliloza.concessioneurope.entity.PlanOfTheDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanOfTheDayService {
    private final PlanOfTheDayDAO planOfTheDayDAO;
    private final OrderDAO orderDAO;

    public PlanOfTheDayService(PlanOfTheDayDAO planOfTheDayDAO, OrderDAO orderDAO) {
        this.planOfTheDayDAO = planOfTheDayDAO;
        this.orderDAO = orderDAO;
    }

    public List<Order> showAllPlansByName(String name){
        PlanOfTheDay allByNameOfProcess = planOfTheDayDAO.findByNameOfProcess(name);
        List<Order> orderList;
        if(allByNameOfProcess == null){
            orderList = new ArrayList<>();
        }else{
            orderList = allByNameOfProcess.getOrderList();
        }
        return orderList;
    }

    public void addToTheList(String id, String name) {
        PlanOfTheDay planOfTheDay;
        if(planOfTheDayDAO.findByNameOfProcess(name) == null){
            planOfTheDay = new PlanOfTheDay();
        }else {
            planOfTheDay = planOfTheDayDAO.findByNameOfProcess(name);
        }
        Order order = orderDAO.findById(Long.valueOf(id)).get();
        planOfTheDay.setNameOfProcess(name);
        List<Order> orderList;
        if(planOfTheDay.getOrderList() == null){
            orderList = new ArrayList<>();
        }else {
            orderList = planOfTheDay.getOrderList();
        }
        orderList.add(order);
        planOfTheDay.setOrderList(orderList);
        planOfTheDayDAO.save(planOfTheDay);
    }
}
