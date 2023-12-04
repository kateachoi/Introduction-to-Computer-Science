import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingWithMouse {

    public static void main(String[] args) {
        // window of size 400 x 400
        final int WINDOW_SIZE = 8 * 50; // larger window: 50 --> 70
        // variable declaration + new object
        DrawingPanel panel = new DrawingPanel(WINDOW_SIZE, WINDOW_SIZE); // object; not doing any drawing yet
        // asking panel to give its graphics object (and store it in variable g)
        Graphics g = panel.getGraphics();

        // g.drawRect(x: top left corner, y: top left corner, width: 400 - 2, height: 400 - 2)
        // creates a "border" around rectangle from window size
        g.drawRect(1, 1, WINDOW_SIZE-2, WINDOW_SIZE-2);
        // default pen color: black

        // The modern way to pass an event handler is by using a lambda
        // function as shown below.
        //
        // method -- passes code as a parameter
        // if the user clicks the mouse...
        panel.onMouseClick(
                (x, y) -> {
                    System.out.println(x + " " + y);
                    g.setColor(Color.RED);
                    g.drawRect(x, y, 1, 1);
                    g.setColor(Color.BLUE);
                    g.drawOval(x+5, y+5, 2, 2);
                }
        );

    }
}
