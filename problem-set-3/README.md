# CSC 111: Problem Set #3
Learning Objectives:
1) Use arrays to store user data
2) Iterate through arrays to make specific calculations

## 1. Carbon Footprint Calculator – Version 2
Your new version of the CarbonFootprintCalculator should allow the user to select specific food products from the list and enter their corresponding estimated consumption in kCal. Below is a sample of what the output should be.

## 2. Roomba
The main objective of this problem is to design and simulate a Roomba-like robot capable of exploring a given environment. As in our random walk problem, our robot is constrained to moving in only one of four directions: up, down, left, or right. It’s objective is to go over most of the available space in the given space while avoiding obstacles. 
1) Show the initial room configuration in a DrawingPanel
2) Ask the user for a target coverage percentage, i.e., a value between 1 to 98
3) Upon completion, the number of steps that Roomba took should be printed to the screen
4) Show how Roomba covered the space as a heatmap.

## 3. Tic-Tac-Toe – Version 2
In a previous problem set, we implemented a simple version of the classic Tic-Tac-Toe game that has much to be desired. For example, if a player clicks on an already occupied box on the board twice, the program should not put multiple Os and/or Xs on top of each other; instead, it should simply ignore the click. We will implement a better version of the Tic-Tac-Toe game in this problem set.\
Additional Rules:
1) The first player always starts with X
2) Your program must check if the user’s click is valid (i.e., if the user clicks on an empty box). Your program will continue to wait for valid user input (and ignore invalid mouse clicks) before proceeding. This means that your program should use an array to monitor the current state of the board. Use 0 to indicate a space is empty, 1 for Xs and 2 for Os.
3) Your program should check if there is a winner after every valid move. The game stops as soon as there is a winner or when all boxes are occupied (i.e., a tie).
4) When the game stops, your program should print a message on the canvas indicating whether the game ended in a tie, or if it is not a tie, the winner of the game.
