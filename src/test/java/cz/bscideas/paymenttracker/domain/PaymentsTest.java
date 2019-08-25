package cz.bscideas.paymenttracker.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PaymentsTest {

    private Payments payments;

    @Before
    public void setUp() {
        payments = new Payments();
    }

    @After
    public void restore() {
        payments = null;
    }

    @Test
    public void add() throws Exception {
        Payment payment = Payment.create("USD", new BigDecimal(123));

        payments.add(payment);

        List<Payment> all = payments.getAll();

        assertEquals(1, all.size());
        assertEquals(payment, all.get(0));
    }

    @Test
    public void getAll() throws Exception {
        Payment p1 = Payment.create("USD", new BigDecimal(30));
        Payment p2 = Payment.create("EUR", new BigDecimal(20));
        Payment p3 = Payment.create("EUR", new BigDecimal(10));

        payments.add(p1);
        payments.add(p2);
        payments.add(p3);

        List<Payment> expected = Collections.unmodifiableList(Arrays.asList(
            Payment.create("USD", new BigDecimal(30)),
            Payment.create("EUR", new BigDecimal(20)),
            Payment.create("EUR", new BigDecimal(10))
        ));

        assertEquals(expected, payments.getAll());
    }

    @Test
    public void getNets() throws Exception {
        Payment p1 = Payment.create("USD", new BigDecimal(30));
        Payment p2 = Payment.create("EUR", new BigDecimal(20));
        Payment p3 = Payment.create("EUR", new BigDecimal(10));

        payments.add(p1);
        payments.add(p2);
        payments.add(p3);

        List<Payment> expected = Collections.unmodifiableList(Arrays.asList(
                Payment.create("EUR", new BigDecimal(30)),
                Payment.create("USD", new BigDecimal(30))
        ));

        assertEquals(expected, payments.getNets());
    }
}