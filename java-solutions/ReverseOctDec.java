import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ReverseOctDec {

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
            BufferedScanner in = new BufferedScanner(
                new InputStreamReader(System.in, StandardCharsets.UTF_8),
                    new byte[]{Character.DECIMAL_DIGIT_NUMBER}, new char[]{'-', 'O', 'o'}
            );
            try {
                int[][] matrix = new int[1][1];
                int[] lengthsOfRows = new int[1];
                int sizeOfMatrix = 0;
                BufferedScanner.TypeOfToken line = in.whatIsNext();
                while (line != BufferedScanner.TypeOfToken.NOTHING) {
                    while (line == BufferedScanner.TypeOfToken.NEW_LINE) {
                        sizeOfMatrix++;
                        matrix = resize(matrix, sizeOfMatrix);
                        matrix[sizeOfMatrix] = new int[1];
                        lengthsOfRows = resize(lengthsOfRows, sizeOfMatrix);
                        line = in.whatIsNext();
                    } 
                    if (line == BufferedScanner.TypeOfToken.TOKEN) {
                        String digit = in.getNext().toLowerCase();
                        matrix[sizeOfMatrix][lengthsOfRows[sizeOfMatrix]++] = 
                            (digit.charAt(digit.length() - 1) == 'o') 
                                ? Integer.parseUnsignedInt(digit.substring(0, digit.length() - 1), 8) 
                                : Integer.parseInt(digit);
                        matrix[sizeOfMatrix] = resize(matrix[sizeOfMatrix], lengthsOfRows[sizeOfMatrix]);                        
                        line = in.whatIsNext();
                    }
                }
                for (int i = sizeOfMatrix - 1; i >= 0; i--) {
                    for (int j = lengthsOfRows[i] - 1; j >= 0; j--) {
                        System.out.print(matrix[i][j] + " ");
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
