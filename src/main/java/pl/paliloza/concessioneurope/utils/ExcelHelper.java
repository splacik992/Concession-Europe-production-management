package pl.paliloza.concessioneurope.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import pl.paliloza.concessioneurope.entity.Order;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Nazwa klienta", "data", "Materiał", "Wystawione", "Produkt nazwa" };
    static String SHEET = "Orders";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static ByteArrayInputStream ordersToExcel(List<Order> orders) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Order order : orders) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(order.getId());
                row.createCell(1).setCellValue(order.getClientName());
                row.createCell(2).setCellValue(String.valueOf(order.getDate()));
                row.createCell(3).setCellValue(order.getMaterialName());
                row.createCell(4).setCellValue(order.getPrincipal());
                row.createCell(5).setCellValue(order.getProductName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Order> excelToOrders(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Order> orders = new ArrayList<Order>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Order order = new Order();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            order.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            order.setClientName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            order.setDate(LocalDate.parse(currentCell.getStringCellValue()));
                            break;

                        case 3:
                            order.setMaterialName(currentCell.getStringCellValue());
                            break;
                        case 4:
                            order.setPrincipal(currentCell.getStringCellValue());
                        case 5:
                            order.setProductName(currentCell.getStringCellValue());
                        default:
                            break;
                    }

                    cellIdx++;
                }

                orders.add(order);
            }

            workbook.close();

            return orders;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
