package pl.paliloza.concessioneurope.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.paliloza.concessioneurope.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDAO extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);
    @Query(value = "select * from orders_table order by id desc",nativeQuery = true)
    List<Order> findAllDesc();
}
