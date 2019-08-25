package cz.bscideas.paymenttracker.cli;

import cz.bscideas.paymenttracker.controller.ApplicationController;
import cz.bscideas.paymenttracker.domain.Payment;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class AddCommandTest {
    @Test
    public void execute() throws Exception {
        ApplicationController app = mock(ApplicationController.class);
        AddCommand command = new AddCommand("USD 1", app);

        command.execute();

        verify(app, times(1)).addPayment(Payment.create("USD", BigDecimal.ONE));
        verifyNoMoreInteractions(app);
    }

}
