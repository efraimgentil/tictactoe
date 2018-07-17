package me.efraimgentil.tictactoe.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class BoardStateCheckerImpl implements BoardStateChecker {

    private int expectedPoints;

    public BoardStateCheckerImpl(int expectedPoints) {
        this.expectedPoints = expectedPoints;
    }

    public Board updateBoardState(Board board) {
        BoardState state = checkBoard(((BoardImpl) board).getBoard());
        if (state == null) state = checkTie(board);
        // if state still null,means that the game still on going
        if (state == null) state = BoardState.onGoing();
        board.setBoardState(state);
        return board;
    }

    private BoardState checkTie(Board board) {
        if (board.getAvailableMoves().isEmpty()) {
            return new BoardState(BoardState.State.FILLED);
        }
        return null;
    }

    protected BoardState checkBoard(char[][] boardArr) {
        Set<Move> foundMoves = new LinkedHashSet<>(expectedPoints);
        for (int r = 0; r < boardArr.length; r++) {
            char[] row = boardArr[r];
            //CHECK ROW
            for (int c = 0; c + expectedPoints <= row.length; c++) {
                foundMoves.clear();
                char playerChar = row[c];
                if(playerChar ==  Board.EMPTY_POSITION_CHAR) continue; //skip if empty
                foundMoves.add(toMove(r, c));
                for (int n = 1; n < expectedPoints; n++) {
                    if (row[c + n] == Board.EMPTY_POSITION_CHAR || playerChar != row[c + n]) {
                        break;
                    }
                    foundMoves.add(toMove(r, c + n));
                }
                if (foundMoves.size() == expectedPoints) {
                    return BoardState.winnerFound(playerChar, foundMoves);
                }
            }

            //CHECK COLUMNS
            for (int c = 0; c < row.length; c++) {
                foundMoves.clear();
                char playerChar = boardArr[r][c];
                if(playerChar ==  Board.EMPTY_POSITION_CHAR) continue; //skip if empty
                foundMoves.add(toMove(r, c));
                for (int n = 1; n < expectedPoints && n < boardArr.length; n++) {
                    if (boardArr[n][c] == Board.EMPTY_POSITION_CHAR || playerChar != boardArr[n][c]) {
                        break;
                    }
                    foundMoves.add(toMove(n, c));
                }
                if (foundMoves.size() == expectedPoints) {
                    return BoardState.winnerFound(playerChar, foundMoves);
                }
            }

            //CHECK DIAGONAL LEFT TO RIGHT
            for (int c = 0; c + expectedPoints <= row.length; c++) {
                foundMoves.clear();
                char playerChar = boardArr[r][c];
                if(playerChar ==  Board.EMPTY_POSITION_CHAR) continue; //skip if empty
                foundMoves.add(toMove(r, c));
                for (int n = 1; n < expectedPoints && r + n < boardArr.length; n++) {
                    if (boardArr[r + n][c + n] == Board.EMPTY_POSITION_CHAR || playerChar != boardArr[r + n][c + n]) {
                        break;
                    }
                    foundMoves.add(toMove(r + n, c + n));
                }
                if (foundMoves.size() == expectedPoints) {
                    return BoardState.winnerFound(playerChar, foundMoves);
                }
            }

            //CHECK DIAGONAL RIGHT TO LEFT
            for (int c = (row.length - 1); c - (expectedPoints - 1) >= 0; c--) {
                foundMoves.clear();
                char playerChar = boardArr[r][c];
                if(playerChar ==  Board.EMPTY_POSITION_CHAR) continue; //skip if empty
                foundMoves.add(toMove(r, c));
                for (int n = 1; n < expectedPoints && r + n < boardArr.length && c - n >= 0; n++) {
                    if (boardArr[r + n][c - n] == Board.EMPTY_POSITION_CHAR || playerChar != boardArr[r + n][c - n]) {
                        break;
                    }
                    foundMoves.add(toMove(r + n, c - n));
                }
                if (foundMoves.size() == expectedPoints) {
                    return BoardState.winnerFound(playerChar, foundMoves);
                }
            }
        }
        //IF no winner found return null
        return null;
    }

    //Move row/col is +1 based (row 0 == row 1 when registering a move)
    private Move toMove(int row, int col) {
        return new Move(row + 1, col + 1);
    }

}
