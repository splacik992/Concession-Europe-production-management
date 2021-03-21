package pl.paliloza.concessioneurope.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class PlanOfTheDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOfProcess;
    @OneToMany
    private List<Order> orderList;

    public String getNameOfProcess() {
        return nameOfProcess;
    }

    public void setNameOfProcess(String nameOfProcess) {
        this.nameOfProcess = nameOfProcess;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
