import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManagingDifficulties {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfTests = in.nextInt();
        for (int t = 0; t < numberOfTests; t++) {
            int numberOfDays = in.nextInt();
            int answer = 0;
            int[] days = new int[numberOfDays];
            for (int i = 0; i < numberOfDays; i++) {
                days[i] = in.nextInt();
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < numberOfDays; j++) {
                for (int k = j + 1; k < numberOfDays; k++) {
                    int neededNumber = 2 * days[j] - days[k];
                    answer += map.getOrDefault(neededNumber, 0);
                }
                map.put(days[j], map.getOrDefault(days[j], 0) + 1);
            }
            System.out.println(answer);
        }
        in.close();
    }
}