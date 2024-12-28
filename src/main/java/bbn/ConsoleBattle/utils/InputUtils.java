package bbn.ConsoleBattle.utils;

import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter an integer");
        }
    }

    public static String readString() {
        return scanner.nextLine();
    }
}
