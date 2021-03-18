package pl.paliloza.concessioneurope.services;

import org.springframework.stereotype.Service;
import pl.paliloza.concessioneurope.dao.ProcessesDAO;
import pl.paliloza.concessioneurope.entity.Processes;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProcessService {

    private final ProcessesDAO processesDAO;

    public ProcessService(ProcessesDAO processesDAO) {
        this.processesDAO = processesDAO;
    }

    public Processes getProcessByName(String process){
        return processesDAO.findByName(process);
    }

    public Set<Processes> getNewProcessList (Processes processes){
        Set<Processes> processSet = new HashSet<>();
        processSet.add(processes);
        return processSet;
    }
}
