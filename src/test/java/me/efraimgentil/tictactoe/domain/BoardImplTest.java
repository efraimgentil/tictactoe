package me.efraimgentil.tictactoe.domain;

import me.efraimgentil.tictactoe.exception.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardImplTest {

    BoardImpl board;


    @Test(expected = InvalidMoveException.class)
    public void shouldReturnErrorIfMoveIsAlreadyRegistered() throws InvalidMoveException {
        board = new BoardImpl(3);
        Move move = new Move(3, 3);

        board.registerMove(move , 'A');
        board.registerMove(move , 'A');
    }

    @Test
    public void shouldRegisterMoveSuccessfully() throws InvalidMoveException {
        board = new BoardImpl(3);
        Move move = new Move(3, 3);

        board.registerMove(move , 'A');

        Character positionVal = board.getBoard()[move.getRow() - 1][move.getColumn() - 1];
        assertThat(positionVal).isEqualTo('A');
    }

    @Test
    public void shouldUnregisterMove() throws InvalidMoveException {
        board = new BoardImpl(3);
        Move move = new Move(3, 3);
        board.registerMove(move , 'A');
        Character positionVal = board.getBoard()[move.getRow() - 1][move.getColumn() - 1];
        assertThat(positionVal).isEqualTo('A');

        board.unregisterMove(move);

        positionVal = board.getBoard()[move.getRow() - 1][move.getColumn() - 1];
        assertThat(positionVal).isEqualTo(Board.EMPTY_POSITION_CHAR);
    }

    @Test
    public void shouldReturnFalseIfMoveIsAlreadyDone() throws InvalidMoveException {
        board = new BoardImpl(3);
        Move move = new Move(2, 2);
        board.registerMove(move , 'A');

        assertThat(board.isMoveAvailable(new Move(2,2))).isFalse();
    }

    @Test
    public void shouldReturnTrueIfMoveIsNotDoneYet() throws InvalidMoveException {
        board = new BoardImpl(3);
        Move move = new Move(2, 2);

        assertThat(board.isMoveAvailable(new Move(2,2))).isTrue();
    }


}
