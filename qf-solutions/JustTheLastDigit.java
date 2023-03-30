import java.util.Scanner;

public class JustTheLastDigit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sizeOfMatrix = in.nextInt();
        int[][] numberOfWays = new int[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            String line = in.next();
            for (int j = 0; j < sizeOfMatrix; j++) {
                numberOfWays[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }
        in.close();
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = i + 1; j < sizeOfMatrix; j++) {
                if (numberOfWays[i][j] == 0) {
                    continue;
                }
                for (int k = j + 1; k < sizeOfMatrix; k++) {
                    numberOfWays[i][k] = (numberOfWays[i][k] - numberOfWays[j][k] + 10) % 10;
                }
            }
        }
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 0; j < sizeOfMatrix; j++) {
                System.out.print(numberOfWays[i][j]);
            }
            System.out.println();
        }
    }
}
