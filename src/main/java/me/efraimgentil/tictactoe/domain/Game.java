package me.efraimgentil.tictactoe.domain;

import me.efraimgentil.tictactoe.exception.InvalidMoveException;
import me.efraimgentil.tictactoe.tracer.BoardTracer;

import java.util.Queue;

public class Game {

    private Board board;

    private Queue<Player> turnOrder;

    public Game(Board board, Queue<Player> turnOrder) {
        this.board = board;
        this.turnOrder = turnOrder;
    }

    public void startGame() throws InvalidMoveException {
        board.draw();
        while (board.getBoardState().isOnGoing()) {
            Player currentPlayer = getNextPlayer();
            Move move = currentPlayer.getInputHandler().handle(currentPlayer, board, turnOrder);
            board.registerMove(move , currentPlayer.getPlayerChar());
            board.draw();
        }
        finishGame(board.getBoardState());
    }

    private void finishGame(BoardState boardState) {
        if(boardState.isFilled()){
            System.out.println("Game ended in TIE");
        }else if(boardState.isWinnerFound()){
            System.out.println(String.format("Game ended with Winner '%s'", boardState.getPlayerChar() ));
        }
    }


    private Player getNextPlayer() {
        Player nextPlayer = turnOrder.poll();
        turnOrder.add(nextPlayer);//add player back to the end of the queue
        return nextPlayer;
    }



}

