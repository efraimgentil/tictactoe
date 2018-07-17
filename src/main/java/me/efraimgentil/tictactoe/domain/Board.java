package me.efraimgentil.tictactoe.domain;

import me.efraimgentil.tictactoe.exception.InvalidMoveException;

import java.util.List;

public interface Board<BoardFormat> {

    public static Character EMPTY_POSITION_CHAR = ' ';

    BoardFormat getBoard();

    int getBoardSize();

    void draw();

    List<Move> getAvailableMoves();

    boolean isMoveAvailable(Move move);

    void registerMove(Move move, char charVal) throws InvalidMoveException;

    void unregisterMove(Move move) throws InvalidMoveException;

    BoardState getBoardState();

    void setBoardState(BoardState boardState);

    boolean isInEndState();

}
