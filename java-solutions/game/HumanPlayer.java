package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Reader in;
    private final PrintStream out;

    public HumanPlayer() {
        this.in = new Reader(System.in);
        this.out = new PrintStream(System.out);
    }

    @Override
    public Move move(Position position) {
        out.println("Position: ");
        out.println(position);
        int row = in.readInt("row") - 1;
        int col = in.readInt("column") - 1;
        Move move = new Move(row, col, position.getTurn());
        while (!position.isValid(move)) {
            out.println("Invalid move! Please, enter another row and column");
            row = in.readInt("row") - 1;
            col = in.readInt("column") - 1;
            move = new Move(row, col, position.getTurn());
        }
        return move;
    }
}
