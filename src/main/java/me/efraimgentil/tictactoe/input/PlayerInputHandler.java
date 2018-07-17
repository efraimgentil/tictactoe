package me.efraimgentil.tictactoe.input;

import me.efraimgentil.tictactoe.domain.Board;
import me.efraimgentil.tictactoe.domain.Move;
import me.efraimgentil.tictactoe.domain.Player;

import java.util.Queue;

public interface PlayerInputHandler {
     String INPUT_MSG = "Player '%s' turn, inform a position in the format 'row number,column number' to be marked in the board! Ex: 1,1";
     String INVALID_INPUT = "Input '%s' invalid, the input should consist of 'row number,column number' value.";
     String INVALID_ROW_VALUE = "Input '%s' invalid, the 'row' value should be a numeric value between %d and %d.";
     String INVALID_COLUMN_VALUE = "Input '%s' invalid, the 'column' value should be a numeric value between %d and %d.";
     String INVALID_POSITION_ALREADY_TAKEN = "The move is invalid, position is already marked";

     Move handle(Player player, Board board, Queue<Player> playerOrder);

}
