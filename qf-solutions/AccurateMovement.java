import java.util.Scanner;

public class AccurateMovement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        double b = in.nextDouble();
        double n = in.nextDouble();
        in.close();
        System.out.println((int) (2 * Math.ceil((n - b) / (b - a)) + 1));
    }
}
