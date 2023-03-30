package game;

import java.io.InputStream;
import java.util.Scanner;

public class Reader {
    Scanner in;

    public Reader(InputStream in) {
        this.in = new Scanner(in);
    }

    public int readInt(String expect) {
        String result;
        System.out.println("Enter " + expect);
        while (!isNumber(result = in.next()) || result.length() >= 10 || Integer.parseInt(result) < 1) {
            System.out.println("Input mismatch! Please enter positive decimal number more than 0!");
            System.out.println("Enter " + expect);
        }
        return Integer.parseInt(result);
    }

    private static boolean isNumber(String str) {
        boolean result = true;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }
}
