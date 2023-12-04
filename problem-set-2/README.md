# CSC 111: Problem Set #2
Learning Objectives:
1) Use conditionals and loops to implement simple yet realistic problems
2) Use methods to simplify code implementations

## 1. GPA Calculator
Write a Java program, called GPACalculator, that first asks the user for the number of courses. Then, it
should asks the user to input the number of credits and the predicted letter grade for each course.

## 2. Black Jack
Implement a simplified version of the Blackjack game in java. Develop your code incrementally and test your code 
frequently. The player (i.e., the user) inputs h to choose hit and s to choose stand. The dealer (i.e., the computer) 
runs automatically without user intervention. At the end of each turn, the player has the option to play another round 
(y) or end the game (n). Your program should continue prompting the user for valid input if it receives any input other
than the ones mentioned above. At the end of the game, your program should also print the statistics of the game before 
it terminates.

## 3. Random Walks
Write a Java program, called RobotRandomBehavior, that asks the user for the size of the grid N and
draws the corresponding N × N grid using StdDraw. It then proceeds to simultaneously simulate and draw
the paths that 3 different robots would take starting at the center coordinate (N/2, N/2) until all three end
out of the grid. Once all three robots are out the grid, the program should show the simulation statistics.

## 4. Tic-Tac-Toe
Let us create a simple version of the classic Tic-Tac-Toe game (TicTacToe.java). In this version, you will
draw a 3 × 3 board on the screen using StdDraw. Two players take turns clicking on the board to put Os and
Xs. The first player (i.e., the one who makes the first mouse click) always puts Os. The Os and Xs must fit the
entire box where the mouse click occurs. We assume that the players will click only on unoccupied boxes on
the board. Therefore, if the players violate this assumption, it is OK that your program puts multiple Os or
Xs on top of each other. However, your program should allow only 9 mouse clicks on the board, even if the
players make a mistake.