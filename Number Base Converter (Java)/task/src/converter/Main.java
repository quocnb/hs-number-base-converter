package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            input = scanner.nextLine();
            if ("/exit".equals(input)) {
                break;
            }
            String[] base = input.split(" ");
            int fromBase = Integer.parseInt(base[0]);
            int toBase = Integer.parseInt(base[1]);
            while (true) {
                System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", fromBase, toBase);
                input = scanner.nextLine();
                if ("/back".equals(input)) {
                    break;
                }
                String[] numberParts = input.split("\\.");
                StringBuilder builder = new StringBuilder();
                builder.append("Conversion result: ");
                builder.append(NumberUtils.convert(numberParts[0], fromBase, toBase));
                if (numberParts.length == 2) {
                    builder.append(".");
                    builder.append(NumberUtils.convertFraction(numberParts[1], fromBase, toBase));
                }
                System.out.println(builder);
                System.out.println();
            }
            System.out.println();
        }
    }

    private static void convertFromDecimal() {
        System.out.print("Enter number in decimal system: ");
        long decimal = Long.parseLong(scanner.nextLine());
        System.out.print("Enter target base: ");
        int base = Integer.parseInt(scanner.nextLine());
        System.out.print("Conversion result: " + NumberUtils.convertFromDecimal(decimal, base));
    }

    private static void convertToDecimal() {
        System.out.print("Enter source number: ");
        String source = scanner.nextLine();
        System.out.print("Enter source base: ");
        int base = Integer.parseInt(scanner.nextLine());
        System.out.print("Conversion to decimal result: " + NumberUtils.convertToDecimal(source, base));
    }
}
