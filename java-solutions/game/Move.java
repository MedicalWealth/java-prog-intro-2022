package game;

public class Move {
    private final int row;
    private final int col;
    private final Cell cell;

    public Move(int row, int col, Cell cell) {
        this.row = row;
        this.col = col;
        this.cell = cell;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getCell() {
        return cell;
    }
}
