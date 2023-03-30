package game;

public class TicTacToePosition implements Position {
    private final TicTacToeBoard board;

    public TicTacToePosition(TicTacToeBoard board) {
        this.board = board;
    }

    @Override
    public Cell getTurn() {
        return board.getTurn();
    }

    public boolean isValid(Move move) {
        return board.isValid(move);
    }

    @Override
    public int getM() {
        return board.getM();
    }

    @Override
    public int getN() {
        return board.getN();
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
