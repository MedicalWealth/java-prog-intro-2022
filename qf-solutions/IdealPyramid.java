import java.util.Scanner;

public class IdealPyramid {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int xLeft = Integer.MAX_VALUE, yLeft = Integer.MAX_VALUE;
        int xRight = Integer.MIN_VALUE, yRight = Integer.MIN_VALUE;
        int numberOfPyramids = in.nextInt();
        for (int i = 0; i < numberOfPyramids; i++) {
            int x = in.nextInt(), y = in.nextInt(), height = in.nextInt();
            xLeft = Math.min(xLeft, x - height);
            yLeft = Math.min(yLeft, y - height);
            xRight = Math.max(xRight, x + height);
            yRight = Math.max(yRight, y + height);
        }
        in.close();
        int primeX = (xLeft + xRight) / 2;
        int primeY = (yLeft + yRight) / 2;
        int primeHeight = (int) Math.ceil((double) (Math.max(xRight - xLeft, yRight - yLeft)) / (double) 2);
        System.out.println(primeX + " " + primeY + " " + primeHeight);
    }
}