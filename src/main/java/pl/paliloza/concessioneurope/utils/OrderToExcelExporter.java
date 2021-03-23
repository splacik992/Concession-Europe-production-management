package pl.paliloza.concessioneurope.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.paliloza.concessioneurope.entity.Order;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class OrderToExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Order> listOrders;

    public OrderToExcelExporter(List<Order> listOrders) {
        this.listOrders = listOrders;
        workbook = new XSSFWorkbook();
    }

    public void writeHeaderLine() {
        sheet = workbook.createSheet("Orders");

        Row row = sheet.createRow(0);
        CellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        createCell(row, 0, "nr", cellStyle);
        createCell(row, 1, "Nazwa klienta", cellStyle);
        createCell(row, 2, "data", cellStyle);
        createCell(row, 3, "Materiał", cellStyle);
        createCell(row, 4, "Wystawione", cellStyle);
        createCell(row, 5, "Produkt nazwa", cellStyle);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle cellStyle) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof LocalDate) {
            cell.setCellValue(String.valueOf((LocalDate) value));
        } else {
            cell.setCellValue((String) value);
        }
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Order order : listOrders) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, order.getId(), style);
            createCell(row, columnCount++, order.getClientName(), style);
            createCell(row, columnCount++, order.getDate(), style);
            createCell(row, columnCount++, order.getMaterialName(), style);
            createCell(row, columnCount++, order.getPrincipal(), style);
            createCell(row, columnCount++, order.getProductName(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}

