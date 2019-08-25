package cz.bscideas.paymenttracker.domain;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PaymentTest {

    @Test
    public void create() throws Exception {
        String currency = "USD";
        BigDecimal amount = new BigDecimal("1234");

        Payment payment = Payment.create(currency, amount);

        Assert.assertEquals(currency, payment.currency());
        Assert.assertEquals(amount, payment.amount());
    }

    @Test
    public void equals() throws Exception {
        Payment p1 = Payment.create("USD", new BigDecimal("1234"));
        Payment p2 = Payment.create("USD", new BigDecimal("1234"));

        Assert.assertEquals(p1, p2);
    }

    @Test
    public void notEquals() throws Exception {
        Payment p1 = Payment.create("USD", new BigDecimal("1234"));
        Payment p2 = Payment.create("USD", new BigDecimal("4321"));

        Assert.assertNotEquals(p1, p2);

        p1 = Payment.create("USD", new BigDecimal("1234"));
        p2 = Payment.create("EUR", new BigDecimal("1234"));

        Assert.assertNotEquals(p1, p2);
    }

}