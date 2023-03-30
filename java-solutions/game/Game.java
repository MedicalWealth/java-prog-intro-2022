package game;

public class Game {
    private final Player player1;
    private final Player player2;
    private final boolean log;

    public Game(Player player1, Player player2, boolean log) {
        this.player1 = player1;
        this.player2 = player2;
        this.log = log;
    }

    public int play(final Board board) {
        while (true) {
            final int result1 = makeMove(board, player1, 1);
            if (result1 >= 0) {
                return result1;
            }
            final int result2 = makeMove(board, player2, 2);
            if (result2 >= 0) {
                return result2;
            }
        }
    }

    private int makeMove(Board board, Player player, int number) {
        try {
            Move move = player.move(board.getPosition());
            Result result = board.makeMove(move);
            if (log) {
                System.out.printf("Player #%d, move: (%d, %d, %s)\n",
                    number, move.getRow() + 1, move.getCol() + 1, move.getCell()
                );
                System.out.println(board);
            }
            if (result == Result.WIN) {
                return number;
            } else if (result == Result.DRAW) {
                return 0;
            } else if (result == Result.LOSE) {
                return 3 - number;
            } else {
                return -1;
            }
        } catch (Exception e) {
            return 3 - number;
        }
    }
}