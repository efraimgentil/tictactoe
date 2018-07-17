package me.efraimgentil.tictactoe.domain;

import java.util.Set;

public class BoardState {

    private State state;

    private Character playerChar;

    private Set<Move> winningMoves;

    public static BoardState onGoing() {
        return new BoardState(State.ON_GOING);
    }

    public static BoardState winnerFound(char playerChar, Set<Move> winningMoves) {
        BoardState boardState = new BoardState(State.WINNER_FOUND, playerChar);
        boardState.winningMoves = winningMoves;
        return boardState;
    }

    public BoardState(BoardState.State state) {
        this.state = state;
    }

    public BoardState(State state, char playerChar) {
        this.state = state;
        this.playerChar = playerChar;
    }

    public boolean isFilled() {
        return State.FILLED.equals(state);
    }

    public boolean isWinnerFound() {
        return State.WINNER_FOUND.equals(state);
    }

    public boolean isOnGoing() {
        return State.ON_GOING.equals(state);
    }

    public BoardState.State getState() {
        return state;
    }

    public Character getPlayerChar() {
        return playerChar;
    }

    public Set<Move> getWinningMoves() {
        return winningMoves;
    }

    @Override
    public String toString() {
        return "BoardState{" +
                "state=" + state +
                ", playerChar=" + playerChar +
                '}';
    }

    public static enum State {
        ON_GOING,
        FILLED,
        WINNER_FOUND;
    }
}
