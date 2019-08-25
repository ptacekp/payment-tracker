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

import cz.bscideas.paymenttracker.cli.*;
import cz.bscideas.paymenttracker.domain.Payment;
import cz.bscideas.paymenttracker.domain.Payments;
import cz.bscideas.paymenttracker.view.ErrorView;
import cz.bscideas.paymenttracker.view.ExitView;
import cz.bscideas.paymenttracker.view.WelcomeView;

import java.util.Scanner;

/**
 * Main application controller.
 *
 * <p>This class provide method to manipulate
 * with model and glue model to commands and views.
 *
 * @author Petr Ptáček
 */
public class ApplicationController {

    /**
     * Payments collection model.
     */
    private final Payments payments;

    /**
     * Startup arguments handler.
     */
    private final ArgumentHandler args;

    /**
     * Create new instance of {@code ApplicationController} class.
     *
     * @param   payments
     *          collection of tracked payments.
     *
     * @param   args
     *          startup arguments handler.
     */
    public ApplicationController(Payments payments, ArgumentHandler args) {
        this.payments = payments;
        this.args = args;
    }

    /**
     * Start application controller infinite loop.
     *
     * <p>This method execute
     * <ul>
     *     <li>show welcome screen</li>
     *     <li>init model from file (if it is set)</li>
     *     <li>run infinite input reading loop</li>
     *     <li>show exit screen, when application quits</li>
     * </ul>
     */
    public void start() {
        new WelcomeView().show();

        initFromFile();
        startLoop();

        new ExitView().show();
    }

    /**
     * Add new payment to payment collection.
     *
     * @param   payment
     *          new payment to add.
     */
    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }

    /**
     * Init payments list from file.
     *
     * <p>Initiation starts only, if the applications
     * startup arguments contains <tt>-f</tt> option.
     */
    private void initFromFile() {
        String fileName = args.getFileName();
        if (fileName == null || fileName.length() == 0) {
            return;
        }

        Command command = new LoadCommand(fileName, this);
        command.execute();
    }

    /**
     * Start infinite loop of reading terminal inputs.
     * Loop reads standard input and parse it to application
     * command. Parsed commands are immediately executed.
     */
    private void startLoop() {
        CommandFactory commandFactory = new CommandFactory(this);
        Scanner reader = new Scanner(System.in);

        while(true) {
            String commandStr = reader.nextLine();
            Command command = commandFactory.parse(commandStr);

            if (command != null) {
                try {
                    command.execute();
                } catch (ExitRequest e) {
                    break;
                } catch (Exception e) {
                    new ErrorView(e, null).show();
                } catch (Throwable t) {
                    new ErrorView(t, null).show();
                    break;
                }
            }
        }
    }
}