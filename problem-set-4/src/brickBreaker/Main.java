/**********************************************************************
 * @file Main.java
 * @brief Brick-breaker is a GUI based java game. This project is also created from scratch. Brick-breaker game is
 * a game in which a player is given a small platform at the bottom of the screen, which he/she can control using
 * arrows in the keyboard. The player uses the platform to keep the ball running in the game. The goal of the player
 * is to break as many bricks as possible. You will lose if you miss the ball with your platform. In this project,
 * we will be using java swing, OOPs concepts and much more.
 * @author Kate Choi
 * @date 12/3/2023
 ***********************************************************************/

package brickBreaker;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create JFrame
        JFrame obj = new JFrame();
        // Create Gameplay object
        Gameplay gamePlay = new Gameplay();
        // Set JFrame properties
        obj.setBounds(10, 10, 700, 600); // background
        obj.setTitle("Breakout Ball"); // title
        obj.setResizable(false); // make it fixed
        obj.setVisible(true); // make it visible
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add Gameplay object to JFrame
        obj.add(gamePlay);
        obj.setVisible(true);
    }
}
