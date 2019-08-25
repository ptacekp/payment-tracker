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
 * {@code ErrorView} print user error message and application
 * error / exceptions to the error output.
 *
 * @author Petr Ptáček
 */
public class ErrorView implements View {

    /**
     * Application error with stack trace.
     */
    private final Throwable throwable;

    /**
     * User error message.
     */
    private final String message;

    /**
     * Create new instance of {@code ErrorView} class.
     *
     * @param throwable
     *          application error with stack trace.
     *
     * @param message
     *          user error message.
     */
    public ErrorView(Throwable throwable, String message) {
        this.throwable = throwable;
        this.message = message;
    }

    /**
     * Print user error message and application
     * error / exceptions to the error output.
     *
     * <p>If user error message is <tt>null</tt>,
     * no message is printed. If application
     * error / exception is <tt>null</tt>, none
     * stack trace is printed.
     */
    @Override
    public void show() {
        if (message != null) {
            System.err.println(message);
        }
        if (throwable != null) {
            this.throwable.printStackTrace();
        }
    }
}
