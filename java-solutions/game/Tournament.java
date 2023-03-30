package game;

public class Tournament {
    private final Player[] players;
    private final Board board;
    public Tournament(Player[] players, Board board) {
        this.players = players;
        this.board = board;
    }

    private int[] getResult() {
        int[] result = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players.length; j++) {
                if (i != j) {
                    int res = new Game(players[i], players[j], true).play(board);
                    if (res == 1) {
                        result[i] += 3;
                    } else if (res == 2) {
                        result[j] += 3;
                    } else {
                        result[i] += 1;
                        result[j] += 1;
                    }
                    board.clearBoard();
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int[] result = getResult();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append("Player ").append(i + 1).append(": ").append(result[i]).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
