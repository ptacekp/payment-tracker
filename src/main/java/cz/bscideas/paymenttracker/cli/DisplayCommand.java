/*
 * Copyright (c) 2019 Petr Ptáček
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package cz.bscideas.paymenttracker.cli;

import cz.bscideas.paymenttracker.view.HelpView;
import cz.bscideas.paymenttracker.view.InvalidCommandView;
import cz.bscideas.paymenttracker.view.VersionView;
import cz.bscideas.paymenttracker.view.View;

/**
 * Activate view and show version of the application.
 *
 * @author Petr Ptáček
 */
public class DisplayCommand implements Command {

    /**
     * View to print to standard output.
     */
    private final View view;

    /**
     * Create new instance of {@code DisplayCommand} class.
     *
     * @param view
     *          view to print to standard output.
     */
    public DisplayCommand(View view) {
        this.view = view;
    }

    /**
     * Create command that shows help for the application.
     *
     * @return instance of {@link Command} class.
     *          Never returns <tt>null</tt>.
     */
    public static Command createHelpCommand() {
        View view = new HelpView();
        return new DisplayCommand(view);
    }

    /**
     * Create command that shows version of the application.
     *
     * @return instance of {@link Command} class.
     *          Never returns <tt>null</tt>.
     */
    public static Command createVersionCommand() {
        View view = new VersionView();
        return new DisplayCommand(view);
    }

    /**
     * Create command that shows error message with invalid user request in
     * the application UI.
     *
     * @param request
     *          user request.
     *
     * @return instance of {@link Command} class.
     *          Never returns <tt>null</tt>.
     */
    public static Command createInvalidCommand(String request) {
        View view = new InvalidCommandView(request);
        return new DisplayCommand(view);
    }

    /**
     * Show version of the application in UI.
     *
     * <p>Call {@link VersionView} to display application
     * version description.
     */
    public void execute() {
        view.show();
    }

    /**
     * Returns view of this command.
     *
     * @return instance of {@link View} or <tt>null</tt>.
     */
    public View getView() {
        return view;
    }
}
