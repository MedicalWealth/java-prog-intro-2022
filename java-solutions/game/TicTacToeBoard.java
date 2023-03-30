package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class TicTacToeBoard implements Board {
    private final int m;
    private final int n;
    private final int k;
    private final Cell[][] field;
    private Cell turn;
    private final Map<Cell, Character> CELL_TO_STRING = Map.of(
        Cell.X, 'X',
        Cell.O, 'O',
        Cell.E, '.',
        Cell.B, 'B'
    );
    private int empty;

    public TicTacToeBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.field = new Cell[m][n];
        for (Cell[] row: this.field) {
            Arrays.fill(row, Cell.E);
        }
        this.turn = Cell.X;
        this.empty = m * n;
    }

    public TicTacToeBoard(int m, int n, int k, int[] blockX, int[] blockY) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.field = new Cell[m][n];
        for (Cell[] row: this.field) {
            Arrays.fill(row, Cell.E);
        }
        for (int i = 0; i < blockX.length; i++) {
            field[blockX[i]][blockY[i]] = Cell.B;
        }
        this.turn = Cell.X;
        this.empty = m * n - blockX.length;
    }

    @Override
    public Position getPosition() {
        return new TicTacToePosition(this);
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        field[move.getRow()][move.getCol()] = move.getCell();
        empty--;
        Result result = getResult(move);
        if (result == Result.WIN) {
            return Result.WIN;
        } else if (result == Result.DRAW) {
            return Result.DRAW;
        } else {
            turn = turn == Cell.X ? Cell.O : Cell.X;
            return Result.UNKNOWN;
        }
    }

    public boolean isValid(Move move) {
        return  0 <= move.getRow() && move.getRow() < m &&
                0 <= move.getCol() && move.getCol() < n &&
                field[move.getRow()][move.getCol()] == Cell.E &&
                move.getCell() == turn;
    }

    private enum Line {
        VERT, HORIZ, DIAG_1, DIAG_2
    }

    private Result getResult(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        int numberOfCells = 0;
        for (Line type: Line.values()) {
            numberOfCells = Math.max(numberOfCells, countCells(row, col, type));
        }
        if (numberOfCells >= k) {
            return Result.WIN;
        } else if (empty == 0) {
            return Result.DRAW;
        } else {
            return Result.UNKNOWN;
        }
    }

    private int countCells(int row, int col, Line type) {
        int rowUp = row;
        int rowDown = row;
        int colRight = col;
        int colLeft = col;
        while (colLeft >= 0 && colRight < n && rowDown >= 0 && rowUp < m &&
            isValidCell(rowDown, rowUp, colLeft, colRight, row, col, type)) {
            rowDown = (type == Line.VERT || type == Line.DIAG_1) ? rowDown - 1 : rowDown;
            colLeft = (type == Line.HORIZ || type == Line.DIAG_1 || type == Line.DIAG_2) ? colLeft - 1 : colLeft;
            rowUp = (type == Line.DIAG_2) ? rowUp + 1 : rowUp;
        }
        if (type == Line.VERT) {
            rowDown++;
        } else if (type == Line.HORIZ) {
            colLeft++;
        } else if (type == Line.DIAG_1) {
            rowDown++;
            colLeft++;
        } else {
            colLeft++;
            rowUp--;
        }
        while (colLeft >= 0 && colRight < n && rowDown >= 0 && rowUp < m &&
            isValidCell(rowDown, rowUp, colLeft, colRight, row, col, type)) {
            rowUp = (type == Line.VERT || type == Line.DIAG_1) ? rowUp + 1 : rowUp;
            colRight = (type == Line.HORIZ || type == Line.DIAG_1 || type == Line.DIAG_2) ? colRight + 1 : colRight;
            rowDown = (type == Line.DIAG_2) ? rowDown - 1 : rowDown;
        }
        return Math.max(rowUp - rowDown, colRight - colLeft);
    }

    private boolean isValidCell(int rowDown, int rowUp, int colLeft, int colRight, int row, int col, Line type) {
        if (type == Line.VERT) {
            return field[rowDown][col] == turn && field[rowUp][col] == turn;
        } else if (type == Line.HORIZ) {
            return field[row][colLeft] == turn && field[row][colRight] == turn;
        } else if (type == Line.DIAG_1) {
            return field[rowDown][colLeft] == turn && field[rowUp][colRight] == turn;
        } else {
            return field[rowUp][colLeft] == turn && field[rowDown][colRight] == turn;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                sb.append(CELL_TO_STRING.get(field[r][c]));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public Cell getTurn() {
        return turn;
    }

    public void clearBoard() {
        int countBlock = 0;
        for (Cell[] row: this.field) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != Cell.B) {
                    row[i] = Cell.E;
                } else {
                    countBlock++;
                }
            }
        }
        this.turn = Cell.X;
        this.empty = m * n - countBlock;
    }
}
