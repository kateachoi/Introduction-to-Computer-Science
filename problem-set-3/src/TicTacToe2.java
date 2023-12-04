/**********************************************************************
 * @file TicTacToe2.java
 * @brief Tic-tac-toe game for two players to alternate, first player is always X. Checks for wins using a 2D int
 * array where 0 indicates an empty cell, 1 indicates a cell filled by X, and 2 a cell filled by O, and prevents a
 * player from clicking a filled cell. Automatically stops when someone wins, or the board is full (tie).
 * @author Kate Choi
 * @date 11/2/2023
 ***********************************************************************/

import java.awt.*;
import java.util.Arrays;

public class TicTacToe2 {
    final static int BOX_SIZE = 100;
    final static int BOARD_SIZE = 3 * (BOX_SIZE);
    final static int WINDOW_WIDTH = BOARD_SIZE;
    final static int WINDOW_HEIGHT = BOARD_SIZE + 50;
    private static int moves;
    private static String currentPlayer;
    private static boolean gameOver = false;
    private static int[][] board;
    private static int player1Moves;
    private static int player2Moves;

    public static void main(String[] args) {

        // Initialize variables
        moves = 0;
        currentPlayer = "Player 1";
        player1Moves = 0;
        player2Moves = 0;

        // Model board using a 3x3 2D int array
        board = new int[3][3];

        DrawingPanel panel = new DrawingPanel(WINDOW_WIDTH, WINDOW_HEIGHT);
        Graphics g = panel.getGraphics();

        // Display whose turn it is
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString(currentPlayer + ": your turn", 20, 30); // on board
        System.out.println(currentPlayer + ": your turn"); // in console

        // Pass graphics object to drawBoard() method
        drawBoard(g);
        // If the mouse is clicked, call the handleClick() function
        panel.onMouseClick((x, y) -> {
            handleClick(g, x, y);
        });
    }

    // Receives (x, y) position where the mouse is clicked
    private static void handleClick(Graphics g, int x, int y) {
        int row, col;

        // Keep updating as long as moves < 9 and game is not over
        if (moves < 9 && !gameOver) {
            // Allow the user to click anywhere inside the box, and the X or O will be centered in the box.
            row = (y - (WINDOW_HEIGHT - BOARD_SIZE)) / BOX_SIZE;
            col = x / BOX_SIZE;

            // Ensure empty box to validate player's click
            if (board[row][col] == 0) {
                if (currentPlayer.equals("Player 1")) {
                    drawX(g, col, row);
                    player1Moves++;
                    // Switch turns
                    currentPlayer = "Player 2";
                    // Mark the cell as X (1)
                    board[row][col] = 1;
                } else {
                    drawO(g, col, row);
                    player2Moves++;
                    // Switch turns
                    currentPlayer = "Player 1";
                    // Mark the cell as O (2)
                    board[row][col] = 2;
                }

                moves++; // Accumulator for the number of moves

                // Check for a win or a full board
                if (player1Moves >= 3 || player2Moves >= 3) {
                    if (checkWin()) {
                        gameOver = true;
                    } else if (moves == 9) {
                        gameOver = true;
                    }
                }

                g.setColor(Color.WHITE);
                g.fillRect(0, 0, WINDOW_WIDTH, 50);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.PLAIN, 15));
                // Check wins
                if (gameOver) {
                    if (checkWin()) {
                        // Switch player so the correct winner is displayed
                        if (currentPlayer.equals("Player 1")) {
                            currentPlayer = "Player 2";
                        } else if (currentPlayer.equals("Player 2")) {
                            currentPlayer = "Player 1";
                        }
                        g.drawString(currentPlayer + " wins!", 20, 30); // on board
                        System.out.println(currentPlayer + " wins!"); // in console
                    } else {
                        // If no one won but game is over, it's a draw
                        g.drawString("It's a draw!", 20, 30); // on board
                        System.out.println("It's a draw!"); // in console
                    }
                }
                // Update text to indicate the current player's turn if the game is not over
                if (!gameOver) {
                    g.drawString(currentPlayer + ": your turn", 20, 30);
                    System.out.println(currentPlayer + ": your turn");
                }
            }
        }
    }

    // Draw the tic-tac-toe board
    private static void drawBoard(Graphics g) {
        int i;
        // Draw the Tic Tac Toe board
        g.setColor(Color.BLACK);
        g.drawRect(0, WINDOW_HEIGHT - BOARD_SIZE, BOARD_SIZE, BOARD_SIZE);
        for (i = 1; i < 3; i++) {
            g.drawLine(i * BOX_SIZE, WINDOW_HEIGHT - BOARD_SIZE, i * BOX_SIZE, WINDOW_HEIGHT);
            g.drawLine(0, WINDOW_HEIGHT - BOARD_SIZE + i * BOX_SIZE, BOARD_SIZE, WINDOW_HEIGHT - BOARD_SIZE + i * BOX_SIZE);
        }
    }
    // Draw X in the box, no matter where in the box the user clicks.
    private static void drawX(Graphics g, int col, int row) {
        g.setColor(Color.BLUE);
        g.drawLine(col * BOX_SIZE, (WINDOW_HEIGHT - BOARD_SIZE) + row * BOX_SIZE, (col + 1) * BOX_SIZE, (WINDOW_HEIGHT - BOARD_SIZE) + (row + 1) * BOX_SIZE);
        g.drawLine(col * BOX_SIZE, (WINDOW_HEIGHT - BOARD_SIZE) + (row + 1) * BOX_SIZE, (col + 1) * BOX_SIZE, (WINDOW_HEIGHT - BOARD_SIZE) + row * BOX_SIZE);
    }

    // Draw O in the box, no matter where in the box the user clicks.
    private static void drawO(Graphics g, int col, int row) {
        g.setColor(Color.RED);
        g.drawOval(col * BOX_SIZE, (WINDOW_HEIGHT - BOARD_SIZE) + row * BOX_SIZE, BOX_SIZE, BOX_SIZE);
    }

    // Check if a player won
    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (board[i][0] == 1 && board[i][1] == 1 && board[i][2] == 1) {
                return true;
            }
            if (board[i][0] == 2 && board[i][1] == 2 && board[i][2] == 2) {
                return true;
            }
            // Check columns
            if (board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1) {
                return true;
            }
            if (board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) ||
                (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) ||
                (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1) ||
                (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2)) {
            return true;
        }
        return false;
    }
}
