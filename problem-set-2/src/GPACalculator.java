/**********************************************************************
 * @file GPACalculator.java
 * @brief Calculates GPA based off user input for number of courses and credit per course.
 * @author Kate Choi
 * @date 10/10/23
 ***********************************************************************/

import java.util.Scanner;
public class GPACalculator {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        // Declare and initialize variables
        double gpa, credits;
        int i = 1;
        String grade, choice;
        boolean add = true, input = true, valid = false;
        double val = 0.0, totalVal = 0.0, totalCredits = 0.0;

        // Title
        System.out.println("GPA Calculator =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

        // Ask user for number of credits
        while (add == true) {
            System.out.print("For course " + i + ", how many credits: ");
            credits = kb.nextDouble();
            // Ask user for letter grade
            while (valid == false) {
                System.out.print("For course " + i + ", what letter grade: ");
                grade = kb.next();
                // Call gradeConversion() method to calculate double for inputted letter grade.
                val = gradeConversion(grade);
                if (val >= 0) {
                    valid = true; // End while loop
                } else { // If grade entered is invalid, prompt the user again
                    System.out.println("Invalid input: " + grade);
                }
            } valid = false; // So that the user is prompted for the letter grade per iteration.

            // Ask user if they want to add another course
            System.out.print("Enter a new course [y/n]? ");
            choice = kb.next();

            totalCredits += credits; // Credits accumulator
            totalVal += (val * credits); // Value accumulator
            i += 1; // Increase course number by 1 per iteration.

            // End while loop if the user does not want to add another course
            if (choice.equals("n")) {
                add = false;
            } else if (choice.equals("y")) { // Continue while loop if the user does want to add another course
                add = true;
            } else {
                System.out.println("Invalid input.");
                input = false;
            }

            while (input == false) { // Keep prompting user until a valid input is stated.
                System.out.print("Enter a new course [y/n]? ");
                choice = kb.next();
                if (choice.equals("y")) { // End while loop
                    input = true;
                } else if (choice.equals("n")) {
                    add = false;
                    input = true; // End while loop
                }
            }

        }

        gpa = totalVal / totalCredits;
        System.out.printf("Your GPA is: %.2f", gpa);
    }


    // Method to convert letter grades into double values
    // Calling gradeConversion() returns a double whose value is equivalent to the letter grade inputted.
    public static double gradeConversion(String grade) {
        double val;

        if (grade.equals("A") || grade.equals("a")) {
            val = 4.0;
        } else if (grade.equals("A-") || grade.equals("a-")) {
            val = 3.67;
        } else if (grade.equals("B+") || grade.equals("b+")) {
            val = 3.33;
        } else if (grade.equals("B") || grade.equals("b")) {
            val = 3.0;
        } else if (grade.equals("B-") || grade.equals("b-")) {
            val = 3.67;
        } else if (grade.equals("C+") || grade.equals("c+")) {
            val = 2.33;
        } else if (grade.equals("C") || grade.equals("c")) {
            val = 2.0;
        } else if (grade.equals("C-") || grade.equals("c-")) {
            val = 1.67;
        } else if (grade.equals("D+") || grade.equals("d+")) {
            val = 1.33;
        } else if (grade.equals("D") || grade.equals("d")) {
            val = 1.0;
        } else if (grade.equals("D-") || grade.equals("d-")) {
            val = 0.67;
        } else if (grade.equals("F") || grade.equals("f")) {
            val = 0.0;
        } else {
            val = -1.0;
        }

        return val;
    }

}
