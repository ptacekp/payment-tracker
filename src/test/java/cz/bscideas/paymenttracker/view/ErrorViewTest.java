package cz.bscideas.paymenttracker.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class ErrorViewTest {
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
    public void showThrowable() throws Exception {
        Throwable throwable = new Exception();
        String expected = getStrackTrace(throwable);

        View view = new ErrorView(throwable, null);
        view.show();

        assertEquals("", outContent.toString());
        assertEquals(expected, errContent.toString());
    }

    @Test
    public void showMessage() throws Exception {
        String message = "This is big error!";

        View view = new ErrorView(null, message);
        view.show();

        assertEquals("", outContent.toString());
        assertEquals(message + lineSeparator, errContent.toString());
    }

    @Test
    public void showBoth() throws Exception {
        Throwable throwable = new Exception();
        String message = "This is big error!";
        String expected = message + lineSeparator + getStrackTrace(throwable);

        View view = new ErrorView(throwable, message);
        view.show();

        assertEquals("", outContent.toString());
        assertEquals(expected, errContent.toString());
    }

    @Test
    public void showNone() throws Exception {
        Throwable throwable = null;
        String message = null;

        View view = new ErrorView(throwable, message);
        view.show();

        assertEquals("", outContent.toString());
        assertEquals("", errContent.toString());
    }

    private static String getStrackTrace(Throwable throwable) {
        StringWriter writer = new StringWriter();
        throwable.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }
}
