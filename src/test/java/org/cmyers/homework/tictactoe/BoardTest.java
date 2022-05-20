package org.cmyers.homework.tictactoe;

import org.cmyers.homework.tictactoe.exceptions.IllegalMoveException;
import org.cmyers.homework.tictactoe.exceptions.WrongPlayerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class tests the Board class
 */
public class BoardTest {

    /**
     * This test confirms that the board constructor correctly initializes the board state.
     *
     * It also relies upon the getBoardPosition() and isXTurn() accessors to test the board state.
     */
    @Test
    public void testBoardConstructor() {
        Board b = new Board();
        Assertions.assertTrue(b.isXTurn(), "It should be player X's turn");
        Assertions.assertEquals(b.getBoardPosition(0, 0), ' ', "Board position 0,0 should be empty");
        Assertions.assertEquals(b.getBoardPosition(0, 1), ' ', "Board position 0,1 should be empty");
        Assertions.assertEquals(b.getBoardPosition(0, 2), ' ', "Board position 0,2 should be empty");
        Assertions.assertEquals(b.getBoardPosition(1, 0), ' ', "Board position 1,0 should be empty");
        Assertions.assertEquals(b.getBoardPosition(1, 1), ' ', "Board position 1,1 should be empty");
        Assertions.assertEquals(b.getBoardPosition(1, 2), ' ', "Board position 1,2 should be empty");
        Assertions.assertEquals(b.getBoardPosition(2, 0), ' ', "Board position 2,0 should be empty");
        Assertions.assertEquals(b.getBoardPosition(2, 1), ' ', "Board position 2,1 should be empty");
        Assertions.assertEquals(b.getBoardPosition(2, 2), ' ', "Board position 2,2 should be empty");
    }

    /**
     * This method tests the getBoardPosition method.  It also requires the makeMove() method to make moves.
     */
    @Test
    public void testGetBoardPosition() {
        Board b = new Board();
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> b.getBoardPosition(5, 0), "Should throw an exception if given row or coordinate out of bounds");
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> b.getBoardPosition(0, 5), "Should throw an exception if given row or coordinate out of bounds");
        Assertions.assertEquals(b.getBoardPosition(0, 0), ' ', "Board position 0,0 should be empty initially");
        b.makeMove('X', 0, 0);
        Assertions.assertEquals(b.getBoardPosition(0, 0), 'X', "Board position 0,0 should contain an X after player moves there");
        b.makeMove('O', 1, 1);
        Assertions.assertEquals(b.getBoardPosition(1, 1), 'O', "Board position 1,1 should contain an O after player moves there");
    }

    /**
     * This method tests the isXTurn() method and that the player's turn variable flips back and forth after each move.
     */
    @Test
    public void testIsXTurn() {
        Board b = new Board();
        Assertions.assertTrue(b.isXTurn(), "It should initially be player X's turn");
        b.makeMove('X', 0, 0);
        Assertions.assertFalse(b.isXTurn(), "It should be player O's turn after making the first move");
        b.makeMove('O', 1, 1);
        Assertions.assertTrue(b.isXTurn(), "It should be player X's turn again for the 3rd move");
    }

    /**
     * This method tests the makeMove function, including all error cases.
     */
    @Test
    public void testMakeMove() {
        Board b = new Board();
        Assertions.assertTrue(b.isXTurn(), "It should be player X's turn for the first move");
        Assertions.assertEquals(b.getBoardPosition(0, 0), ' ', "Board position 0,0 should be empty");
        Assertions.assertThrows(WrongPlayerException.class, () -> b.makeMove('O', 0, 0), "Should throw if player O tries to go first");

        b.makeMove('X', 0, 0);
        Assertions.assertFalse(b.isXTurn(), "It should be player O's turn for the second move");
        Assertions.assertEquals(b.getBoardPosition(0, 0), 'X', "After a move, position 0,0 should be X");
        Assertions.assertThrows(WrongPlayerException.class, () -> b.makeMove('X', 1, 1), "Should throw if player X tries to go twice in a row");
        Assertions.assertThrows(IllegalMoveException.class, () -> b.makeMove('O', 5, 0), "Should throw if player tries to make an invalid move");
        Assertions.assertThrows(IllegalMoveException.class, () -> b.makeMove('O', 0, 5), "Should throw if player tries to make an invalid move");
        Assertions.assertEquals(b.getBoardPosition(1, 1), ' ', "After an invalid move, position 1,1 should still be empty");

        b.makeMove('O', 1, 1);
        Assertions.assertTrue(b.isXTurn(), "It should be player X's turn for the third move");
        Assertions.assertEquals(b.getBoardPosition(1, 1), 'O', "After a move, position 1,1 should be O");
    }

    /**
     * This function tests isGameOver() and getWinningPlayer() methods
     */
    @Test
    public void testGameStateFunctions() {
        Board xWinsVertically = new Board(
                new char [] [] {
                    { 'X', ' ', ' ' },
                    { 'X', 'O', ' ' },
                    { 'X', 'O', ' ' },
                }, false);
        Assertions.assertTrue(xWinsVertically.isGameOver(), "Game should be over when a player has won (vertically)");
        Assertions.assertEquals(xWinsVertically.getWinningPlayer(), 'X', "Winning player should be X (vertically)");

        Board oWinsVertically = new Board(
                new char [] [] {
                        { ' ', 'O', 'X' },
                        { 'X', 'O', ' ' },
                        { 'X', 'O', ' ' },
                }, true);
        Assertions.assertTrue(oWinsVertically.isGameOver(), "Game should be over when a player has won (vertically)");
        Assertions.assertEquals(oWinsVertically.getWinningPlayer(), 'O', "Winning player should be O (vertically)");

        Board xWinsHorizontally = new Board(
                new char [] [] {
                        { 'X', 'X', 'X' },
                        { ' ', 'O', ' ' },
                        { ' ', 'O', ' ' },
                }, false);
        Assertions.assertTrue(xWinsHorizontally.isGameOver(), "Game should be over when a player has won (horizontally)");
        Assertions.assertEquals(xWinsHorizontally.getWinningPlayer(), 'X', "Winning player should be X (horizontally)");

        Board oWinsHorizontally = new Board(
                new char [] [] {
                        { 'X', ' ', 'X' },
                        { 'O', 'O', 'O' },
                        { 'X', ' ', ' ' },
                }, true);
        Assertions.assertTrue(oWinsHorizontally.isGameOver(), "Game should be over when a player has won (horizontally)");
        Assertions.assertEquals(oWinsHorizontally.getWinningPlayer(), 'O', "Winning player should be O (horizontally)");

        Board xWinsDiagonally = new Board(
                new char [] [] {
                        { ' ', ' ', 'X' },
                        { 'O', 'X', ' ' },
                        { 'X', 'O', ' ' },
                }, false);
        Assertions.assertTrue(xWinsDiagonally.isGameOver(), "Game should be over when a player has won (diagonally)");
        Assertions.assertEquals(xWinsDiagonally.getWinningPlayer(), 'X', "Winning player should be X (diagonally)");

        Board oWinsDiagonally = new Board(
                new char [] [] {
                        { 'O', 'X', 'X' },
                        { ' ', 'O', ' ' },
                        { 'X', ' ', 'O' },
                }, true);
        Assertions.assertTrue(oWinsDiagonally.isGameOver(), "Game should be over when a player has won (diagonally)");
        Assertions.assertEquals(oWinsDiagonally.getWinningPlayer(), 'O', "Winning player should be O (diagonally)");

        Board catsGame = new Board(
                new char [] [] {
                        { 'O', 'X', 'X' },
                        { 'X', 'O', 'O' },
                        { 'X', 'O', 'X' },
                }, false);
        Assertions.assertTrue(catsGame.isGameOver(), "Game should be over when all spaces are taken up");
        Assertions.assertEquals(catsGame.getWinningPlayer(), ' ', "Winning player should be ' ' (diagonally)");
    }
}
