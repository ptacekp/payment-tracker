package cz.bscideas.paymenttracker.cli;

import org.junit.Test;

public class ExitCommandTest {

    @Test(expected = ExitRequest.class)
    public void execute() throws Exception {
        Command command = ExitCommand.getInstance();
        command.execute();
    }
}
