package me.efraimgentil.tictactoe;

import me.efraimgentil.tictactoe.domain.Game;
import me.efraimgentil.tictactoe.domain.GameBuilder;
import me.efraimgentil.tictactoe.exception.InvalidConfigException;
import me.efraimgentil.tictactoe.exception.InvalidMoveException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Application {

    public static void main(String ... args) throws IOException, InvalidConfigException, InvalidMoveException {
        URL resource = Application.class.getResource("/config.properties");
        String path = resource.getPath();
        Game game = new GameBuilder().buildGame(getPropertiesFromFile(path));
        game.startGame();
    }

    public static Properties getPropertiesFromFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            throw new IllegalArgumentException("Configuration file does not exists");
        }else{
            String substring = path.substring(path.lastIndexOf("."));
            if(!substring.contains(".properties")){
                throw new IllegalArgumentException("Configuration file is not a .properties file");
            }
            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            return properties;
        }
    }

}
