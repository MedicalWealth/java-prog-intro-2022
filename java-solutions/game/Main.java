package game;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Reader in = new Reader(System.in);
        int numberOfPlayers = in.readInt("number of players");
        while (numberOfPlayers < 2) {
            System.out.println("Oops! You should enter decimal number >= 2");
            numberOfPlayers = in.readInt("number of players");
        }
        int m = in.readInt("m");
        int n = in.readInt("n");
        int k = in.readInt("k");
        Player[] players = new Player[numberOfPlayers];
        Arrays.fill(players, new RandomPlayer());
        System.out.println(new Tournament(players, new TicTacToeBoard(m, n, k)));
    }
}
