package pl.paliloza.concessioneurope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.paliloza.concessioneurope.dao.OrderDAO;
import pl.paliloza.concessioneurope.entity.Order;
import pl.paliloza.concessioneurope.utils.ExcelHelper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    OrderDAO orderDAO;

    public void save(MultipartFile file) {
        try {
            List<Order> tutorials = ExcelHelper.excelToOrders(file.getInputStream());
            orderDAO.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Order> orders = orderDAO.findAll();

        ByteArrayInputStream in = ExcelHelper.ordersToExcel(orders);
        return in;
    }

    public List<Order> getAllTutorials() {
        return orderDAO.findAll();
    }
}