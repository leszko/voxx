package com.rafalleszko.voxx;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

    @RequestMapping(value = "/{currency}/exchange-rate",
            method = RequestMethod.GET)
    public String exchangeRate(@PathVariable String currency) {
        if ("usd".equalsIgnoreCase(currency)) {
            return "1.00";
        } else {
            return "not supported";
        }
    }
}
