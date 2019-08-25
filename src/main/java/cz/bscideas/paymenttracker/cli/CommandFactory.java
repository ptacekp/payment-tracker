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

import cz.bscideas.paymenttracker.controller.ApplicationController;
import cz.bscideas.paymenttracker.common.Constants;

import static cz.bscideas.paymenttracker.common.Constants.PAYMENT_INPUT_PATTERN;

/**
 * {@code CommandFactory} class converts user terminal input to
 * application controller commands.
 *
 * <p>The {@code CommandFactory} can parse this user input:
 * <ul>
 *     <li><b>help</b> - print help message</li>
 *     <li><b>version</b> - print version of the application</li>
 *     <li><b>add</b> - add new payment</li>
 *     <li><b>load</b> - load payments from file</li>
 *     <li><b>quit</b> - quit the application</li>
 *     <li>text with pattern {@value Constants#PAYMENT_INPUT_PATTERN}
 *         - same as add command</li>
 * </ul>
 *
 * @author Petr Ptáček
 */
public class CommandFactory {

    /**
     * Binding to application controller.
     */
    private final ApplicationController app;

    /**
     * Create new instance of {@code CommandFactory} class.
     *
     * @param app
     *          Application controller.
     */
    public CommandFactory(ApplicationController app) {
        this.app = app;
    }

    /**
     * Parse request from terminal input and create command
     * based on it.
     *
     * @param request
     *          user terminal input
     *
     * @return  instance of {@link Command} class.
     *          Never return <tt>null</tt>.
     */
    public Command parse(String request) {
        if (request == null || request.length() == 0) {
            return DisplayCommand.createInvalidCommand(request);
        }

        request = request.trim();


        if (request.matches(PAYMENT_INPUT_PATTERN)) {
            return new AddCommand(request, app);
        } else if (request.startsWith("add ")) {
            String line = request.replace("add ", "");
            if (!line.matches(PAYMENT_INPUT_PATTERN)) {
                return DisplayCommand.createInvalidCommand(request);
            }

            return new AddCommand(line, app);
        } else if (request.startsWith("load ")) {
            String path = request.replace("load ", "");
            return new LoadCommand(path, app);
        } else if ("quit".equalsIgnoreCase(request)) {
            return ExitCommand.getInstance();
        } else if ("help".equalsIgnoreCase(request)) {
            return DisplayCommand.createHelpCommand();
        } else if ("version".equalsIgnoreCase(request)) {
            return DisplayCommand.createVersionCommand();
        } else {
            return DisplayCommand.createInvalidCommand(request);
        }
    }
}