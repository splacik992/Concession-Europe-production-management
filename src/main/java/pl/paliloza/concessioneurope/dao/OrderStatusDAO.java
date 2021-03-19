package pl.paliloza.concessioneurope.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.paliloza.concessioneurope.entity.OrderStatus;

import java.util.List;

@Repository
public interface OrderStatusDAO extends JpaRepository<OrderStatus,Long> {
    @Query(value ="select * from order_status" ,nativeQuery = true)
    List<OrderStatus> findAll();
    OrderStatus findByName(String name);
}
