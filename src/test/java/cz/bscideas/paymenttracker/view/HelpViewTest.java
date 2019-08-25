package cz.bscideas.paymenttracker.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class HelpViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final String lineSeparator = System.lineSeparator();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void show() throws Exception {
        String expected = "usage: paymenttracker [(-h) (-v) (-f) FILE]" + lineSeparator
                + "This application track and store payments for"
                + lineSeparator + "different currency." + lineSeparator
                + " -f,--file <file>   file or text file with list of payment data" + lineSeparator
                + " -h,--help          print this message" + lineSeparator
                + " -v,--version       print version of the application" + lineSeparator
                + "Payment Tracker allow you to insert new payment"
                + lineSeparator + "from console or text file." + lineSeparator + lineSeparator
                + "Commands in application:" + lineSeparator
                + " add      - add new payment" + lineSeparator
                + " help     - print this message" + lineSeparator
                + " version  - print version of the application" + lineSeparator
                + " load     - load payments from a file" + lineSeparator
                + " quit     - exit the application" + lineSeparator + lineSeparator;

        View view = new HelpView();
        view.show();

        assertEquals(expected, outContent.toString());
        assertEquals("", errContent.toString());
    }

}
