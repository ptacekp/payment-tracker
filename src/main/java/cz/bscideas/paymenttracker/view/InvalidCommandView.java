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
package cz.bscideas.paymenttracker.view;

/**
 * Print invalid command error to error output.
 *
 * <p>The error message has following format:
 * <pre>Invalid Command 'blabla'</pre>
 * for user input <code>blabla</code>.
 *
 * @author Petr Ptáček
 */
public class InvalidCommandView implements View {

    /**
     * User invalid command input.
     */
    private final String request;

    /**
     * Create new instance of {@code InvalidCommandView} class.
     *
     * @param request
     *          user invalid command input.
     */
    public InvalidCommandView(String request) {
        this.request = request;
    }

    /**
     * Print invalid command message to the error output.
     *
     * <p>The error message has following format:
     * <pre>Invalid Command 'messge'</pre>
     */
    @Override
    public void show() {
        synchronized (System.err) {
            System.err.println("Invalid Command '" + request + "'");
        }
    }
}
