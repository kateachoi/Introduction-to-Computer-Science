package brickBreaker;

import java.awt.*;

// Class to create bricks
public class MapGenerator {
    // Add a 2d array to contain all the bricks
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    // Add constructor to receive number of rows and columns for a particular number of bricks
    public MapGenerator(int row, int col) {
        // Initialize 2d array with input parameters
        map = new int[row][col];
        // iterate through rows
        for (int i = 0; i < map.length; i++) {
            // iterate through columns
            for (int j = 0; j < map[0].length; j++) {
                // Logic: 1 will detect that this particular brick has not been intersected with the ball, thus shown on the panel
                // 0 will represent no brick in that position
                map[i][j] = 1;
            }
        }

        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }
    // Create a method to draw the bricks
    public void draw(Graphics2D g) {
        // iterate through rows
        for (int i = 0; i < map.length; i++) {
            // iterate through columns
            for (int j = 0; j < map[0].length; j++) {
                // Check if the particular value is greater than 0
                if (map[i][j] > 0) {
                    // Create a brick in that position
                    g.setColor(Color.white);
                    // draw rectangle (brick)
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    // Create border around each individual brick
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black); // same as background color
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    // Method to detect collisions between ball and brick
    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value; // passed from Gameplay class
    }
}
