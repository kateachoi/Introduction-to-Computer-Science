/**********************************************************************
 * @file TicTacToe.java
 * @brief Tic-tac-toe game for two players to alternate, first player is always O.
 * Yet to be improved to stop when a player wins, game automaticallly
 * stops after 9 clicks. Yet to account for accidental clicks.
 * @author Kate Choi
 * @date 10/10/2023
 ***********************************************************************/

import java.awt.*;

public class TicTacToe {
    final static int BOX_SIZE = 100;
    final static int BOARD_SIZE = 3 * (BOX_SIZE);
    final static int WINDOW_WIDTH = BOARD_SIZE;
    final static int WINDOW_HEIGHT = BOARD_SIZE + 50;
    private static int moves;
    private static boolean playerXTurn;
    private static String currentPlayer;

    public static void main(String[] args) {
        // Initialize variables
        moves = 0;
        playerXTurn = false;
        currentPlayer = "Player 1";

        DrawingPanel panel = new DrawingPanel(WINDOW_WIDTH, WINDOW_HEIGHT);
        Graphics g = panel.getGraphics();

        // Display whose turn it is
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString(currentPlayer + ": your turn", 20, 30);

        // Pass Graphics object to drawBoard() method
        drawBoard(g);
        // If the mouse is clicked, call handleClick() function
        panel.onMouseClick(
                (x, y) -> {
                    handleClick(g, x, y);
                });
    }

    // Receives (x, y) position where mouse was clicked
    private static void handleClick(Graphics g, int x, int y) {
        int row, col;
        int BUFFER = 3;

        // Keeps updating until moves >= 9.
        if (moves < 9) {
            // Allow user to click anywhere inside the box, and the X or O will be centered in the box.
            row = (y - (WINDOW_HEIGHT - BOARD_SIZE)) / BOX_SIZE * BOX_SIZE + (WINDOW_HEIGHT - BOARD_SIZE);
            col = (x - BUFFER) / BOX_SIZE * BOX_SIZE;
            // Update player's turns
            if (playerXTurn) {
                drawX(g, col, row);
                currentPlayer = "Player 1";
            } else {
                drawO(g, col, row);
                currentPlayer = "Player 2";
            }

            moves++; // accumulator for number of moves
            playerXTurn = !playerXTurn; // switch player's turns

            // Update text to indicate the current player's turn
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WINDOW_WIDTH, 50);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g.drawString(currentPlayer + ": your turn", 20, 30);}
    }

    // Method to draw X in the box, no matter where in the box the user clicks.
    private static void drawX(Graphics g, int col, int row) {
        g.setColor(Color.BLUE);
        g.drawLine(col, row, col + BOX_SIZE, row + BOX_SIZE);
        g.drawLine(col, row + BOX_SIZE, col + BOX_SIZE, row);
    }

    // Method to draw O in the box, no matter where in the box the user clicks.
    private static void drawO(Graphics g, int col, int row) {
        g.setColor(Color.RED);
        g.drawOval(col, row, BOX_SIZE, BOX_SIZE);
    }

    // Method to draw the tic-tac-toe board
    private static void drawBoard(Graphics g) {
        int i, j;
        // Draw the Tic Tac Toe board
        g.setColor(Color.BLACK);
        g.drawRect(0, WINDOW_HEIGHT - BOARD_SIZE, BOARD_SIZE, BOARD_SIZE);
        for (i = 1; i < 3; i++) {
            g.drawLine(i * BOX_SIZE, WINDOW_HEIGHT - BOARD_SIZE, i * BOX_SIZE, WINDOW_HEIGHT);
            g.drawLine(0, WINDOW_HEIGHT - BOARD_SIZE + i * BOX_SIZE, BOARD_SIZE, WINDOW_HEIGHT - BOARD_SIZE + i * BOX_SIZE);
        }
    }
}
