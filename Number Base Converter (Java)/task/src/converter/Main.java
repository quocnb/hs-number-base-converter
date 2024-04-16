package converter;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
            switch (scanner.nextLine()) {
                case "/from":
                    convertFromDecimal();
                    break;
                case "/to":
                    convertToDecimal();
                    break;
                case "/exit":
                    return;
            }
            System.out.println("\n");
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
