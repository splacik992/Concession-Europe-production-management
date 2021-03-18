package pl.paliloza.concessioneurope.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.paliloza.concessioneurope.entity.Processes;

import java.util.List;

@Repository
public interface ProcessesDAO extends JpaRepository<Processes,Long> {
    @Query(value = "select * from processes",nativeQuery = true)
    List<Processes> getAll();
    Processes findByName(String name);
}
