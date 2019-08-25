package cz.bscideas.paymenttracker.cli;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArgumentHandlerTest {

    @Test
    public void canContinueTrue() throws Exception {
        String[] args = {};
        ArgumentHandler handler = new ArgumentHandler(args);

        assertEquals(true, handler.canContinue());
    }

    @Test
    public void canContinueFalse() throws Exception {
        String[] args = {"-h"};
        ArgumentHandler handler = new ArgumentHandler(args);

        assertEquals(false, handler.canContinue());
    }

    @Test
    public void getFileName() throws Exception {
        String[] args = {"-f", "payments.txt"};
        ArgumentHandler handler = new ArgumentHandler(args);

        assertEquals(handler.getFileName(), "payments.txt");
    }
}
