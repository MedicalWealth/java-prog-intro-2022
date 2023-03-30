package game;

public class SequencePlayer implements Player {
    @Override
    public Move move(Position position) {
        int m = position.getM();
        int n = position.getN();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                final Move move = new Move(
                    r,
                    c,
                    position.getTurn()
                );
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("No valid moves");
    }
}
