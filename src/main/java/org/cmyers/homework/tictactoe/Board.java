package org.cmyers.homework.tictactoe;

import java.util.ArrayList;
import org.cmyers.homework.tictactoe.exceptions.IllegalMoveException;
import org.cmyers.homework.tictactoe.exceptions.WrongPlayerException;

/**
 * This class represents a tic tac toe board.
 */
public class Board {
    char [] [] board; // possible values are 'X', 'O', or ' ' for empty
    boolean isXTurn; // true for when it is the X player's turn, false for the O player's turn

    /**
     * constructor for the board, initializes an empty board, sets isXTurn to true.
     */
    public Board() {
        // TODO: write your code here
    }

    /**
     * This method exists for testing purposes, and thus is protected.  Tests need to construct a board in a particular state.
     *
     * @param board
     * @param isXTurn
     */
    protected Board(char[] [] board, boolean isXTurn) {
        this.board = board;
        this.isXTurn = isXTurn;
    }

    /**
     * This method returns the contents of the given board space.
     *
     * @param row the row (i.e. 0=top, 1=middle, 2=bottom)
     * @param col the column (i.e. 0=left, 1=middle, 2=right)
     * @return 'X', 'O', or ' ' for empty
     */
    public char getBoardPosition(int row, int col) throws ArrayIndexOutOfBoundsException {
        // TODO: write your code here
        return '\0';
    }

    public boolean isXTurn() {
        return isXTurn;
    }

    /**
     * Returns true if current player can't move (because game is won, or tied).
     */
    public boolean isGameOver() {
        // TODO: write your code here
        return false;
    }

    /**
     * Returns the winning player, 'X', 'O', or ' ' if game is tied or unfinished.
     */
    public char getWinningPlayer() {
        // TODO: write your code here
        return '\0';
    }

    /**
     * This method makes a move.  If the move is not valid, it throws an exception.
     *
     * Illegal moves include moving over a space that is already occupied, moving
     * outside the board, or moving when it isn't your turn.
     *
     * @throws IllegalMoveException if the move is not valid
     * @throws WrongPlayerException if it is not the player's turn
     */
    public void makeMove(char player, int row, int col) throws IllegalMoveException, WrongPlayerException, ArrayIndexOutOfBoundsException {
        // TODO: write your code here
    }

    /**
     * Get the the board and game state as a string suitable for printing
     *
     * @return
     */
    @Override
    public String toString() {
        return this.toString(false);
    }

    /**
     * Get the the board and game state as a string suitable for printing
     *
     * @param pretty if true, print using newlines and a pretty board layout, otherwise print as a single line.
     * @return
     */
    public String toString(boolean pretty) {
        StringBuilder sb = new StringBuilder();
        if (pretty) {
            sb.append("     0   1   2  \n");
            sb.append("   =============\n");
            sb.append(" 0 | " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |\n");
            sb.append("   -------------\n");
            sb.append(" 1 | " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |\n");
            sb.append("   -------------\n");
            sb.append(" 2 | " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |\n");
            sb.append("   =============\n");
            sb.append("   Player '" + (isXTurn ? "X" : "O") + "' turn\n");
            sb.append("   =============\n");
        } else {
            sb.append("[board: {");

            var rows = new ArrayList<String>();
            for (int i = 0; i < 3; ++i) {
                Arrays.stream(board).forEach(x -> rows.add(new String(x).replace(' ', '-')));
            }
            sb.append(String.join("|", rows));
            sb.append("} turn: '" + (isXTurn ? "X" : "O") + "']");
        }
        return sb.toString();
    }

}
