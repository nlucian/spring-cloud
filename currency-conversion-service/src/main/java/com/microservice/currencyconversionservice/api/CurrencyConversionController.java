package com.microservice.currencyconversionservice.api;


import com.microservice.currencyconversionservice.client.CurrencyExchangeServiceProxy;
import com.microservice.currencyconversionservice.entity.CurrencyConversionBean;
import com.microservice.currencyconversionservice.service.CurrencyConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * We have a service convertFeignCurrency which calls the method backed by feign client,
 * and another rest service convertCurrency which calls the service which is using spring rest template.
 *
 */
@RestController
@RequestMapping(path = "/currency-converter")
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyConversionService currencyConversionService;

    @Autowired
    private CurrencyExchangeServiceProxy exchangeServiceProxy;

    @GetMapping(path = "/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        ResponseEntity<CurrencyConversionBean> bean = currencyConversionService.getConversionRate(from, to);
        CurrencyConversionBean currencyConversionBean = bean.getBody();
        return new CurrencyConversionBean(1L, from, to, currencyConversionBean.getConversionMultiple(), quantity,
                quantity.multiply(currencyConversionBean.getConversionMultiple()),
                currencyConversionBean.getPort());
    }

    @GetMapping(path = "/feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertFeignCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionBean currencyConversionBean = exchangeServiceProxy.retrieveExchangeValue(from, to);
        logger.info("{}", currencyConversionBean);
        return new CurrencyConversionBean(1L, from, to, currencyConversionBean.getConversionMultiple(), quantity,
                quantity.multiply(currencyConversionBean.getConversionMultiple()),
                currencyConversionBean.getPort());
    }
}
