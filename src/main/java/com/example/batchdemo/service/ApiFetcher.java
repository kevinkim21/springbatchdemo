package com.example.batchdemo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiFetcher {
    private final RestTemplate restTemplate;
    private static final int PAGE_SIZE = 100;

    public <T> List<T> fetchItems(String baseUrl, String from, String to) {
        List<T> allItems = new ArrayList<>();
        int pageIndex = 0;
        boolean moreData = true;

        while (moreData) {
            String url = String.format("%s?from=%s&to=%s&index=%d&size=%d", baseUrl, from, to, pageIndex, PAGE_SIZE);
            ParameterizedTypeReference<List<T>> responseType = new ParameterizedTypeReference<>() {};
            ResponseEntity<List<T>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

            if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
                List<T> items = response.getBody();

                if (items.isEmpty()) {
                    moreData = false;
                } else {
                    allItems.addAll(items);
                    pageIndex++;
                }
            } else {
                throw new RuntimeException("API 요청 실패: " + response.getStatusCode());
            }
        }
        return allItems;
    }

    public <T> T fetchItem(String baseUrl, String paymentId) {
        String url = String.format("%s?paymentId=%s", baseUrl, paymentId);
        ParameterizedTypeReference<T> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
            T item = response.getBody();
            return item;
        } else {
            throw new RuntimeException("API 요청 실패: " + response.getStatusCode());
        }
    }
}
