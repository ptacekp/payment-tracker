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


import cz.bscideas.paymenttracker.domain.Payment;
import cz.bscideas.paymenttracker.domain.Payments;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * {@code NetAmountsView} print list of currency name
 * and net amount to the standard output.
 * List is sorted by currency name.
 *
 * @author Petr Ptáček
 */
public class NetAmountsView implements View {

    /**
     * Payments collection.
     */
    private final Payments payments;

    /**
     * Create an instance of class {@link NetAmountsView} according
     * to the specified parameters.
     *
     * @param   payments
     *          payments collection
     */
    public NetAmountsView(Payments payments) {
        this.payments = payments;
    }

    /**
     * Print list of list of currency name
     * and net amount to the standard output.
     * List is sorted by currency name.
     *
     * <p>This method prints header, list of currency names and net amounts
     * and blank line. The header format is:
     * <pre>-- Net amount per currency --</pre>
     * Payment format is three capital letters of currency name, space
     * and decimal number rounded up to one decimal point.
     */
    @Override
    public final void show() {
        List<Payment> nets = this.payments.getNets();
        if (nets == null || nets.isEmpty()) {
            return;
        }

        synchronized (System.out) {
            System.out.println("-- Net amount per currency --");

            for (Payment payment : nets) {
                printPayment(payment);
            }

            System.out.println("");
        }
    }

    /**
     * Print one payment to standard output.
     *
     * @param   payment
     *          payment to print.
     */
    private void printPayment(Payment payment) {
        String currency = payment.currency();
        BigDecimal amount = payment.amount()
                .setScale(1, RoundingMode.HALF_UP);

        if (new BigDecimal(0).compareTo(amount) >= 0) {
            return;
        }

        System.out.println(currency + " " + amount);
    }
}
