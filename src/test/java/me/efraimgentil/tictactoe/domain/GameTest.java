package me.efraimgentil.tictactoe.domain;

import me.efraimgentil.tictactoe.exception.InvalidMoveException;
import me.efraimgentil.tictactoe.input.PlayerInputHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {



    @Mock Player player1;
    @Mock Player player2;
    @Mock Player playerIA;

    @Mock
    PlayerInputHandler inputHandler1;

    @Mock
    PlayerInputHandler inputHandler2;

    @Mock
    PlayerInputHandler inputHandlerIA;

    @Before
    public void setUp(){
        when(player1.getPlayerChar()).thenReturn('X');
        when(player2.getPlayerChar()).thenReturn('O');
        when(playerIA.getPlayerChar()).thenReturn('I');
        when(player1.getInputHandler()).thenReturn(inputHandler1);
        when(player2.getInputHandler()).thenReturn(inputHandler2);
        when(playerIA.getInputHandler()).thenReturn(inputHandlerIA);
    }

    @Test
    public void shouldKeepGameRunningUntilTheWinnerIsFound() throws InvalidMoveException {
        BoardImpl board = new BoardImpl(3);
        Queue<Player> turnOrder = new LinkedList<>(Arrays.asList(player1, player2, playerIA));
        Game game = new Game(board, turnOrder);
        when(inputHandler1.handle(player1 , board , turnOrder)).thenReturn(
                new Move(1,3),
                new Move(2,3),
                new Move(3,3));
        when(inputHandler2.handle(player2 , board , turnOrder)).thenReturn(
                new Move(1,2),
                new Move(2,1));
        when(inputHandlerIA.handle(playerIA , board , turnOrder)).thenReturn(
                new Move(1,1),
                new Move(2,2));

        game.startGame();
        BoardState boardState = board.getBoardState();

        assertThat(boardState.getState()).isEqualTo(BoardState.State.WINNER_FOUND);
        assertThat(boardState.getPlayerChar()).isEqualTo('X');
    }

    @Test
    public void shouldKeepGameRunningUntilTheNoSpaceIsAvailableAnymore() throws InvalidMoveException {
        BoardImpl board = new BoardImpl(3);
        Queue<Player> turnOrder = new LinkedList<>(Arrays.asList(player1, player2, playerIA));
        Game game = new Game(board, turnOrder);
        when(inputHandler1.handle(player1 , board , turnOrder)).thenReturn(
                new Move(1,3),
                new Move(2,3),
                new Move(3,2));
        when(inputHandler2.handle(player2 , board , turnOrder)).thenReturn(
                new Move(1,2),
                new Move(2,1),
                new Move(3,3));
        when(inputHandlerIA.handle(playerIA , board , turnOrder)).thenReturn(
                new Move(1,1),
                new Move(2,2),
                new Move(3,1));

        game.startGame();
        BoardState boardState = board.getBoardState();

        assertThat(boardState.getState()).isEqualTo(BoardState.State.FILLED);
        assertThat(boardState.getPlayerChar()).isNull();
    }

}
