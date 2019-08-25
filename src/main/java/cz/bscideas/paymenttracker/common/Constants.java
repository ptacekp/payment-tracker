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
package cz.bscideas.paymenttracker.common;

/**
 * This class contains application global constants.
 *
 * @author Petr Ptáček
 */
public class Constants {

    /**
     * Period of update Net payment view in ms.
     * It is one minute in default.
     */
    public static final long NET_PAYMENT_VIEW_UPDATE_PERIOD = 60 * 1000;

    /**
     * Version of the Application.
     */
    public static final String VERSION = "1.0";

    /**
     * Regular expression pattern for parsing payment input.
     */
    public static final String PAYMENT_INPUT_PATTERN = "[A-Z]{3} (-)?\\d+(.\\d+)?";

    /**
     * You are not allowed to make instance of this class.
     */
    private Constants() { }
}
