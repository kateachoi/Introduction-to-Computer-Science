/**********************************************************************
 * @file BlackJack.java
 * @brief Simplified computer-simulated Blackjack game.
 * @author Kate Choi
 * @date 10/10/2023
 ***********************************************************************/

import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {

        // Title
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Welcome To the Blackjack Table");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        // Create new objects
        Scanner kb = new Scanner(System.in);
        Random rand = new Random();

        // Declare and initialize variables
        boolean play = true;
        int p1, p2, p3, d;
        int pTotal = 0, dTotal = 0, max = 21, bound = 17, dWins = 0, pWins = 0, ties = 0;
        boolean another;
        String hs, yn;
        String winner = "";

        // Each round while player wants to play
        while (play) {
            pTotal = 0;
            dTotal = 0;
            d = 0;

            // Player's turn
            System.out.println("Player’s Turn +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
            p1 = rand.nextInt(11) + 1;
            System.out.println("Card: " + p1 + " Current Total: " + p1);
            p2 = rand.nextInt(11) + 1;
            pTotal = p1 + p2;
            System.out.println("Card: " + p2 + " Current Total: " + pTotal);

            another = true;

            // Ask the player if they want to hit or stand
            while (another && (pTotal <= 21)) {
                System.out.print("(h)it or (s)tand?: ");
                hs = kb.next();
                // If the player hits, draw another card, and ask again
                if (hs.equals("h")) {
                    p3 = rand.nextInt(11) + 1;
                    pTotal += p3;
                    System.out.println("Card: " + p3 + " Current Total: " + pTotal);
                } else if (hs.equals("s")) { // Stop asking if the player chooses to stand
                    another = false;
                } else { // Ask again if the input is invalid
                    another = true;
                } if(pTotal > 21) {  // Stop asking if the player's card total exceeds 21
                    another = false;
                }
            } System.out.println("Player's total is: " + pTotal); // Print player total per round

            // Dealer's turn: dealer's card keeps drawing while the dealer's score is less than 17
            System.out.println("Dealer’s Turn +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
            while (dTotal < bound) {
                d = rand.nextInt(11) + 1;
                dTotal += d;
                System.out.println("Card: " + d + " Current Total: " + dTotal);
            } System.out.println("Dealer's total is: " + dTotal);

            // Determine the winner of each round
            boolean tie = false;
            if (pTotal > max) { // Player busts
                if (dTotal > max) { // Dealer busts
                    tie = true;
                } else { // Dealer doesn't bust
                    winner = "Dealer";
                }
            } else if (pTotal <= max) { // Player doesn't bust
                if (dTotal <= max) { // Dealer doesn't bust
                    if (pTotal > dTotal) { // Player's score is higher
                        winner = "Player";
                    } else if (dTotal > pTotal) { // Dealer's score is higher
                        winner = "Dealer";
                    } else { // Scores are equal
                        tie = true;
                    }
                } else { // Dealer busts
                    winner = "Player";
                }
            }

            if (tie == false) { // Declare winner
                System.out.println(winner + " wins!");
            } else { // Declare tie
                System.out.println("Tie!");
                ties++;
            }

            if (winner.equals("Dealer")) {
                dWins++; // Add a dealer win to total wins
            } else if (winner.equals("Player")) {
                pWins++; // Add a player win to total wins
            }

            // Ask the player if they want to play again
            boolean playAgain = true;
            while (playAgain) {
                System.out.print("Play again? [y/n] ");
                yn = kb.next();
                if (!yn.equals("y") && !yn.equals("n")) { // For invalid input
                    while (!yn.equals("y") && !yn.equals("n")) {
                        System.out.print("Play again? [y/n] ");
                        yn = kb.next();
                        playAgain = false;
                    }
                } else if (yn.equals("y")) { // Stop asking to play again, loop around and start "play" again (play = true)
                    playAgain = false;
                } else if (yn.equals("n")) { // Stop asking to play again, stop loop to restart rounds
                    playAgain = false;
                    play = false;
                }
            }
        }

        // Print game statistics
        System.out.println("GAME STATISTICS -------------");
        System.out.println("Player won " + pWins + " times");
        System.out.println("Dealer won " + dWins + " times");
        System.out.println(ties + " ties");

    }
}
