package converter;

import java.util.Arrays;
import java.util.List;

public class NumberUtils {
    static List<String> hexLetter = List.of("A", "B", "C", "D", "E", "F");
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
}
