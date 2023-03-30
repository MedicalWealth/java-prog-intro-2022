package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move move(Position position) {
        int m = position.getM();
        int n = position.getN();
        while (true) {
            final Move move = new Move(
                random.nextInt(m),
                random.nextInt(n),
                position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
