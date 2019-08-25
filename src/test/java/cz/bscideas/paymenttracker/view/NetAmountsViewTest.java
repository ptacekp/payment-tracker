package cz.bscideas.paymenttracker.view;

import cz.bscideas.paymenttracker.domain.Payment;
import cz.bscideas.paymenttracker.domain.Payments;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class NetAmountsViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final String lineSeparator = System.lineSeparator();
    private Payments payments;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        payments = new Payments();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);

        payments = null;
    }

    @Test
    public void show() throws Exception {
        Payment p1 = Payment.create("USD", new BigDecimal(30));
        Payment p2 = Payment.create("EUR", new BigDecimal(20));
        Payment p3 = Payment.create("EUR", new BigDecimal(10));

        payments.add(p1);
        payments.add(p2);
        payments.add(p3);

        View view = new NetAmountsView(payments);
        view.show();

        String expected = "-- Net amount per currency --" + lineSeparator
                            + "EUR 30.0" + lineSeparator
                            + "USD 30.0" + lineSeparator + lineSeparator;

        assertEquals(expected, outContent.toString());
        assertEquals("", errContent.toString());
    }

}
