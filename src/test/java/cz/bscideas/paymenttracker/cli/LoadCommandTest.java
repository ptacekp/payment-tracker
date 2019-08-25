package cz.bscideas.paymenttracker.cli;

import cz.bscideas.paymenttracker.controller.ApplicationController;
import cz.bscideas.paymenttracker.domain.Payment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class LoadCommandTest {

    private ApplicationController app;

    @Before
    public void setUp() {
        app = mock(ApplicationController.class);
    }

    @After
    public void restore() {
        app = null;
    }

    @Test
    public void execute() throws Exception {
        Command command = new LoadCommand("", app) {
            @Override
            protected Reader createReader() throws IOException {
                return new StringReader("USD 1\nEUR 2\nCZK 3");
            }
        };

        command.execute();

        verify(app, times(1)).addPayment(Payment.create("USD", new BigDecimal(1)));
        verify(app, times(1)).addPayment(Payment.create("EUR", new BigDecimal(2)));
        verify(app, times(1)).addPayment(Payment.create("CZK", new BigDecimal(3)));

        verifyNoMoreInteractions(app);
    }


}
