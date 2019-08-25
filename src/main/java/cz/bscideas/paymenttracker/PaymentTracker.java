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
package cz.bscideas.paymenttracker;

import cz.bscideas.paymenttracker.cli.ArgumentHandler;
import cz.bscideas.paymenttracker.controller.ApplicationController;
import cz.bscideas.paymenttracker.controller.NetAmountsController;
import cz.bscideas.paymenttracker.domain.Payments;

/**
 * This class is an entry point of Payment Tracker application.
 *
 * @author Petr Ptáček
 */
public class PaymentTracker {
    /**
     * Main method and entry point of the application.
     *
     * @param   args
     *          startup arguments.
     */
    public static void main(String[] args) {
        ArgumentHandler argHandler = new ArgumentHandler(args);
        argHandler.process();
        if (!argHandler.canContinue()) {
            return;
        }

        Payments payments = new Payments();

        Thread nets = new NetAmountsController(payments);
        nets.setDaemon(true);
        nets.start();

        ApplicationController app = new ApplicationController(payments, argHandler);
        app.start();
    }
}
