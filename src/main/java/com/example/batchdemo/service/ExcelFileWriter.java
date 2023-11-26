package com.example.batchdemo.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class ExcelFileWriter {

    public void appendPaymentsToExcel(List<Payment> payments, String filePath) throws IOException {
        Workbook workbook;
        Sheet sheet;
        int rowNum;

        // 파일이 존재하는지 확인하고, 존재할 경우 해당 파일을 엽니다.
        File file = new File(filePath);
        if (file.exists()) {
            InputStream is = new FileInputStream(file);
            workbook = WorkbookFactory.create(is);
            sheet = workbook.getSheetAt(0);
            rowNum = sheet.getLastRowNum() + 1;
        } else {
            // 파일이 존재하지 않을 경우 새로운 파일을 생성합니다.
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Payments");
            createHeaderRow(sheet);
            rowNum = 1;
        }

        // 데이터 행 추가
        for (Payment payment : payments) {
            Row row = sheet.createRow(rowNum++);
            fillPaymentRow(row, payment);
        }

        // 각 열의 크기를 자동 조정
        for (int i = 0; i < 15; i++) {
            sheet.autoSizeColumn(i);
        }

        // 파일 저장
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }

    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Amount", "Currency", "Method", "Intent", "Description", "Status", "Created", "Updated", "Customer", "Invoice Number", "Receipt Number", "Receipt URL", "Link Self", "Link Actions"};
        for (int i = 0; i < columns.length; i++) {
            headerRow.createCell(i).setCellValue(columns[i]);
        }
    }

    private void fillPaymentRow(Row row, Payment payment) {
        row.createCell(0).setCellValue(payment.getId());
        row.createCell(1).setCellValue(payment.getAmount());
        row.createCell(2).setCellValue(payment.getCurrency());
        row.createCell(3).setCellValue(payment.getMethod());
        row.createCell(4).setCellValue(payment.getIntent());
        row.createCell(5).setCellValue(payment.getDescription());
        row.createCell(6).setCellValue(payment.getStatus());
        row.createCell(7).setCellValue(payment.getCreated());
        row.createCell(8).setCellValue(payment.getUpdated());
        row.createCell(9).setCellValue(payment.getCustomer());
        row.createCell(10).setCellValue(payment.getInvoiceNumber());
        row.createCell(11).setCellValue(payment.getReceiptNumber());
        row.createCell(12).setCellValue(payment.getReceiptUrl());
        row.createCell(13).setCellValue(payment.getLinkSelf());
        row.createCell(14).setCellValue(payment.getLinkActions());
    }
}
