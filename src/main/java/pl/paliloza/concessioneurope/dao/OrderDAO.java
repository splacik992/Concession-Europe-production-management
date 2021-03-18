package pl.paliloza.concessioneurope.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.paliloza.concessioneurope.entity.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order,Long> {
}
