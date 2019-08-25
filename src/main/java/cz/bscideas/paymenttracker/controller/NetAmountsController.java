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
package cz.bscideas.paymenttracker.controller;

import cz.bscideas.paymenttracker.common.Constants;
import cz.bscideas.paymenttracker.domain.Payments;
import cz.bscideas.paymenttracker.view.NetAmountsView;

/**
 * {@code NetAmountsController} is responsible for periodic updating
 * UI output with list of currency names and net amounts.
 *
 * <p>Period of updates is define by value
 * {@value Constants#NET_PAYMENT_VIEW_UPDATE_PERIOD}. Default
 * update period is one per minute.
 *
 * @author Petr Ptáček
 */
public class NetAmountsController extends Thread {

    /**
     * Payments collection model.
     */
    private final Payments payments;

    /**
     * Create new instance of {@code NetAmountsController} class.
     *
     * @param   payments
     *          payments collection model.
     */
    public NetAmountsController(Payments payments) {
        this.payments = payments;
    }

    /**
     * Run infinite loop of periodic updating UI output
     * with list of curency names and net amounts.
     */
    @Override
    public void run() {
        NetAmountsView view = new NetAmountsView(payments);

        while(true){
            try {
                Thread.sleep(Constants.NET_PAYMENT_VIEW_UPDATE_PERIOD);

                view.show();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
