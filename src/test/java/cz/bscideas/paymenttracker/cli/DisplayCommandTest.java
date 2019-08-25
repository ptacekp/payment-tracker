package cz.bscideas.paymenttracker.cli;

import cz.bscideas.paymenttracker.view.HelpView;
import cz.bscideas.paymenttracker.view.InvalidCommandView;
import cz.bscideas.paymenttracker.view.VersionView;
import cz.bscideas.paymenttracker.view.View;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class DisplayCommandTest {

    @Test
    public void createHelpCommand() throws Exception {
        Command command = DisplayCommand.createHelpCommand();

        assertTrue(command instanceof DisplayCommand);
        assertTrue(((DisplayCommand) command).getView() instanceof HelpView);
    }

    @Test
    public void createVersionCommand() throws Exception {
        Command command = DisplayCommand.createVersionCommand();

        assertTrue(command instanceof DisplayCommand);
        assertTrue(((DisplayCommand) command).getView() instanceof VersionView);
    }

    @Test
    public void createInvalidCommand() throws Exception {
        Command command = DisplayCommand.createInvalidCommand("");

        assertTrue(command instanceof DisplayCommand);
        assertTrue(((DisplayCommand) command).getView() instanceof InvalidCommandView);
    }

    @Test
    public void execute() throws Exception {
        View view = mock(View.class);

        Command command = new DisplayCommand(view);
        command.execute();

        verify(view, times(1)).show();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void getView() throws Exception {
        View view = mock(View.class);
        DisplayCommand command = new DisplayCommand(view);

        assertEquals(view, command.getView());
    }
}
