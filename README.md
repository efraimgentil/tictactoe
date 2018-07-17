# Tic Tac Toe


### How to Build and Run

To build, run the command on the root folder of the project
```cmd
$ ./mvnw clean package
```

To run the game  on the root folder of the project
```cmd
$ java -jar target/tic-tac-toe.jar {configFilePath}
```
Where `{configFilePath}` should be replaced for a existing path, to a configuration file
e.g `/home/username/configtictactoe.properties`

### Configuration 

To run the game you need to provide a configuration file, with the following properties

> Note that the extension needs to be a .properties

```properties
#VALID VALUES BETWEEN 3 and 10
game.boardSize=10
game.playerOneChar=A
game.playerTwoChar=B
game.playerIAChar=C
```

### Design decisions and Assumptions

- Went with a approach using a bi dimensional array, as it was easier to verify the board state, and test
- Used maven to handle building the project into a runnable jar file
- I chose to use a Game class to maintain the game state and dictate the player order
- I chose to have a richer Board class implementation, so the board would know his actual state, the moves available,
  and how to draw himself, with that is easier to maintain the Board and also changes his implementation if needed (exposed through an interface)
- I chose to use a Factory Pattern to handle the complexity to create a Game + Board
- I chose to use a Strategy Pattern to handle how each type of player (IA and Human) would receive the input
- I chose to use a modification of the MiniMax algorithm to handle the IA input (even though is not the most optimal, it can be slow in the 10x board)

### Stack

- Java 8 
- Maven
- JUnit / Mockito / AssertJ (For testing)