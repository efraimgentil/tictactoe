package me.efraimgentil.tictactoe;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnIllegalArgumentIfConfigFileDoesNotExists() throws IOException {
        Application.getPropertiesFromFile("randoFileNameThatDoesNotExists.properties");
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnIllegalArgumentIfConfigFileIsNotAPropertiesFile() throws IOException {
        URL resource = Application.class.getResource("/config.txt");

        Application.getPropertiesFromFile(resource.getPath());
    }

    @Test
    public void shouldReturnPropertiesFromTheFile() throws IOException {
        URL resource = Application.class.getResource("/config.properties");

        Properties propertiesFromFile = Application.getPropertiesFromFile(resource.getPath());

        assertThat(propertiesFromFile.getProperty("game.boardSize")).isEqualTo("5");
        assertThat(propertiesFromFile.getProperty("game.playerOneChar")).isEqualTo("A");
        assertThat(propertiesFromFile.getProperty("game.playerTwoChar")).isEqualTo("B");
        assertThat(propertiesFromFile.getProperty("game.playerIAChar")).isEqualTo("C");
    }


}
