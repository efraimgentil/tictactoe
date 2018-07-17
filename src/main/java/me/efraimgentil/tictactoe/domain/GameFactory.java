package me.efraimgentil.tictactoe.domain;

import me.efraimgentil.tictactoe.exception.InvalidConfigException;
import me.efraimgentil.tictactoe.input.ConsoleInputHandler;
import me.efraimgentil.tictactoe.input.IAInputReceiver;

import java.util.*;

public class GameFactory {

    public static final String BOARD_SIZE = "game.boardSize";
    public static final String PLAYER_ONE_CHAR = "game.playerOneChar";
    public static final String PLAYER_TWO_CHAR = "game.playerTwoChar";
    public static final String PLAYER_IA_CHAR = "game.playerIAChar";

    private static final String MISSING_PROPERTY = "Missing property '%s'";
    private static final String MIN_BOARD_ERROR = "Board size can not be less than 3";
    private static final String MAX_BOARD_ERROR = "Board size can not be greater than 10";
    private static final String PLAYER_CONFLICT_ERROR = "Player Two can not have the same character of Player One";
    private static final String IA_CONFLICT_ERROR = "Player IA can not have the same character of Player One or Player Two";
    private static final String PLAYER_CHAR_ERROR = "The player value can not be bigger than 1 character";

    public Game buildGame(Properties properties) throws InvalidConfigException {
        Integer boardSize = getBoardSize(properties);
        Queue<Player> playersOrder = definePlayerOrder(getPlayerList(properties));
        return new Game(new BoardImpl(boardSize), playersOrder);
    }

    /**
     * Retrieve board size configuration
     * Throws error if boardSize is missing, less than 3 or bigger than 10
     */
    protected Integer getBoardSize(Properties properties) throws InvalidConfigException {
        String stringVal = properties.getProperty(BOARD_SIZE);
        if (stringVal == null) {
            throw new InvalidConfigException(String.format(MISSING_PROPERTY, BOARD_SIZE));
        }
        Integer boardSize = Integer.valueOf(stringVal);
        if (boardSize < 3) {
            throw new InvalidConfigException(MIN_BOARD_ERROR);
        }
        if (boardSize > 10) {
            throw new InvalidConfigException(MAX_BOARD_ERROR);
        }
        return boardSize;
    }

    /**
     * Create list of players and IA (if configured)
     * Use LinkedList to take advantage of the 'Queue' to define player order later
     */
    protected LinkedList<Player> getPlayerList(Properties properties) throws InvalidConfigException {
        LinkedList<Player> players = new LinkedList<>();
        Character playerChar1 = getPlayerChar(properties, PLAYER_ONE_CHAR, true);
        Character playerChar2 = getPlayerChar(properties, PLAYER_TWO_CHAR, true);

        if (playerChar1 == playerChar2) {
            throw new InvalidConfigException(PLAYER_CONFLICT_ERROR);
        }
        players.add(new Player(playerChar1, false, new ConsoleInputHandler()));
        players.add(new Player(playerChar2, false, new ConsoleInputHandler()));

        Character playerIA = getPlayerChar(properties, PLAYER_IA_CHAR, true);
        if (playerIA != null) {
            if (playerChar1 == playerIA || playerChar2 == playerIA) {
                throw new InvalidConfigException(IA_CONFLICT_ERROR);
            }
            players.add(new Player(playerIA, true, new IAInputReceiver(3)));
        }
        return players;
    }

    /**
     * Retrieve the player character.
     * Throw error if value length bigger than 1, or marked to fail on missing
     */
    protected Character getPlayerChar(Properties properties, String key, boolean failMissing) throws InvalidConfigException {
        String stringVal = properties.getProperty(key);
        if (stringVal == null && failMissing) {
            throw new InvalidConfigException(String.format(MISSING_PROPERTY, key));
        }
        if (stringVal != null && stringVal.length() > 1) {
            throw new InvalidConfigException(PLAYER_CHAR_ERROR);
        }
        return stringVal != null ? stringVal.charAt(0) : null;
    }

    /**
     * Randomize the players order
     */
    protected Queue<Player> definePlayerOrder(List<Player> players) {
        Collections.shuffle(players, new Random());
        return (LinkedList) players;
    }

}

