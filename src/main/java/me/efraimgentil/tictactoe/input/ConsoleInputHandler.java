package me.efraimgentil.tictactoe.input;

import me.efraimgentil.tictactoe.domain.Board;
import me.efraimgentil.tictactoe.domain.Move;
import me.efraimgentil.tictactoe.domain.Player;
import me.efraimgentil.tictactoe.exception.InvalidMoveException;

import java.util.Queue;
import java.util.Scanner;

public class ConsoleInputHandler implements PlayerInputHandler {

    @Override
    public Move handle(Player player , Board board, Queue<Player> playerQueue) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            //will keep requesting the input, until the player gives a correct input
            try{
                System.out.println(String.format(INPUT_MSG, player.getPlayerChar() ));
                return translateInputToMove(scanner.nextLine() , board);
            }catch(InvalidMoveException ime){
                System.out.println(ime.getMessage());
            }
        }
    }

    protected Move translateInputToMove(String input, Board board) throws InvalidMoveException {
        String[] split = input.split(",");
        if(split.length != 2){
            throw new InvalidMoveException(String.format(INVALID_INPUT , input));
        }else{
           int row = translateToIdxValue(split[0] , input , INVALID_ROW_VALUE , board.getBoardSize()); //ROW
           int col = translateToIdxValue(split[1] , input , INVALID_COLUMN_VALUE, board.getBoardSize()); //COL
           Move move = new Move(row, col);
           if(!board.isMoveAvailable(move)){
               throw new InvalidMoveException(INVALID_POSITION_ALREADY_TAKEN);
           }
           return move;
        }
    }

    protected int translateToIdxValue(String val , String originalInput , String messageTemplate, int boardSize) throws InvalidMoveException {
        if(!val.matches("[0-9]+")){
            throw new InvalidMoveException(String.format(messageTemplate , originalInput, 1 , boardSize));
        }else{
            Integer integer = Integer.valueOf(val);
            if( (integer - 1)  < 0 || (integer-1) > (boardSize-1)){
                throw new InvalidMoveException(String.format(messageTemplate, originalInput , 1 , boardSize ));
            }
            return integer;
        }
    }
}
