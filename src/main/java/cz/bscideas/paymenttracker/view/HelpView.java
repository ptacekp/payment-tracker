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

import cz.bscideas.paymenttracker.cli.ArgumentHandler;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * {@code HelpView} print application help to the standard output.
 * Help output consist of startup help and application commands
 * help.
 *
 * @author Petr Ptáček
 */
public class HelpView implements View {

    /**
     * Create an instance of {@code HelpView}.
     */
    public HelpView() {}

    /**
     * Print application help to the standard output.
     * Help output consist of startup help and application
     * commands help.
     */
    @Override
    public void show() {
        synchronized (System.out) {
            startupHelp();
            applicationHelp();
        }
    }

    /**
     * Print startup application help.
     */
    private void startupHelp() {
        final String lineSeparator = System.lineSeparator();
        final Options options = ArgumentHandler.getOptions();
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "paymenttracker [(-h) (-v) (-f) FILE]",
                "This application track and store payments for"
                        + lineSeparator + "different currency.", options ,
                "Payment Tracker allow you to insert new payment"
                        + lineSeparator + "from console or text file.");
        System.out.println("");
    }

    /**
     * Print application commands help.
     */
    private void applicationHelp() {
        System.out.println("Commands in application:");
        System.out.println(" add      - add new payment");
        System.out.println(" help     - print this message");
        System.out.println(" version  - print version of the application");
        System.out.println(" load     - load payments from a file");
        System.out.println(" quit     - exit the application");
        System.out.println("");
    }
}