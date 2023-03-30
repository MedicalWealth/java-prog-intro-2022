import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Equidistant {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numberOfCities = in.nextInt();
        int numberOfTeams = in.nextInt();
        List<IntList> country = new ArrayList<>(numberOfCities + 1);
        for (int i = 0; i <= numberOfCities; i++) {
            country.add(new IntList());
        }
        for (int i = 1; i < numberOfCities; i++) {
            int firstCity = in.nextInt();
            int secondCity = in.nextInt();
            country.get(firstCity).add(secondCity);
            country.get(secondCity).add(firstCity);
        }
        IntList teamInCity = new IntList();
        teamInCity.add(0);
        for (int i = 1; i <= numberOfTeams; i++) {
            int team = in.nextInt();
            teamInCity.add(team);
        }
        in.close();
        int[] depthsFirstTeam = new int[numberOfCities + 1];
        depthsFirstTeam[0] = -1;
        dfs(country, teamInCity.get(1), 0, depthsFirstTeam);
        int maxDepth = -1, deepestTeam = -1;
        for (int i = 1; i < teamInCity.size(); i++) {
            if (maxDepth < depthsFirstTeam[teamInCity.get(i)]) {
                maxDepth = depthsFirstTeam[teamInCity.get(i)];
                deepestTeam = teamInCity.get(i);
            }
        }
        if (maxDepth % 2 == 1) {
            System.out.println("NO");
        } else {
            int[] depthsRemoteTeam = new int[numberOfCities + 1];
            depthsRemoteTeam[0] = -1;
            dfs(country, deepestTeam, 0, depthsRemoteTeam);
            for (int i = 1; i < depthsRemoteTeam.length; i++) {
                if (depthsRemoteTeam[i] == maxDepth / 2 && depthsFirstTeam[i] == maxDepth / 2) {
                    int[] depthsMiddleCity = new int[numberOfCities + 1];
                    depthsMiddleCity[0] = -1;
                    dfs(country, i, 0, depthsMiddleCity);
                    boolean flag = true;
                    for (int j = 1; j < teamInCity.size(); j++) {
                        if (depthsMiddleCity[teamInCity.get(j)] != maxDepth / 2) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.println("YES");
                        System.out.println(i);
                    } else {
                        System.out.println("NO");
                    }
                    break;
                }
            }
        }
    }

    public static void dfs(List<IntList> country, int currentCity, int previousCity, int[] deepToCity) {
        deepToCity[currentCity] = deepToCity[previousCity] + 1;
        for (int i = 0; i < country.get(currentCity).size(); i++) {
            if (country.get(currentCity).get(i) != previousCity) {
                dfs(country, country.get(currentCity).get(i), currentCity, deepToCity);
            }
        }
    }

    static class IntList {
        private int[] storage = new int[1];
        private int size;

        public void add(int digit) {
            storage[size++] = digit;
            ensureCapacity();
        }

        public int size() {
            return size;
        }

        public int get(int index) {
            return storage[index];
        }

        private void ensureCapacity() {
            if (size == storage.length) {
                storage = Arrays.copyOf(storage, size * 2);
            }
        }
    }
}