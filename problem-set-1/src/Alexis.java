/**********************************************************************
 * @file Alexis.java
 * @brief This program introduces Alexis, a simple AI personal assistant to have interesting conversations with the user.
 * @author Kate Choi
 * @date 9/15/2023
 ***********************************************************************/

import java.util.Scanner;

public class Alexis {
    public static void main(String[] args) {

        // Create a Scanner object for  interaction
        Scanner a = new Scanner(System.in);

        // String variables
        String name;
        String place;
        String sport;
        String food;

        /* Print the exchange */

        // Exchange 1: name
        System.out.println("Hello! My name is Alexis. What is your name?");
        name = a.nextLine();
        System.out.println(name + " has a nice ring to it!");

        // Exchange 2: place
        System.out.println("Where are you from, " + name + "?");
        place = a.nextLine();
        System.out.println("Wow! I've never been to " + place + " before!");

        // Exchange 3: sport
        System.out.println("What is your favorite sport to watch, " + name + "?");
        sport = a.nextLine();
        System.out.println("Omg! I love watching " + sport + "!");

        // Exchange 4: food
        System.out.println("When you watch " + sport + ", what is your snack food of choice?");
        food = a.nextLine();
        System.out.println("Yum! Personally, I love watching " + sport + " with Bojangles, but " +
                "I'll have to try " + food + " sometime!");

        // End conversation
        System.out.println("Well, it was great talking to you, " + name + ". Have a nice day!");

    }
}
