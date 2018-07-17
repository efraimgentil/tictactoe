package me.efraimgentil.tictactoe.domain;

import me.efraimgentil.tictactoe.exception.InvalidMoveException;
import me.efraimgentil.tictactoe.tracer.BoardTracer;
import me.efraimgentil.tictactoe.tracer.ConsoleBoardTracer;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board<char[][]>{

    private char[][] board;
    private int boardSize;
    private BoardState boardState;
    private BoardStateChecker stateChecker;
    private BoardTracer boardTracer;

    public BoardImpl(int boardSize) {
        this(boardSize , 3);
    }

    public BoardImpl(int boardSize , int pointsToWin) {
        this.boardSize = boardSize;
        this.board = createBoard(boardSize);
        this.stateChecker = new BoardStateCheckerImpl(pointsToWin);
        this.boardState = BoardState.onGoing();
        this.boardTracer = new ConsoleBoardTracer();
    }

    public void registerMove(Move move , char charVal) throws InvalidMoveException {
        if(!isMoveAvailable(move)){
            throw new InvalidMoveException("The move is invalid, position is already marked");
        }
        board[move.getRow()-1][move.getColumn()-1] = charVal;
        stateChecker.updateBoardState(this);
    }

    public void unregisterMove(Move move) {
        board[move.getRow()-1][move.getColumn()-1] = EMPTY_POSITION_CHAR;
    }

    @Override
    public void draw() {
        this.boardTracer.draw(this);
    }

    public boolean isInEndState(){
        return boardState.isFilled() || boardState.isWinnerFound();
    }

    /**
     * Create an empty board for the game
     */
    private char[][] createBoard(Integer boardSize) {
        char[][] board = new char[boardSize][boardSize];
        for(int r = 0 ; r < board.length ; r++){
            for(int c = 0 ; c < board.length ; c++){
                board[r][c] = EMPTY_POSITION_CHAR;
            }
        }
        return board;
    }

    public List<Move> getAvailableMoves(){
        List<Move> moves = new ArrayList<>();
        for(int r = 0 ; r < board.length ; r++){
            for(int c = 0 ; c < board.length ; c++){
                if(board[r][c] == EMPTY_POSITION_CHAR){
                    moves.add(new Move(r+1 ,c+1));
                }
            }
        }
        return moves;
    }

    @Override
    public boolean isMoveAvailable(Move move) {
        return board[move.getRow()-1][move.getColumn()-1] == EMPTY_POSITION_CHAR;
    }

    @Override
    public char[][] getBoard() {
        return board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public void setBoardState(BoardState boardState) {
        this.boardState = boardState;
    }

}
