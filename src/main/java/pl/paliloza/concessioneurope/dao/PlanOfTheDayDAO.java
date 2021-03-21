package pl.paliloza.concessioneurope.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.paliloza.concessioneurope.entity.PlanOfTheDay;

import java.util.List;

@Repository
public interface PlanOfTheDayDAO extends JpaRepository<PlanOfTheDay,Long> {
    PlanOfTheDay findByNameOfProcess(String name);
}
