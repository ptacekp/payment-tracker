package cz.bscideas.paymenttracker.cli;

import cz.bscideas.paymenttracker.controller.ApplicationController;
import cz.bscideas.paymenttracker.view.HelpView;
import cz.bscideas.paymenttracker.view.InvalidCommandView;
import cz.bscideas.paymenttracker.view.VersionView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class CommandFactoryTest {

    private ApplicationController app;
    private CommandFactory factory;

    @Before
    public void setUp() {
        this.app = mock(ApplicationController.class);
        this.factory = new CommandFactory(app);
    }

    @After
    public void restore() {
        this.app = null;
        this.factory = null;
    }

    @Test
    public void parseQuit() throws Exception {
        Command command = factory.parse("quit");

        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseHelp() throws Exception {
        Command command = factory.parse("help");

        assertTrue(command instanceof DisplayCommand);
        assertTrue(((DisplayCommand) command).getView() instanceof HelpView);
    }

    @Test
    public void parseLoad() throws Exception {
        Command command = factory.parse("load payments.txt");

        assertTrue(command instanceof LoadCommand);
    }

    @Test
    public void parseVersion() throws Exception {
        Command command = factory.parse("version");

        assertTrue(command instanceof DisplayCommand);
        assertTrue(((DisplayCommand) command).getView() instanceof VersionView);
    }

    @Test
    public void parseAdd() throws Exception {
        Command command = factory.parse("add USD 1");

        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parseInput() throws Exception {
        Command command = factory.parse("USD 123");

        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parseInvalid() throws Exception {
        Command command = factory.parse("bla bla bla");

        assertTrue(command instanceof DisplayCommand);
        assertTrue(((DisplayCommand) command).getView() instanceof InvalidCommandView);
    }
}
