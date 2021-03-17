package pl.paliloza.concessioneurope.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.paliloza.concessioneurope.entity.OrderStatus;

public interface OrderStatusDAO extends JpaRepository<OrderStatus,Long> {

}
