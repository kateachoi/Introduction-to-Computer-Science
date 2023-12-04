/**********************************************************************
 * @file RoombaSimulator.java
 * @brief Simulates a roomba cleaning a room, example images from file room_conf.txt for a 20 x 20 array where -1
 * represents obstacles that the roomba can't go over, 0 are uncleaned cells and positive integers are cleaned cells.
 * Prompts the user for desired % of the room to clean, program automatically stops when that percentage is reached.
 * An image is displayed where red is the obstacles (-1), blue are the uncleaned cells (0), and shades of yellow are
 * cleaned cells based on how many times the roomba went over them while cleaning (>1).
 * @author Kate Choi
 * @date 11/2/2023
 ***********************************************************************/

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class RoombaSimulator {
    static final int WINDOW_SIZE = 500;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter file path: "); // room_conf.txt
        String filename = scan.next();

        DrawingPanel panel = new DrawingPanel(WINDOW_SIZE, WINDOW_SIZE);
        Graphics g = panel.getGraphics();

        // Load  room configuration from the file
        int[][] room = loadConfiguration(filename);
        if (room == null) {
            System.out.println("Invalid room configuration.");
            return;
        }

        // Display initial room configuration
        print2DArray(room); // as an array in the console
        // as an image from DrawingPanel
        g.drawImage(convertToImage(room), 1, 1, WINDOW_SIZE - 2, WINDOW_SIZE - 2, null);

        System.out.print("Enter percentage of the room to clean: "); // 0 - 100;
        double percentWanted = scan.nextDouble();
        double percentDone = 0;

        // Set initial position to the center of the room.
        int x = room.length / 2;
        int y = room[0].length / 2;
        int steps = 0;

        // Continue cleaning until the desired percentage is reached
        while (percentDone < percentWanted) {
            String direction = newMove();
            int[] position = newPosition(direction, x, y);

            int newX = position[0];
            int newY = position[1];

            // Check if the new position is within the bounds and is not an obstacle (room[][] = -1 is an obstacle)
            if (isWithinBounds(newX, newY, room) && room[newX][newY] != -1) {
                x = newX;
                y = newY;
                // Increment the cell value, setting it to at least 1
                room[x][y] = Math.max(1, room[x][y] + 1);
                percentDone = calculatePercentCleaned(room);
                steps++;
            }
        }

        // Display final room configuration
        print2DArray(room);
        System.out.println("Roomba is done.");
        System.out.println("Roomba took " + steps + " steps");
        g.drawImage(convertToImage(room), 1, 1, WINDOW_SIZE - 2, WINDOW_SIZE - 2, null);

    }

    // Generate random movement direction
    public static String newMove() {
        Random rand = new Random();
        String[] moves = {"right", "left", "up", "down"};
        return moves[rand.nextInt(4)];
    }

    // Calculate the new position based on the given direction
    public static int[] newPosition(String direction, int x0, int y0) {
        int x = x0, y = y0;
        if (direction.equals("right")) {
            x++;
        } else if (direction.equals("left")) {
            x--;
        } else if (direction.equals("up")) {
            y--;
        } else if (direction.equals("down")) {
            y++;
        }
        int[] position = {x, y};
        return position;
    }

    // Check if the given position is within the room bounds
    public static boolean isWithinBounds(int x, int y, int[][] room) {
        return x >= 0 && x < room.length && y >= 0 && y < room[0].length;
    }

    // Calculate the percentage of the room that is cleaned
    public static double calculatePercentCleaned(int[][] room) {
        int totalCells = room.length * room[0].length;
        int cleanedCells = 0;
        for (int[] row : room) {
            for (int cell : row) {
                if (cell >= 1) { // Consider cells with values >= 1 as cleaned
                    cleanedCells++;
                }
            }
        }
        return (cleanedCells * 100.0) / totalCells;
    }

    /**
     * loadConfiguration() receives the name of the text file to read as its parameter
     * and returns a 2D int array containing the file's content.
     *
     * The file should be formatted as:
     *      n
     *      n
     *      <a list of n*n integer values separated by spaces or new lines>
     *
     * @param filename String
     * @return 2D array of ints, or null if an error occurs.
     */
    public static int[][] loadConfiguration(String filename) {
        int[][] room = null;
        Scanner reader = null;
        // Create file object
        File file = new File(filename);
        try {
            // Try opening the file to read with a Scanner object
            reader = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            // If the file doesn't exist then print error and quit
            e.printStackTrace();
        }
        // Read the first two integers; should be rows x columns
        int n, m;
        n = reader.nextInt();
        m = reader.nextInt();
        if (m != n) {
            return null;
        }
        // Now read the rest of the data
        room = new int[n][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (reader.hasNextInt()) {
                    room[i][j] = reader.nextInt();
                } else {
                    return null;
                }
            }
        }
        return room;
    }

    // Display a 2D array
    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * convertToImage() converts a 2D int array to an Image object
     * @param room int[][]
     * @return Image
     */
    private static Image convertToImage(int[][] room) {
        int pixel;
        // Create a BufferedImage object
        int width = room[0].length;
        int height = room.length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Set the pixel values
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Obstacles (-1) should show up as red
                // Spaces not visited (0) as blue
                // Visited spaced (>0) as shades of yellow
                if (room[x][y] == -1) {
                    pixel = getColor(255, 0, 0); // red
                } else if (room[x][y] == 0) {
                    pixel = getColor(0, 0, 255); // blue
                } else {
                    pixel = room[x][y] + 80;
                    pixel = getColor(pixel, pixel, 0);
                }
                image.setRGB(x, y, pixel);
            }
        }
        return image;
    }

    private static int getColor(int red, int green, int blue) {
        red = red % 256;
        green = green % 256;
        blue = blue % 256;
        return (red << 16) | (green << 8) | blue;
    }

}