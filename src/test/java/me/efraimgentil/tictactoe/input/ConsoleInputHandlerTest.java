package me.efraimgentil.tictactoe.input;

import me.efraimgentil.tictactoe.domain.Board;
import me.efraimgentil.tictactoe.domain.Move;
import me.efraimgentil.tictactoe.domain.Player;
import me.efraimgentil.tictactoe.exception.InvalidMoveException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleInputHandlerTest {

    ConsoleInputHandler handler;

    @Mock
    Board board;

    @Mock
    Queue<Player> playersOrder;

    @Mock
    Player player;

    InputStream stdin;

    @Before
    public void setUp() {
        handler = spy(new ConsoleInputHandler());
        stdin = System.in;
    }

    @After
    public void destroy() {
        System.setIn(stdin);
    }

    @Test
    public void shouldReceiveTheInputAndRegisterTheMoveInTheBoard() throws InvalidMoveException {
        String validInput = "1,3";
        System.setIn(new ByteArrayInputStream(validInput.getBytes())); //fake scanner in
        when(player.getPlayerChar()).thenReturn('A');
        when(board.getBoardSize()).thenReturn(3);
        when(board.isMoveAvailable(any(Move.class))).thenReturn(true);

        Move result = handler.handle(player, board, playersOrder);

        assertThat(result).isEqualTo(new Move(1,3));
    }

    @Test
    public void shouldFailTheFirstTiemAndThenTryAgainWithTheCorrectInput() throws InvalidMoveException {
        String validInput = "1,M\n2,3";
        System.setIn(new ByteArrayInputStream(validInput.getBytes())); //fake scanner in
        when(player.getPlayerChar()).thenReturn('A');
        when(board.getBoardSize()).thenReturn(3);
        when(board.isMoveAvailable(any(Move.class))).thenReturn(true);

        Move result = handler.handle(player, board, playersOrder);

        verify(handler, times(1)).translateInputToMove("1,M" , board);
        verify(handler, times(1)).translateInputToMove("2,3" , board);
        assertThat(result).isEqualTo(new Move(2,3) );
    }

    @Test
    public void shouldTranslateToMoveObject() throws InvalidMoveException {
        when(board.getBoardSize()).thenReturn(3);
        when(board.isMoveAvailable(any(Move.class))).thenReturn(true);

        Move move = handler.translateInputToMove("1,1", board);

        assertThat(move.getRow()).isEqualTo(1);
        assertThat(move.getColumn()).isEqualTo(1);
    }

    @Test
    public void shouldTranslateToMoveObjectWhenBoardBigger() throws InvalidMoveException {
        when(board.getBoardSize()).thenReturn(6);
        when(board.isMoveAvailable(any(Move.class))).thenReturn(true);

        Move move = handler.translateInputToMove("5,4", board);

        assertThat(move.getRow()).isEqualTo(5);
        assertThat(move.getColumn()).isEqualTo(4);
    }

    @Test(expected = InvalidMoveException.class)
    public void shouldThrowInvalidMoveIfInputBiggerThanBoard() throws InvalidMoveException {
        when(board.getBoardSize()).thenReturn(3);

        handler.translateInputToMove("1,4", board);
    }

    @Test(expected = InvalidMoveException.class)
    public void shouldThrowInvalidMoveIfInputInWrongFormat() throws InvalidMoveException {

        handler.translateInputToMove("14", board);
    }

    @Test(expected = InvalidMoveException.class)
    public void shouldThrowInvalidMoveIfInputHaveLetters() throws InvalidMoveException {
        when(board.getBoardSize()).thenReturn(3);

        handler.translateInputToMove("ONE,4", board);
    }


}
