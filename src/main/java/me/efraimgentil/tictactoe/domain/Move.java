package me.efraimgentil.tictactoe.domain;

public class Move {

    private int row;

    private int column;

    private int score;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Move(int row, int column, int score) {
        this(row , column);
        this.score = score;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (row != move.row) return false;
        return column == move.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return "Move{" +
                "row=" + row +
                ", column=" + column +
                ", score=" + score +
                '}';
    }
}
