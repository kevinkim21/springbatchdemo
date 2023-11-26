package com.example.batchdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {
    private final ApiFetcher apiFetcher;
    private final ExcelFileWriter excelFileWriter;

    private final static String filePath = "src/main/resources/data/payments.xlsx";

    public void fetchPayments(String from, String to) {
        try {
            // ApiListFetcher를 사용하여 Payment 데이터 가져오기
            List<Payment> payments =  apiFetcher.fetchItems("http://localhost:8080/api/v1/payments", from, to);
            // ExcelFileWriter를 사용하여 데이터를 엑셀 파일로 저장
            excelFileWriter.appendPaymentsToExcel(payments, filePath);
        } catch (IOException e) {
            // 파일 작성 중 오류 처리
            e.printStackTrace();
        }
    }

    public Payment fetchPayment(String paymentId) {
        return apiFetcher.fetchItem("http://localhost:8080/api/v1/payments", paymentId);
    }
}
