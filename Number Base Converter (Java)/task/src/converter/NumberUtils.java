package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
        return builder.isEmpty() ? "0" : builder.toString();
    }

    static String convertFraction(String source, int fromBase, int toBase) {
        StringBuilder builder = new StringBuilder();
        BigDecimal number = BigDecimal.ZERO;
        BigDecimal fromBaseDecimal = BigDecimal.valueOf(fromBase);
        BigDecimal toBaseDecimal = BigDecimal.valueOf(toBase);

        for (String s: new StringBuilder(source).reverse().toString().split("")) {
            double value;
            try {
                value = hexLetter.indexOf(s.toUpperCase());
            } catch (Exception e) {
                value = 0;
            }
            number = number.add(BigDecimal.valueOf(value)).divide(fromBaseDecimal, 5, RoundingMode.FLOOR);
        }
        int count = 0;
        while (count < 5) {
            number = number.multiply(toBaseDecimal);
            BigDecimal integerPart = number.setScale(0, RoundingMode.DOWN);
            builder.append(hexLetter.get(integerPart.intValue()));
            number = number.remainder(BigDecimal.ONE);
            count += 1;
        }
        return builder.toString();
    }
}
