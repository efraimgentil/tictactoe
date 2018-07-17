package me.efraimgentil.tictactoe.input;

import me.efraimgentil.tictactoe.domain.BoardImpl;
import me.efraimgentil.tictactoe.domain.Move;
import me.efraimgentil.tictactoe.domain.Player;
import me.efraimgentil.tictactoe.exception.InvalidMoveException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class IAInputHandlerTest {

    IAInputReceiver iaReceiver;

    @Before
    public void setUp(){
        iaReceiver = new IAInputReceiver(3);
    }


    @Test
    public void shouldReturnTheMoveToBlockThePlayerVictory() throws InvalidMoveException {
        BoardImpl board = new BoardImpl(3);
        Player hPlayer = new Player('A', false, null);
        Player iaPlayer = new Player('B', false, null);
        LinkedList<Player> turnOrder = new LinkedList<>(Arrays.asList(hPlayer,iaPlayer ));
        board.registerMove( new Move(3,1 ) , 'A');
        board.registerMove( new Move(3,2 ) , 'A');
        board.registerMove( new Move(2,1 ) , 'B');
        board.registerMove( new Move(1,1 ) , 'B');
        iaReceiver.setMaxPlayer(iaPlayer);

        Move move = iaReceiver.handle(iaPlayer,board , turnOrder );

        //BLOCKS enemy victory
        assertThat(move.getRow()).isEqualTo(3);
        assertThat(move.getColumn()).isEqualTo(3);
    }

    @Test
    public void shouldReturnMoveToVictory() throws InvalidMoveException {
        BoardImpl board = new BoardImpl(3);
        Player hPlayer = new Player('A', false, null);
        Player iaPlayer = new Player('B', false, null);
        LinkedList<Player> turnOrder = new LinkedList<>(Arrays.asList(hPlayer,iaPlayer ));
        board.registerMove( new Move(3,1 ) , 'B');
        board.registerMove( new Move(3,2 ) , 'B');
        board.registerMove( new Move(2,1 ) , 'A');
        board.registerMove( new Move(1,1 ) , 'A');
        iaReceiver.setMaxPlayer(iaPlayer);

        Move move = iaReceiver.miniMax(board, iaPlayer, turnOrder, 3);

        //BLOCKS enemy victory
        assertThat(move.getRow()).isEqualTo(3);
        assertThat(move.getColumn()).isEqualTo(3);
    }

    @Test
    public void shouldPredictPerfectMove() throws InvalidMoveException {
        BoardImpl board = new BoardImpl(3);
        Player hPlayer = new Player('A', false, null);
        Player iaPlayer = new Player('B', false, null);
        LinkedList<Player> turnOrder = new LinkedList<>(Arrays.asList(hPlayer,iaPlayer ));
        board.registerMove( new Move(1,1 ) , 'A');
        iaReceiver = new IAInputReceiver(5);
        iaReceiver.setMaxPlayer(iaPlayer);

        Move move = iaReceiver.miniMax(board, iaPlayer, turnOrder, 0);

        System.out.println(move);
    }





}
