/**********************************************************************
 * @file CarbonFootprintCalculator.java
 * @brief This program calculates total cals/day, daily carbon footprint, and annual carbon footprint based off of user input.
 * @author Kate Choi
 * @date 9/15/2023
 ***********************************************************************/

import java.util.Scanner;
public class CarbonFootprintCalculator {
    public static void main(String[] args) {

        // Title
        System.out.println("=====================================");
        System.out.println("Carbon Footprint Calculator for Foods");
        System.out.println("=====================================");

        // Description
        System.out.println("Find out how many kilograms of CO2e your choice of foods for a daily " +
                "diet would produce.");
        System.out.println("Assuming a recommended 2000 kCals/day, for each item, enter the amount of kCal\n" +
                "you would consumed in a day.");

        int num; // number of calories
        int sum = 0; // accumulator
        double cfsum = 0; // accumulator for carbon footprint (cf)

        Scanner cf = new Scanner(System.in); // "cf" = carbon footprint

        /* Get user data for amount of kCal consumed for each food item
            - <num> receives input from user
            - <sum += num> accumulates each kCal inputted by the user for the total at the end.
            - <cfsum += num * (emissions factor)> accumulates emission of each food item by multiplying kCal from user input
               by provided emissions factors [kgCO2e/kCal] for total carbon footprint at the end.
        */

        System.out.print("Beef: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.01378;

        System.out.print("Chicken: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00337;

        System.out.print("Cheese: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00447;

        System.out.print("Yogurt: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00349;

        System.out.print("Milk: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00317;

        System.out.print("Eggs: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00306;

        System.out.print("Rice: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00208;

        System.out.print("Tofu: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00138;

        System.out.print("Beans: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00140;

        System.out.print("Lentils: ");
        num = cf.nextInt();
        sum += num;
        cfsum += num * 0.00078;

        System.out.print("Nuts: ");
        num = num + cf.nextInt();
        sum += num;
        cfsum += num * 0.00039;

        // Print total daily calories
        System.out.println("Your total number of calories per day would be: " + sum);

        // Print daily carbon footprint to three decimal places
        System.out.printf("Your daily food carbon footprint would be %.3f kilograms of CO2e%n", cfsum);

        // Convert daily carbon footprint (kgCO2e) to annual carbon footprint (tons) and print to three decimal places
        System.out.printf("Your annual food carbon footprint would be %.3f tons of CO2e%n", cfsum * 365.0 / 1000);

    }
}


