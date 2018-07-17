package me.efraimgentil.tictactoe.domain;

import me.efraimgentil.tictactoe.exception.InvalidConfigException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class GameFactoryTest {

    GameFactory builder;

    @Before
    public void setUp(){
        builder = new GameFactory();
    }

    @Test(expected = InvalidConfigException.class)
    public void shouldThrowErrorIfBoardSizeLessThanThree() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty(GameFactory.BOARD_SIZE , "2");

        builder.getBoardSize(properties);
    }

    @Test(expected = InvalidConfigException.class)
    public void shouldThrowErrorIfBoardSizeIsBiggerThanTen() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty(GameFactory.BOARD_SIZE , "11");

        builder.getBoardSize(properties);
    }

    @Test(expected = InvalidConfigException.class)
    public void shouldThrowErrorIfBoardSizePropertyIsMissing() throws InvalidConfigException {
        Properties properties = new Properties();

        builder.getBoardSize(properties);
    }

    @Test
    public void shouldReturnPlayerCharacterOne() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty("playerCharKeyOne" , "A");

        Character playerCharKey = builder.getPlayerChar(properties, "playerCharKeyOne", true);

        assertThat(playerCharKey).isEqualTo('A');
    }

    @Test(expected = InvalidConfigException.class)
    public void shouldFailtIfPlayerCharacterIsNotPresent() throws InvalidConfigException {
        Properties properties = new Properties();

        builder.getPlayerChar(properties, "playerCharKeyOne", true);
    }

    @Test
    public void shouldNotFailIfPlayerCharacterIsNotPresentAndFailtIsMissingIsFalse() throws InvalidConfigException {
        Properties properties = new Properties();

        Character playerCharKeyIA = builder.getPlayerChar(properties, "playerCharKeyIA", false);

        assertThat(playerCharKeyIA).isNull();
    }

    @Test
    public void shouldReturnThePlayerListWithTwoPlayers() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty( GameFactory.PLAYER_ONE_CHAR , "A");
        properties.setProperty( GameFactory.PLAYER_TWO_CHAR , "B");
        properties.setProperty( GameFactory.PLAYER_IA_CHAR , "C");

        List<Player> playerList = builder.getPlayerList(properties);

        assertThat(playerList).hasSize(3);
        assertThat(playerList.get(0).getPlayerChar()).isEqualTo('A');
        assertThat(playerList.get(0).isIA()).isFalse();
        assertThat(playerList.get(1).getPlayerChar()).isEqualTo('B');
        assertThat(playerList.get(1).isIA()).isFalse();
        assertThat(playerList.get(2).getPlayerChar()).isEqualTo('C');
        assertThat(playerList.get(2).isIA()).isTrue();
    }

    @Test
    public void shouldReturnThePlayerListWithTwoPlayersAndTheIAPlayer() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty( GameFactory.PLAYER_ONE_CHAR , "A");
        properties.setProperty( GameFactory.PLAYER_TWO_CHAR , "B");
        properties.setProperty( GameFactory.PLAYER_IA_CHAR , "C");

        List<Player> playerList = builder.getPlayerList(properties);

        assertThat(playerList).hasSize(3);
        assertThat(playerList.get(0).getPlayerChar()).isEqualTo('A');
        assertThat(playerList.get(0).isIA()).isFalse();
        assertThat(playerList.get(1).getPlayerChar()).isEqualTo('B');
        assertThat(playerList.get(1).isIA()).isFalse();
        assertThat(playerList.get(2).getPlayerChar()).isEqualTo('C');
        assertThat(playerList.get(2).isIA()).isTrue();
    }

    @Test(expected = InvalidConfigException.class)
    public void shouldThrowErrorIfPlayerOneAndPlayerTwoHaveTheSameCharacter() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty( GameFactory.PLAYER_ONE_CHAR , "A");
        properties.setProperty( GameFactory.PLAYER_TWO_CHAR , "A");

        builder.getPlayerList(properties);
    }

    @Test(expected = InvalidConfigException.class)
    public void shouldThrowErrorIfPlayerIAAndPlayerTwoHaveTheSameCharacter() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty( GameFactory.PLAYER_ONE_CHAR , "A");
        properties.setProperty( GameFactory.PLAYER_TWO_CHAR , "B");
        properties.setProperty( GameFactory.PLAYER_IA_CHAR , "B");

        builder.getPlayerList(properties);
    }

    @Test(expected = InvalidConfigException.class)
    public void shouldThrowErrorIfPlayerIAAndPlayerOneHaveTheSameCharacter() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty( GameFactory.PLAYER_ONE_CHAR , "A");
        properties.setProperty( GameFactory.PLAYER_TWO_CHAR , "B");
        properties.setProperty( GameFactory.PLAYER_IA_CHAR , "A");

        builder.getPlayerList(properties);
    }

    @Test
    public void shouldSuccessFullyCreateAGame() throws InvalidConfigException {
        Properties properties = new Properties();
        properties.setProperty(GameFactory.BOARD_SIZE , "3");
        properties.setProperty( GameFactory.PLAYER_ONE_CHAR , "A");
        properties.setProperty( GameFactory.PLAYER_TWO_CHAR , "B");
        properties.setProperty( GameFactory.PLAYER_IA_CHAR , "C");

        Game game = builder.buildGame(properties);

        assertThat(game).isNotNull();
    }




}
