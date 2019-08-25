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

import cz.bscideas.paymenttracker.common.Constants;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility for parsing and loading {@link Payment}
 * instances from resources.
 *
 * @author Petr Ptáček
 */
public class PaymentLoader {

    /**
     * You are not allowed to create instance of this class.
     */
    private PaymentLoader() { }

    /**
     * Load list of payments from resource.
     * Resource is dependent on reader in argument.
     *
     * @param   reader
     *          resource reader
     *
     * @return  list of {@link Payment}.
     *          Never returns <tt>null</tt>.
     *
     * @throws  IOException
     *          occures when there is an error in reading resource.
     */
    public static List<Payment> load(Reader reader) throws IOException {
        List<Payment> result = new ArrayList<>();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(reader);

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                Payment payment = parse(line);
                if (payment != null) {
                    result.add(payment);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return result;
    }

    /**
     * Create file reader for reading text files.
     *
     * @param   path
     *          path to text file
     *
     * @return  instance of file reader.
     *
     * @throws  FileNotFoundException
     *          occurs if file or directory not found.
     */
    public static Reader createFileReader(String path) throws FileNotFoundException {
        return new FileReader(path);
    }

    /**
     * Parse request line into tracked payment object.
     * Request line should have {@value Constants#PAYMENT_INPUT_PATTERN}
     * pattern.
     *
     * @param   request
     *          request line for parsing.
     *
     * @return  instance of {@link Payment} or <tt>null</tt>,
     *          if parsing failed.
     */
    public static Payment parse(String request) {
        if (request == null || !request.matches(Constants.PAYMENT_INPUT_PATTERN)) {
            return null;
        }

        String[] split = request.split(" ");

        String currency = split[0];
        BigDecimal amount = new BigDecimal(split[1]);

        return Payment.create(currency, amount);
    }
}