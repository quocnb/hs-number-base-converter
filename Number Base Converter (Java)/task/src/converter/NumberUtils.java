package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumberUtils {
    static List<String> hexLetter = new ArrayList<>();
    static {
        for (int i = 0; i <= 9; i++) {
            hexLetter.add(String.valueOf(i));
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            hexLetter.add(String.valueOf(i));
        }
    }

    static String convertFromDecimal(long decimal, int base) {
        StringBuilder builder = new StringBuilder();
        while (decimal > 0) {
            int reminder = (int) (decimal % base);
            builder.insert(0, reminder >= 10 ? hexLetter.get(reminder - 10) : reminder);
            decimal = decimal / base;
        }
        return builder.toString();
    }

    static long convertToDecimal(String source, int base) {
        long decimal = 0;
        int count = 1;
        for (String s: source.split("")) {
            int value;
            try {
                value = Integer.parseInt(s);
            } catch (Exception e) {
                value = 10 + hexLetter.indexOf(s.toUpperCase());
            }
            decimal += (long) (value * Math.pow(base, source.length() - count));
            count += 1;
        }
        return decimal;
    }

    static String convert(String source, int fromBase, int toBase) {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        BigInteger number = BigInteger.ZERO;
        for (String s: source.split("")) {
            int value;
            try {
                value = hexLetter.indexOf(s.toUpperCase());
            } catch (Exception e) {
                value = 0;
            }
            long add = (long) (value * Math.pow(fromBase, source.length() - count));
            number = number.add(BigInteger.valueOf(add));
            count += 1;
        }
        while (number.compareTo(BigInteger.ZERO) > 0) {
            int reminder = number.mod(BigInteger.valueOf(toBase)).intValue();
            builder.insert(0, hexLetter.get(reminder));
            number = number.divide(BigInteger.valueOf(toBase));
        }
        return builder.toString();
    }
}
