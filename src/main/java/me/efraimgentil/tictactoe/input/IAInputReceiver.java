package me.efraimgentil.tictactoe.input;

import me.efraimgentil.tictactoe.domain.Board;
import me.efraimgentil.tictactoe.domain.BoardState;
import me.efraimgentil.tictactoe.domain.Move;
import me.efraimgentil.tictactoe.domain.Player;
import me.efraimgentil.tictactoe.exception.InvalidMoveException;

import java.util.*;

public class IAInputReceiver implements PlayerInputHandler {

    private int maxDepth;
    private Player maxPlayer;

    public IAInputReceiver(int depth) {
        this.maxDepth = depth;
    }

    @Override
    public Move handle(Player iaPLayer, Board board, Queue<Player> playerOrder) {
        maxPlayer = iaPLayer;
        try {
            System.out.println(String.format("IA %s turn, she is thinking, don't rush her" , maxPlayer.getPlayerChar()));
            Move move = miniMax(board, iaPLayer, new LinkedList<>(playerOrder), 0);
            System.out.println(String.format("IA %s move (%s)" , maxPlayer.getPlayerChar() , move.getRow() +","+move.getColumn()));
            return move;
        } catch (InvalidMoveException e) {
            throw new RuntimeException("The IA is messed up, the game is broken");
        }
    }

    /**
     * Recursively go through the available moves and simulate moves ahead, then pick
     * a "best" move the me played
     */
    public Move miniMax(Board board, Player currentPlayer, LinkedList<Player> playersOrder, int depth) throws InvalidMoveException {
        List<Move> availableMoves = board.getAvailableMoves();
        List<Move> suggestedMoves = new ArrayList<>(availableMoves.size()); //available moves will be = to empty spots
        for(Move move : availableMoves){
            board.registerMove(move, currentPlayer.getPlayerChar());
            BoardState boardState = board.getBoardState();
            int score = score(currentPlayer, boardState , depth);
            if (boardState.isOnGoing() && depth + 1 < maxDepth) {
                suggestedMoves.add( miniMax(board, playersOrder.peek()
                        , newPlayerOrder(playersOrder), depth + 1));
            }else {
                suggestedMoves.add(new Move(move.getRow(), move.getColumn(), score));
            }
            board.unregisterMove(move);
        }
        return getBestAvailableMove(currentPlayer , suggestedMoves);
    }

    private Move getBestAvailableMove(Player currentPlayer, List<Move> availableMoves) {
        Comparator<Move> comparator = Comparator.comparing(Move::getScore);
        //return the best move if IA
        if(currentPlayer.equals(maxPlayer)) {
            return availableMoves.stream().map(m ->
                new Move(m.getRow() , m.getColumn() ,Math.abs(m.getScore()))
            ).max(comparator).get();
        }
        //return the worst move if enemy
        return availableMoves.stream().min(comparator).get();
    }

    /**
     * Give a score to the move based on the boardState and how deep is in the recursion
     */
    private int score(Player currentPlayer, BoardState boardState, int depth) {
        int score = 0;
        if (boardState.isWinnerFound() && currentPlayer.equals(maxPlayer)) {
            score = depth > 0 ? 10 / depth : 20;
        }
        if (boardState.isWinnerFound() && !currentPlayer.equals(maxPlayer)) {
            score =  depth > 0  ? (-12 / depth) : -12;
        }
        return score;
    }

    private LinkedList<Player> newPlayerOrder(LinkedList<Player> playersOrder){
        LinkedList<Player> newPlayerOrder = new LinkedList<>(playersOrder);
        Player nextPlayer = newPlayerOrder.removeFirst();
        newPlayerOrder.add(nextPlayer);
        return newPlayerOrder;
    }

    protected void setMaxPlayer(Player maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

}
