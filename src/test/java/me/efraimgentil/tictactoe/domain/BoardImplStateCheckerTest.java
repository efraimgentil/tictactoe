package me.efraimgentil.tictactoe.domain;

import org.junit.Before;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class BoardImplStateCheckerTest {

    BoardStateCheckerImpl checker;

    @Before
    public void setUp(){
        checker = new BoardStateCheckerImpl(3);
    }

    @Test
    public void shouldFindVictoryByLine(){
        char[][] boardArr = new char[][]{
                { 'X', ' ', 'X', 'X' },
                { ' ', 'O', 'O', 'O' },
                { ' ', ' ', ' ', ' ' },
                { 'X', 'O', ' ', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('O');
        assertThat(boardState.getWinningMoves()).contains( new Move(2,2) , new Move(2,3) , new Move(2,4) );
    }

    @Test
    public void shouldFindVictoryByLine2(){
        char[][] boardArr = new char[][]{
                { 'X', 'O', ' ' },
                { ' ', 'O', 'O' },
                { 'X', 'X', 'X' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('X');
        assertThat(boardState.getWinningMoves()).contains( new Move(3,1) , new Move(3,2) , new Move(3,3) );
    }

    @Test
    public void shouldFindVictoryByColumn(){
        char[][] boardArr = new char[][]{
                { ' ', ' ', 'X', 'X' },
                { 'X', 'O', ' ', 'O' },
                { 'X', ' ', ' ', ' ' },
                { 'X', 'O', ' ', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('X');

        assertThat(boardState.getWinningMoves()).contains( new Move(2,1) , new Move(3,1) , new Move(4,1) );
    }

    @Test
    public void shouldFindVictoryByColumn2(){
        char[][] boardArr = new char[][]{
                { ' ', ' ', ' ', 'X' },
                { 'X', 'O', 'X', 'O' },
                { ' ', ' ', 'X', ' ' },
                { 'X', 'O', 'X', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('X');
        assertThat(boardState.getWinningMoves()).contains( new Move(2,3) , new Move(3,3) , new Move(4,3) );
    }

    @Test
    public void shouldFindVictoryByDiagonalLefToRight(){
        char[][] boardArr = new char[][]{
                { 'X', ' ', ' ', 'X' },
                { ' ', 'X', 'X', 'O' },
                { ' ', 'O', 'X', ' ' },
                { 'X', 'O', ' ', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('X');
        assertThat(boardState.getWinningMoves()).contains( new Move(1,1) , new Move(2,2) , new Move(3,3) );
    }

    @Test
    public void shouldFindVictoryByDiagonalLefToRight2(){
        char[][] boardArr = new char[][]{
                { 'X', 'X', ' ', 'X' },
                { ' ', 'O', 'X', 'O' },
                { ' ', ' ', 'X', 'X' },
                { 'X', 'O', ' ', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('X');
        assertThat(boardState.getWinningMoves()).contains( new Move(1,2) , new Move(2,3) , new Move(3,4) );
    }

    @Test
    public void shouldFindVictoryByDiagonalLefToRight3(){
        char[][] boardArr = new char[][]{
                { 'X', 'X', ' ', 'X', ' ' },
                { 'X', 'O', 'C', 'O', ' ' },
                { 'O', 'C', 'X', ' ', 'O' },
                { 'X', 'O', 'C', 'C', ' ' },
                { 'O', 'X', 'X', 'C', ' ' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('C');
        assertThat(boardState.getWinningMoves()).contains( new Move(3,2) , new Move(4,3) , new Move(5,4) );
    }

    @Test
    public void shouldFindVictoryByDiagonalLefToRight4(){
        char[][] boardArr = new char[][]{
                { 'X', 'X', ' ', 'X', ' ' },
                { 'X', 'O', 'C', 'O', ' ' },
                { 'O', 'C', 'X', ' ', 'O' },
                { 'X', 'O', ' ', 'X', ' ' },
                { 'O', 'X', 'X', 'C', 'X' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('X');
        assertThat(boardState.getWinningMoves()).contains( new Move(3,3) , new Move(4,4) , new Move(5,5) );
    }

    @Test
    public void shouldFindVictoryByDiagonalRightToLeft(){
        char[][] boardArr = new char[][]{
                { 'X', 'X', ' ', 'X' },
                { ' ', 'O', 'X', 'O' },
                { ' ', 'X', ' ', ' ' },
                { 'O', 'O', ' ', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('X');
        assertThat(boardState.getWinningMoves()).contains( new Move(1,4) , new Move(2,3) , new Move(3,2) );
    }

    @Test
    public void shouldFindVictoryByDiagonalRightToLeft2(){
        char[][] boardArr = new char[][]{
                { 'X', 'X', ' ', 'X' },
                { ' ', ' ', ' ', 'O' },
                { ' ', 'X', 'O', ' ' },
                { 'O', 'O', ' ', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('O');
        assertThat(boardState.getWinningMoves()).contains( new Move(2,4) , new Move(3,3) , new Move(4,2) );
    }

    @Test
    public void shouldFindVictoryByDiagonalRightToLeft3(){
        char[][] boardArr = new char[][]{
                { 'X', 'X', ' ', 'X', ' ' },
                { ' ', 'C', ' ', 'O', ' ' },
                { 'C', 'X', 'C', ' ', 'O' },
                { 'O', 'O', 'C', 'O', ' ' },
                { 'O', 'X', 'O', 'O', ' ' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('O');
        assertThat(boardState.getWinningMoves()).contains( new Move(3,5) , new Move(4,4) , new Move(5,3) );
    }

    @Test
    public void shouldFindVictoryByDiagonalRightToLeft4(){
        char[][] boardArr = new char[][]{
                { 'X', 'X', ' ', 'X', ' ' },
                { 'X', 'O', 'C', 'O', ' ' },
                { 'O', 'C', 'X', ' ', 'O' },
                { 'C', 'O', 'C', 'C', ' ' },
                { 'O', 'X', 'X', 'O', ' ' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('C');
        assertThat(boardState.getWinningMoves()).contains( new Move(2,3) , new Move(3,2) , new Move(4,1) );
    }


    @Test
    public void shouldNotFindTheWinnerIfMorePointsRequired(){
        checker = new BoardStateCheckerImpl(4);
        char[][] boardArr = new char[][]{
                { 'X', ' ', 'X', 'X' },
                { ' ', 'O', 'O', 'O' },
                { ' ', ' ', ' ', ' ' },
                { 'X', 'O', ' ', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState).isNull();
    }

    @Test
    public void shouldFindTheWinnerIfTheExpectedPointsIsFound(){
        checker = new BoardStateCheckerImpl(4);
        char[][] boardArr = new char[][]{
                { 'X', ' ', 'X', 'X' },
                { 'O', 'O', 'O', 'O' },
                { ' ', ' ', ' ', ' ' },
                { 'X', 'O', ' ', 'O' }
        };

        BoardState boardState = checker.checkBoard(boardArr);

        assertThat(boardState.isWinnerFound()).isTrue();
        assertThat(boardState.getPlayerChar()).isEqualTo('O');
        assertThat(boardState.getWinningMoves()).contains(
                new Move(2,1) , new Move(2,2) , new Move(2,3), new Move(2,4) );
    }



}
