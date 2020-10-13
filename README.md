# Kalah Game implementation

### Wikipedia Description
> The game provides a Kalah board and a number of seeds or counters. The board has 6 small pits, called houses, on each side; and a big pit, called an end zone, at each end. The object of the game is to capture more seeds than one's opponent.
  At the beginning of the game, four seeds are placed in each house. This is the traditional method.
  Each player controls the six houses and their seeds on the player's side of the board. The player's score is the number of seeds in the store to their right.
  Players take turns sowing their seeds. On a turn, the player removes all seeds from one of the houses under their control. Moving counter-clockwise, the player drops one seed in each house in turn, including the player's own store but not their opponent's.
  If the last sown seed lands in an empty house owned by the player, and the opposite house contains seeds, both the last seed and the opposite seeds are captured and placed into the player's store.
  If the last sown seed lands in the player's store, the player gets an additional move. There is no limit on the number of moves a player can make in their turn.
  When one player no longer has any seeds in any of their houses, the game ends. The other player moves all remaining seeds to their store, and the player with the most seeds in their store wins.

### Development / Run
Project was build using `Java 11`, AdoptOpenJDK version.
In order to build and run this project you need this version of Java setup.

Project is using Maven and there is included Maven Wrapper so just use `./mvnw` for Unix based systems and `mvnw.cmd` for Win32.

To build it just run:
```
./mvnw clean package
```

### Game Description
Pits from 1-6 are houses of North player and pit number 7 is store.

Pits from 8-13 are houses of South player and pit number 14 is store.

You can only move stones from houses.

### API
To access Swagger Docoumentation visit:
```
http://localhost:8080/swagger-ui/
```
> =============

To start a new game, use:
```
curl -X POST http://localhost:8080/games/
```
To make a move, use:
```
curl -X POST http://localhost:8080/games/{gameId}/{pitId}
```
When finished game will sum up a score and send response with sum for each player, for ex:
```
{"id":2,"url":"http://localhost:8080/games/2","status":{"1":0,"2":0,"3":0,"4":0,"5":0,"6":0,"7":59,"8":0,"9":0,"10":0,"11":0,"12":0,"13":0,"14":13}}% 
```
After that the game is marked as finished and no further moves will be allowed.
