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
package cz.bscideas.paymenttracker.domain;

import java.math.BigDecimal;
import java.util.*;

/**
 * Collection of tracked payments. Holding a list
 * of {@link Payment} class instance in inserted order.
 *
 * <p>The {@code Payments} class provide operation for
 * add and retrive data from collection. All operations
 * are thread-safe. It is possible to call add operation
 * from one thread and get operation from different thread
 * on same instance of {@code Payments}.
 *
 * @author Petr Ptáček
 */
public class Payments {

    /**
     * Collection holding all tracked payments.
     */
    private final List<Payment> payments;

    /**
     * Create new instance of {@code Payments} and init
     * payments collection.
     */
    public Payments() {
        this.payments = new ArrayList<>();
    }

    /**
     * Add one payment to this collection.
     * <p>
     * Add operation is thread-safe. It is possible to
     * call add operation from one thread and get operations
     * from different thread on same instance of {@code Payments}.
     *
     * @param   payment
     *          tracked payment.
     */
    public void add(Payment payment) {
        synchronized (this.payments) {
            this.payments.add(payment);
        }
    }

    /**
     * Return all tracked payments in the collection.
     * Returned list is unmodifiable.
     *
     * <p>Get operation is thread-safe. It is possible to
     * call get operation from one thread and add operations
     * from different thread on same instance of {@code Payments}.
     *
     * @return  an unmodifiable view of the payments list.
     */
    public List<Payment> getAll() {
        synchronized (this.payments) {
            return Collections.unmodifiableList(this.payments);
        }
    }

    /**
     * Return list of net payments per currency.
     * Returned list is unmodifiable.
     *
     * <p>This method first count net amount for every
     * recorded currency name. This net amounts is converted
     * to the list of payments {@link Payment}.
     * At the end of process all of payments is sorted by
     * currency name.
     *
     * <p>Get operation is thread-safe. It is possible to
     * call get operation from one thread and add operations
     * from different thread on same instance of {@code Payments}.
     *
     * @return  an unmodifiable view of the payments list.
     */
    public List<Payment> getNets() {
        Map<String, BigDecimal> nets = countNets();
        List<Payment> result = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> e : nets.entrySet()) {
            String currency = e.getKey();
            BigDecimal amount = e.getValue();

            result.add(Payment.create(currency, amount));
        }

        result.sort((p1, p2) -> p1.currency().compareTo(p2.currency()));
        return Collections.unmodifiableList(result);
    }

    /**
     * Count net amounts for every recorder currency name.
     * This method si thread-safe.
     *
     * @return  map of currency names and their net amount.
     */
    private Map<String, BigDecimal> countNets() {
        Map<String, BigDecimal> nets = new HashMap<>();

        synchronized (this.payments) {
            for (Payment payment : payments) {
                String currency = payment.currency();
                BigDecimal amount = payment.amount();

                BigDecimal prev = nets.get(currency);

                if (prev != null) {
                    amount = amount.add(prev);
                }

                nets.put(currency, amount);
            }
        }

        return nets;
    }
}
