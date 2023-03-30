import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReverseSum {
    public static int[] resize(int[] arr, int size) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, size * 2);
        }
        return arr;
    }
    public static int[][] resize(int[][] arr, int size) {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, size * 2);
        }
        return arr;
    }
    public static void main(String[] args) {
        try {
            BufferedScanner in = new BufferedScanner(new InputStreamReader(System.in),
                new byte[]{Character.DECIMAL_DIGIT_NUMBER}, new char[]{'-'}, 256
            );
            try {
                int[][] matrix = new int[1][1];
                int[] dynamicSumRows = new int[1];
                int[] dynamicSumColon = new int[1];
                int lengthOfRow = 0;
                int sizeOfMatrix = 0;
                BufferedScanner.TypeOfToken line = in.whatIsNext();
                while (line != BufferedScanner.TypeOfToken.NOTHING) {
                    if (line == BufferedScanner.TypeOfToken.NEW_LINE) {
                        while (line == BufferedScanner.TypeOfToken.NEW_LINE) {
                            matrix[sizeOfMatrix] = Arrays.copyOf(matrix[sizeOfMatrix], lengthOfRow);
                            sizeOfMatrix++;
                            matrix = resize(matrix, sizeOfMatrix);
                            matrix[sizeOfMatrix] = new int[1];
                            dynamicSumRows = resize(dynamicSumRows, sizeOfMatrix);
                            lengthOfRow = 0;
                            line = in.whatIsNext();
                        }
                    } else {
                        matrix[sizeOfMatrix][lengthOfRow++] = Integer.parseInt(in.getNext());
                        matrix[sizeOfMatrix] = resize(matrix[sizeOfMatrix], lengthOfRow);
                        dynamicSumRows[sizeOfMatrix] += matrix[sizeOfMatrix][lengthOfRow - 1];
                        dynamicSumColon[lengthOfRow - 1] += matrix[sizeOfMatrix][lengthOfRow - 1];
                        dynamicSumColon = resize(dynamicSumColon, lengthOfRow);
                        line = in.whatIsNext();
                    }
                }
                for (int i = 0; i < sizeOfMatrix; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        int sum = dynamicSumRows[i] + dynamicSumColon[j] - matrix[i][j];
                        System.out.print(sum + " ");
                    }
                    System.out.println();
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O exception: " + e.getMessage());
        }
    }
}   
