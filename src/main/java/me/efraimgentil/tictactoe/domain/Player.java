package me.efraimgentil.tictactoe.domain;

import me.efraimgentil.tictactoe.input.PlayerInputHandler;

public class Player {

    private char playerChar;

    private boolean IA;

    private PlayerInputHandler inputHandler;

    public Player(char playerChar, boolean IA, PlayerInputHandler inputHandler) {
        this.playerChar = playerChar;
        this.IA = IA;
        this.inputHandler = inputHandler;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public boolean isIA() {
        return IA;
    }

    public PlayerInputHandler getInputHandler() {
        return inputHandler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return playerChar == player.playerChar;
    }

    @Override
    public int hashCode() {
        return (int) playerChar;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerChar=" + playerChar +
                '}';
    }
}
