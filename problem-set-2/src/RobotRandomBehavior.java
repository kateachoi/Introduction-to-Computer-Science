/**********************************************************************
 * @file RobotRandomBehavior.java
 * @brief Simulates random paths for three robots to take in a grid of size N inputted by the user and calculates
 * average steps taken per robot before exiting the grid, and average total steps.
 * @author Kate Choi
 * @date 10/10/2023
 ***********************************************************************/

import java.util.Scanner;
import java.util.Random;
import java.awt.*;

public class RobotRandomBehavior {
    public static int WINDOW_SIZE = 400;
    public static int N;
    public static int BOARD_SIZE;
    public static int BOARD_WIDTH;
    public static int BOARD_HEIGHT;
    public static double CELL_WIDTH;
    public static double CELL_HEIGHT;
    public static int totalSteps;


    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        System.out.print("Enter grid size (N): ");
        N = kb.nextInt();

        // Initialize board and cell sizes
        WINDOW_SIZE = WINDOW_SIZE + (4 * N);
        BOARD_SIZE = WINDOW_SIZE - 20;
        BOARD_WIDTH = BOARD_SIZE;
        BOARD_HEIGHT = BOARD_SIZE - 60;
        CELL_WIDTH = (double) BOARD_WIDTH / N;
        CELL_HEIGHT = (double) BOARD_HEIGHT / N;

        // Set drawing panel (window), draw board, draw grid
        DrawingPanel panel = new DrawingPanel(WINDOW_SIZE, WINDOW_SIZE);
        Graphics g = panel.getGraphics();
        drawBoard(g);
        drawGrid(g, N);

        // Heading
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_SIZE, 70);

        // Simulate robot 1
        double x1 = BOARD_WIDTH / 2;
        double y1 = BOARD_HEIGHT / 2;
        int robot1Steps = robotMoves(g, Color.BLUE, x1, y1);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("Robot 1: steps = " + robot1Steps, (WINDOW_SIZE / 2) - N, 17);

        // Simulate robot 2
        double x2 = BOARD_WIDTH / 2;
        double y2 = BOARD_HEIGHT / 2;
        int robot2Steps = robotMoves(g, Color.MAGENTA, x2, y2);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("Robot 2: steps = " + robot2Steps, (WINDOW_SIZE / 2) - N, 29);

        // Simulate robot 3
        double x3 = BOARD_WIDTH / 2;
        double y3 = BOARD_HEIGHT / 2;
        int robot3Steps = robotMoves(g, Color.CYAN, x3, y3);
        g.setColor(Color.CYAN);
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("Robot 3: steps = " + robot3Steps, (WINDOW_SIZE / 2) - N, 41);

        // Calculate and print average steps
        double avgSteps = ((double) (robot1Steps + robot2Steps + robot3Steps) / 3);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("average: " + String.format("%.2f", avgSteps), (WINDOW_SIZE / 2) - N, 57);
    }

    // Draw border for board
    public static void drawBoard(Graphics g) {
        g.drawRect(10, 70, BOARD_SIZE, BOARD_SIZE - 60);
    }

    // Draw grid based on user input N
    public static void drawGrid(Graphics g, int N) {
        for (int i = 1; i <= N; i++) {
            int x = (int) (10 + (i * CELL_WIDTH));
            int y = (int) (70 + (i * CELL_HEIGHT));

            g.drawLine(x, 70, x, 70 + BOARD_HEIGHT); // vertical lines
            g.drawLine(10, y, 10 + BOARD_WIDTH, y); // horizontal lines
        }
    }

    // Method to simulate robot path. Calling robotMoves() traces the (random) path of the robot on the grid
    // and returns the total integer steps taken by the robot before exiting the grid.
    public static int robotMoves(Graphics g, Color color, double x0, double y0) {
        Random rand = new Random();
        // Initialize
        double x = 0, y = 0;
        totalSteps = 0;

        // While loop for robot is in the grid
        while (isIn(x0, y0)) {

            // Set direction codes
            // 1: up, 2: down, 3: right, 4: left
            int move = rand.nextInt(4) + 1;

            if (move == 1) {
                x = x0;
                y = y0 - CELL_HEIGHT;
            } else if (move == 2) {
                x = x0;
                y = y0 + CELL_HEIGHT;
            } else if (move == 3) {
                x = x0 + CELL_WIDTH;
                y = y0;
            } else if (move == 4) {
                x = x0 - CELL_WIDTH;
                y = y0;
            }

            // Trace robot path
            g.setColor(color);
            g.drawLine((int) x0, (int) y0, (int) x, (int) y);

            // Check if robot exited
            if (!isIn(x, y)) {
                break;
            }

            // Store previous positions for next iteration
            x0 = x;
            y0 = y;

            totalSteps++; // accumulate number of steps
        }
        return totalSteps;
    }

    // Method to check whether the robot is still in the grid of size N after each move in the robotMoves() method.
    // Calling isIn() returns true if the robot is still in the grid, false if the robot exited the grid and
    // terminates the while loop in the robotMoves() method.
    public static boolean isIn(double x, double y) {
        if (x > BOARD_WIDTH + 10 || x < 10) {
            return false;
        }
        if (y > BOARD_HEIGHT + 70 || y < 70) {
            return false;
        }
        return true;
    }

}