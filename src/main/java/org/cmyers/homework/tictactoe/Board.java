package org.cmyers.homework.tictactoe;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
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
        this.board = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
        };
        this.isXTurn = true;
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
        return board[row][col];
    }

    public boolean isXTurn() {
        return isXTurn;
    }

    /**
     * Returns true if current player can't move (because game is won, or tied).
     */
    public boolean isGameOver() {

        // char streams don't exist, they are really just streams of ints, le sigh.
        if (Arrays.stream(board).flatMapToInt(x -> CharBuffer.wrap(x).chars()).allMatch(x -> x != (int) ' ')) {
            // all spaces are taken
            return true;
        }
        // if not all spaces are taken, we need to detect a win.
        // first look for horizontal wins
        char winner = getWinningPlayer();
        if (winner == 'X' || winner == 'O') {
            return true;
        }

        // no winner and spaces left, game not over
        return false;
    }

    /**
     * Returns the winning player, 'X', 'O', or ' ' if game is tied or unfinished.
     */
    public char getWinningPlayer() {
        // we have to detect all the possible ways to win
        // after you make a winning move, it is the other player's turn, so a
        // valid win state can only happen for the player whose turn it is not.
        // This means we only have to test for one type of winner
        char winningPlayer;
        if (isXTurn) {
            winningPlayer = 'O';
        } else {
            winningPlayer = 'X';
        }
        // horizontal
        for (int i = 0; i < 3; ++i) {
            if (board[i][0] == winningPlayer
                    && board[i][1] == winningPlayer
                    && board[i][2] == winningPlayer) {
                return winningPlayer;
            }
        }
        // vertical
        for (int i = 0; i < 3; ++i) {
            if (board[0][i] == winningPlayer
                && board[1][i] == winningPlayer
                && board[2][i] == winningPlayer) {
                return winningPlayer;
            }
        }
        // diagonals
        if (board[0][0] == winningPlayer
            && board[1][1] == winningPlayer
            && board[2][2] == winningPlayer) {
            return winningPlayer;
        }
        if (board[0][2] == winningPlayer
                && board[1][1] == winningPlayer
                && board[2][0] == winningPlayer) {
            return winningPlayer;
        }

        return ' ';
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
        if (isXTurn) {
            if (player != 'X') {
                throw new WrongPlayerException();
            }
        } else {
            if (player != 'O') {
                throw new WrongPlayerException();
            }
        }
        if (board[row][col] != ' ') {
            throw new IllegalMoveException();
        }
        board[row][col] = player;
        isXTurn = !isXTurn;
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
