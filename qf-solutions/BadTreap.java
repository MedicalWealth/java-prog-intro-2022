import java.util.Scanner;

public class BadTreap {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int depth = in.nextInt();
        in.close();
        int NUMBER_TO_BEGIN = -710 * 25000;
        for (int i = 0; i < depth; i++) {
            System.out.println(NUMBER_TO_BEGIN);
            NUMBER_TO_BEGIN += 710;
        }
    }
}