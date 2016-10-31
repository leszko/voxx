package com.rafalleszko.voxx;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExchangeControllerTest {
    private ExchangeController controller = new ExchangeController();

    @Test
    public void Should_ReturnOne_When_Usd() {
        // given
        String currency = "usd";

        // when
        String result = controller.exchangeRate(currency);

        // then
        assertEquals("1.00", result);
    }

    @Test
    public void Should_ReturnNotSupported_When_Mad() {
        // given
        String currency = "mad";

        // when
        String result = controller.exchangeRate(currency);

        // then
        assertEquals("not supported", result);
    }
}
