import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class HighLoadDatabase {
    public static int lowerBound(int[] array, int left, int right, int value) {
        int lastBlock = left + 1;
        while (left < right - 1) {
            int mid = (left + right) / 2;
            if (array[mid] - array[lastBlock] <= value) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTrans = in.nextInt();
        int maximumOfQueries = 0;
        int[] prefixSum = new int[numberOfTrans + 1];
        for (int i = 0; i < numberOfTrans; i++) {
            int query = in.nextInt();
            prefixSum[i + 1] = prefixSum[i] + query;
            maximumOfQueries = Math.max(maximumOfQueries, query);
        }
        int numberOfQueries = in.nextInt();
        int[] answers = new int[prefixSum[numberOfTrans] + 1];
        for (int i = 0; i < numberOfQueries; i++) {
            int query = in.nextInt();
            if (query < maximumOfQueries) {
                System.out.println("Impossible");
                continue;
            }
            if (query > prefixSum[numberOfTrans] || answers[query] != 0) {
                System.out.println(Math.max(1, answers[query]));
                continue;
            }
            int lastBlock = 0;
            int answer = 0;
            while (lastBlock < numberOfTrans) {
                lastBlock = lowerBound(prefixSum, lastBlock - 1, numberOfTrans + 1, query);
                answer++;
            }
            answers[query] = answer;
            System.out.println(answer);
        }
        in.close();
    }
}