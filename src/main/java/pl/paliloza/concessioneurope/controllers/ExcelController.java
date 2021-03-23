package pl.paliloza.concessioneurope.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.paliloza.concessioneurope.entity.Order;
import pl.paliloza.concessioneurope.services.ExcelService;
import pl.paliloza.concessioneurope.services.OrderService;
import pl.paliloza.concessioneurope.utils.OrderToExcelExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ExcelController {

    private final OrderService orderService;
    private final ExcelService excelService;

    public ExcelController(OrderService orderService, ExcelService excelService) {
        this.orderService = orderService;
        this.excelService = excelService;
    }

    @PostMapping("/order/import/excel")
    public String uploadMultipartFile(@RequestParam("uploadFileUrl") MultipartFile file, Model model) {
        try {
            excelService.save(file);
            model.addAttribute("message", "File uploaded successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
        }
        return "redirect:/";
    }

    @GetMapping("/order/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Order> listUsers = orderService.listAll();

        OrderToExcelExporter excelExporter = new OrderToExcelExporter(listUsers);

        excelExporter.export(response);
    }


//    @PostMapping("/order/import/excel")
//    public String uploadFile(Model model, MultipartFile file) throws IOException {
//        InputStream in = file.getInputStream();
//        File currDir = new File(".");
//        String path = currDir.getAbsolutePath();
//        fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
//        FileOutputStream f = new FileOutputStream(fileLocation);
//        int ch = 0;
//        while ((ch = in.read()) != -1) {
//            f.write(ch);
//        }
//        f.flush();
//        f.close();
//        model.addAttribute("message", "File: " + file.getOriginalFilename()
//                + " has been uploaded successfully!");
//        return "/";
//    }
}
