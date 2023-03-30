import java.util.Scanner;

public class DoublePalindrome {

    private final static long PRIME = 998_244_353L;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long k = in.nextLong();
        in.close();
        long[] numberOfWays = new long[(int) n + 1];
        long[] numberOfPalindrome = new long[(int) n + 1];
        long[] prefixSum = new long[(int) n + 1];
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) {
                numberOfWays[i] = i * fastPow(k, (i + 1) / 2);
            } else {
                numberOfWays[i] = i / 2 * fastPow(k, i / 2) + i / 2 * fastPow(k , i / 2 + 1);
            }
            numberOfWays[i] = takePrime(numberOfWays[i]);
            numberOfPalindrome[i] = numberOfWays[i];
            int l = 1;
            while (l * l < i) {
                if (i % l == 0) {
                    numberOfPalindrome[i] -= i / l * numberOfPalindrome[l];
                    prefixSum[i] += numberOfPalindrome[l];
                    if (l != 1) {
                        numberOfPalindrome[i] -= l * numberOfPalindrome[i / l];
                        prefixSum[i] += numberOfPalindrome[i / l];
                    }
                    prefixSum[i] = takePrime(prefixSum[i]);
                    numberOfPalindrome[i] = takePrime(numberOfPalindrome[i]);
                }
                l++;
            }
            if (l * l == i && l != 1) {
                numberOfPalindrome[i] -= i / l * numberOfPalindrome[l];
                prefixSum[i] += numberOfPalindrome[l];
                prefixSum[i] = takePrime(prefixSum[i]);
                numberOfPalindrome[i] = takePrime(numberOfPalindrome[i]);
            }
            prefixSum[i] += numberOfPalindrome[i];
        }
        long answer = 0;
        for (int i = 1; i <= n; i++) {
            answer += prefixSum[i];
            answer = takePrime(answer);
        }
        System.out.println(answer);
    }

    public static long fastPow(long a, long b) {
        long result = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                result *= a;
            }
            a *= a;
            a = takePrime(a);
            b /= 2;
            result = takePrime(result);
        }
        return result;
    }

    public static long takePrime(long a) {
        return (a + PRIME) % PRIME;
    }
}