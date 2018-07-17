package me.efraimgentil.tictactoe.tracer;

import me.efraimgentil.tictactoe.domain.Board;
import me.efraimgentil.tictactoe.domain.BoardImpl;

import java.nio.CharBuffer;
import java.util.Collections;
import java.util.stream.Collectors;

public class ConsoleMatrixTracer implements BoardTracer {

    @Override
    public void draw(Board board) {
        char[][] boardArr = ((BoardImpl) board).getBoard();
        for (int r = 0; r < boardArr.length; r++) {
            System.out.println(
                    CharBuffer.wrap(boardArr[r]).chars()
                            .mapToObj(c -> " " + String.valueOf((char) c)).collect(Collectors.joining(" |"))
            );
            if (r + 1 < boardArr.length) {
                System.out.println(String.join("", Collections.nCopies(boardArr.length, "----")));
            }
        }
    }

}
