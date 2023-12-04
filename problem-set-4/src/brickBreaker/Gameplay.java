package brickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    // Add properties
    private boolean play = false; // When the game starts, shouldn't be automatic
    private int score = 0; // Starting score

    private int totalBricks = 21; // 7x3 map

    private Timer timer;
    private int delay = 8; // Speed

    // Add x-axis/y-axis slider and ball
    private int playerX = 310; // starting position of slider
    private int ballposX = 120; // starting position of ball in x
    private int ballposY = 350; // starting position of ball in y
    private int ballXdir = -1; // direction of ball in x
    private int ballYdir = -2; // direction of ball in y

    // create an object for the MapGenerator class
    private MapGenerator map;

    // Add constructor with initialized values when gamePlay object is created in Main
    public Gameplay() {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this); // variable=delay, context=this
        timer.start(); // start timer
    }

    // Create a method to draw different shapes
    public void paint(Graphics g) {
        // Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // Call the draw function from MapGenerator class to draw map
        map.draw((Graphics2D) g); // convert to Graphics2D

        // Borders
        g.setColor(Color.yellow);
        // create three rectangles for the border
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        // No border at the bottom because we want the ball to move down for the end of the game

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        // Create the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // Create the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        // Check if the game is finished (totalBricks = 0)
        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won!", 260, 300);
            // restart game?
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);

        }

        // Check game over (ball leaves the screen)
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);
            // restart game?
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);

        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        // Move ball //
        // if right/left arrow key is pressed
        if (play) {
            // Detect intersection with the paddle
            // Create a rectangle around the ball (current:oval)
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            // Iterate through each brick
            // Note: first map is from this class, second map is from MapGenerator class
            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    // Detect intersection with brick
                    if (map.map[i][j] > 0) {
                        // Detect the position of the ball and position of the brick with respect to width and height of the brick
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        // Create rectangle around the brick
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        // Create rectangle around ball in order to detect intersection
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        // Create rectangle and pass reference of rect
                        Rectangle brickRect = rect;

                        // Check intersection
                        if (ballRect.intersects(brickRect)) {
                            // Call setBrickValue() method from MapGenerator class and change its value to zero
                            map.setBrickValue(0, i, j);
                            // Set total bricks remaining
                            totalBricks--;
                            // Increment score
                            score += 5;

                            // Fix problems for left/right side intersections
                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                // Move the ball to the opposite direction
                                ballXdir = -ballXdir; // left/right
                            } else {
                                ballYdir = -ballYdir; // top/bottom
                            }

                            // Take the compiler out of the outer loop:
                            // create a break label (A) since just using break will only break out of the inner loop
                            break A;
                        }

                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            // Compensate for if the ball hits borders
            if (ballposX < 0) { // left border
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) { // top border
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) { // right border
                ballXdir = -ballXdir;
            }
        }

        // Recall the paint() method and draw everything again
        // When incrementing the value of playerX, no change is shown because the paddle needs to be redrawn
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // Add key listeners to detect left arrow key or right arrow key
    @Override
    public void keyPressed(KeyEvent e) {
        // Detect right key
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // increment X position of the slider to the right
            if (playerX > 600) {
                // Ensure it doesn't go outside the panel
                playerX = 600;
            } else {
                moveRight();
            }
        }
        // Detect left key
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // increment X position of the slider to the left
            if (playerX < 10) {
                // Ensure it doesn't go outside the panel
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        // Add a key event to detect if enter is pressed to restart the game
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) { // if the game is over
                // Set everything to default again
                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                // Regenerate the map
                map = new MapGenerator(3, 7);

                repaint();
            }
        }
    }

    // Create moveRight() and moveLeft() methods to move the position of the slider
    public void moveRight() {
        // change boolean variable
        play = true;
        playerX += 20; // increment 20 positions to the right
    }
    public void moveLeft() {
        // change boolean variable
        play = true;
        playerX -= 20; // increment 20 positions to the left
    }

}
