package pl.paliloza.concessioneurope.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.paliloza.concessioneurope.entity.PlanOfTheDay;

@Repository
public interface PlanOfTheDayDAO extends JpaRepository<PlanOfTheDay,Long> {

}
