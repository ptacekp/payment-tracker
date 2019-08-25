package cz.bscideas.paymenttracker.controller;

import cz.bscideas.paymenttracker.cli.ArgumentHandler;
import cz.bscideas.paymenttracker.domain.Payment;
import cz.bscideas.paymenttracker.domain.Payments;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class ApplicationControllerTest {
    @Test
    public void addPayment() throws Exception {
        Payments payments = mock(Payments.class);
        ArgumentHandler args = mock(ArgumentHandler.class);

        Payment payment = Payment.create("USD", BigDecimal.ONE);

        ApplicationController app = new ApplicationController(payments, args);
        app.addPayment(payment);

        verify(payments, times(1)).add(payment);
        verifyNoMoreInteractions(payments);
    }
}