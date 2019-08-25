package cz.bscideas.paymenttracker.domain;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PaymentLoaderTest {
    @Test
    public void load() throws Exception {
        List<Payment> expected = Arrays.asList(
                Payment.create("USD", new BigDecimal(1)),
                Payment.create("EUR", new BigDecimal(2)),
                Payment.create("CZK", new BigDecimal(3))
        );

        Reader reader = new StringReader("USD 1\nEUR 2\nCZK 3");
        List<Payment> loaded = PaymentLoader.load(reader);

        assertEquals(expected, loaded);
    }

    @Test
    public void parseValid() throws Exception {
        String line = "USD -123.456";
        Payment expected = Payment.create("USD", new BigDecimal("-123.456"));

        Payment parsed = PaymentLoader.parse(line);

        assertEquals(expected, parsed);
    }

    @Test
    public void parseInvalid() throws Exception {
        String line = "bla bla bla";
        Payment parsed = PaymentLoader.parse(line);

        assertNull(parsed);
    }
}