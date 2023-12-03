/**********************************************************************
 * @file LoanCalculator.java
 * @brief Calculates monthly payment and total interest based off of user input, incorporates savings methods by shortening year or reducing rate
 * @author Kate Choi
 * @date 9/15/2023
 ***********************************************************************/

import java.util.Scanner;
public class LoanCalculator {
    public static void main(String[] args) {

        Scanner lc = new Scanner(System.in); // "lc" = loan calculator

        // Title
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Loan Calculator");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        // Set variables
        double principalAmount; // amount of the loan
        double annualRate; // annual interest rate (%)
        int termInYears; // term of loan in years

        // Get user input
        System.out.print("Enter the amount of the loan (e.g., 10000.00): ");
        principalAmount = lc.nextDouble();

        System.out.print("Enter the annual interest rate (e.g., 5): ");
        annualRate = lc.nextDouble();

        System.out.print("Enter the term of the loan in years (e.g., 5): ");
        termInYears = lc.nextInt();

        // Convert annual interest rate to monthly interest rate
        double monthlyRate = annualRate / 12 / 100;

        // Convert term in years to months
        int n = termInYears * 12;

        // Calculate monthly payment using the provided formula
        double monthlyPayment = principalAmount * (monthlyRate * Math.pow(1 + monthlyRate, n)) /
                (Math.pow(1 + monthlyRate, n) - 1);

        // Calculate total interest
        double totalInterest = (monthlyPayment * n) - principalAmount;

        // Print monthly payment and total interest to two decimal places
        System.out.printf("Your monthly payment is: $%.2f%n", monthlyPayment);
        System.out.printf("Total interest to be paid: $%.2f%n", totalInterest);

        /* Smarter Loan Calculator */

        System.out.println("You can save money if you shorten your loan term or negotiate a better interest rate!");

        // Calculate new monthly payment and interest saved when shortening the term
        System.out.print("Enter the number of years you want to shorten (e.g., 1): ");
        int trimYears = lc.nextInt(); // years term is shortened by

        int newTerm = (termInYears - trimYears) * 12; // new term in months
        int newTermInYears = termInYears - trimYears; // new term in years

        double newMonthlyPayment = principalAmount * (monthlyRate * Math.pow(1 + monthlyRate, newTerm)) /
                (Math.pow(1 + monthlyRate, newTerm) - 1);

        double newInterest = (newMonthlyPayment * newTerm) - principalAmount; // new interest
        double interestSaved = totalInterest - newInterest; // interest saved by shortening years

        System.out.println("If you pay off your loan in " + newTermInYears + " years (instead of " +
                termInYears + "), your new monthly payment would be ");
        System.out.printf("$%.2f, and you will save $%.2f in interest!%n", newMonthlyPayment, interestSaved);

        // Calculate new monthly payment and interest saved when reducing the interest rate
        System.out.print("Enter the amount of interest rate you want to reduce (e.g., 0.5): ");
        double rInterestPercent = lc.nextDouble(); // % rate is reduced by

        double rAnnualRate = annualRate - rInterestPercent; // new annual rate
        double rMonthlyRate = rAnnualRate / 12 / 100; // new monthly rate

        double rMonthlyPayment = principalAmount * (rMonthlyRate * Math.pow(1 + rMonthlyRate, n)) /
                (Math.pow(1 + rMonthlyRate, n) - 1);

        double rInterest = (rMonthlyPayment * n) - principalAmount; // new interest
        double rInterestSaved = totalInterest - rInterest; // interest saved by reducing rate

        System.out.printf("If you reduce your interest rate to %.2f%% (instead of keeping it at %.2f%%), your new %n",
                rAnnualRate, annualRate);
        System.out.printf("monthly payment would be $%.2f, and you will save $%.2f in interest!%n",
                rMonthlyPayment, rInterestSaved);

    }
}

