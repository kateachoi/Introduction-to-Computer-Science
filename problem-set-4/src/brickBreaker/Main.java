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
