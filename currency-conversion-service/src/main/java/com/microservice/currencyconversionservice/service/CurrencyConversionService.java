package com.microservice.currencyconversionservice.service;

import com.microservice.currencyconversionservice.entity.CurrencyConversionBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService {

    public ResponseEntity<CurrencyConversionBean> getConversionRate(String from, String to) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}/",
                CurrencyConversionBean.class, uriVariables);
        return responseEntity;
    }
}
