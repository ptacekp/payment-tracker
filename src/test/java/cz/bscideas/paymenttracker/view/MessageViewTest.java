package cz.bscideas.paymenttracker.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MessageViewTest {
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
        String message = "Bla bla message";

        View view = new MessageView(message);
        view.show();

        assertEquals(message + lineSeparator, outContent.toString());
        assertEquals("", errContent.toString());
    }

    @Test
    public void showNull() throws Exception {
        String message = null;

        View view = new MessageView(message);
        view.show();

        assertEquals("", outContent.toString());
        assertEquals("", errContent.toString());
    }

}
