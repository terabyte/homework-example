# Tic Tac Toe homework example

This is an example of how I think CS homework should work.

The professor provides you the "tictactoe" branch.  This branch provides you unit tests, an empty skeleton in which you can write your implementation, and some guidance on how it should work.  You know you are done when all the tests pass!  Test-Driven Development!

An example solution is provided in the tictactoe-solution branch, though a student's solution can and will look very different.

# Building and testing the program

This program uses gradle wrapper, so it is easy!

```
$ ./gradlew clean test assemble
```

# Running the program

The `gradle run` command doesn't work with STDIN, so the best way to run the project on the command line is to do the following:

```
$ ./gradlew assemble
$ java -classpath build/libs/homework.jar org.cmyers.homework.tictactoe.App
```

# Example output
```
Tic Tac Toe game
Board state:
     0   1   2
   =============
 0 |   |   |   |
   -------------
 1 |   |   |   |
   -------------
 2 |   |   |   |
   =============
   Player 'X' turn
   =============

Player 'X' move (e.g. 'row col' like '1 1'):
1 1
Board state:
     0   1   2
   =============
 0 |   |   |   |
   -------------
 1 |   | X |   |
   -------------
 2 |   |   |   |
   =============
   Player 'O' turn
   =============

Player 'O' move (e.g. 'row col' like '1 1'):
0 2
Board state:
     0   1   2
   =============
 0 |   |   | O |
   -------------
 1 |   | X |   |
   -------------
 2 |   |   |   |
   =============
   Player 'X' turn
   =============

Player 'X' move (e.g. 'row col' like '1 1'):
0 0
Board state:
     0   1   2
   =============
 0 | X |   | O |
   -------------
 1 |   | X |   |
   -------------
 2 |   |   |   |
   =============
   Player 'O' turn
   =============

Player 'O' move (e.g. 'row col' like '1 1'):
1 2
Board state:
     0   1   2
   =============
 0 | X |   | O |
   -------------
 1 |   | X | O |
   -------------
 2 |   |   |   |
   =============
   Player 'X' turn
   =============

Player 'X' move (e.g. 'row col' like '1 1'):
2 2
Game over!
The winner was 'X'!
```
