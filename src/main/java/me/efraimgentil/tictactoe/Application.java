package me.efraimgentil.tictactoe;

import me.efraimgentil.tictactoe.domain.Game;
import me.efraimgentil.tictactoe.domain.GameFactory;
import me.efraimgentil.tictactoe.exception.InvalidConfigException;
import me.efraimgentil.tictactoe.exception.InvalidMoveException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Application {

    public static void main(String ... args) throws IOException, InvalidConfigException, InvalidMoveException {
        //URL resource = Application.class.getResource("/config.properties");
        if(args.length == 0)
            throw new IllegalArgumentException("Arg with configuration file path not found");
        String path = args[0];
        Game game = new GameFactory().buildGame(getPropertiesFromFile(path));
        game.startGame();
    }

    public static Properties getPropertiesFromFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            throw new IllegalArgumentException(String.format("Configuration file (%s) does not exists" , path));
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
