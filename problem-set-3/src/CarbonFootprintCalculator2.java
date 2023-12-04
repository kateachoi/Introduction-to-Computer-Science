/**********************************************************************
 * @file CarbonFootprintCalculator2.java
 * @brief Displays menu of 20 food items as an array, with number of calories user chooses to input next to it.
 * Adds up all the calories from user input to calculate daily and annual carbon footprint.
 * Allows for user to choose the same option multiple times; total cal per option is accumulated; it does not reset.
 * @author Kate Choi
 * @date 11/2/2023
 ***********************************************************************/

import java.util.Scanner;

public class CarbonFootprintCalculator2 {
    public static void main(String[] args) {
        // String array to store food products
        String[] products = {
                "Lamb", "Beef", "Turkey", "Broccoli", "Tuna", "Salmon",
                "Cheese", "Pork", "Yogurt", "Chicken", "Milk", "Eggs", "Rice",
                "Potatoes", "Beans", "Tomatoes", "Tofu", "Lentils", "Peanut Butter",
                "Nuts"};
        // Double array to store emission factors
        double[] emissionFactors = {0.02085, 0.01378, 0.00583, 0.00571, 0.00526,
                0.00515, 0.00447, 0.00445, 0.00349, 0.00337, 0.00317, 0.00306,
                0.00208, 0.00146, 0.00140, 0.00139, 0.00138, 0.00078, 0.00042,
                0.00039};

        // Double array to track and store user input of calories per food item
        double[] calories = new double[20];

        // Title and header
        for (int i = 0; i < 37; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println("Carbon Footprint Calculator for Foods");
        for (int i = 0; i < 37; i++) {
            System.out.print("=");
        }
        System.out.println();
        System.out.println("Let’s estimate the kilograms of CO2e that your choice of foods for a daily diet\n" +
                "would release to the environment.");

        // Print initial menu
        printMenu(products, calories);
        // New array for total emission per
        // food item
        double[] userEmission = new double[20];

        Scanner scan = new Scanner(System.in);

        boolean quit = false;
        while(!quit) {
            System.out.print("Select a food product (or 0 to quit): ");
            int opt = scan.nextInt();
            if (opt == 0) {
                quit = true;
            } else if (opt >= 1 && opt <= 20) {
                System.out.print("Number of calories for " + products[opt - 1] + ": ");
                int serv = scan.nextInt();
                if (serv <= 0.0) {
                    while (serv <= 0.0) {
                        System.out.println("Invalid, serving should be greater than 0.");
                        System.out.print("Number of calories for " + products[opt - 1] + ": ");
                        serv = scan.nextInt();
                    }
                }
                calories[opt - 1] += serv;
                printMenu(products, calories);
            } else {
                System.out.println("Invalid: Not a menu option.");
            }
        }

        // User emissions
        for (int i = 0; i < userEmission.length; i++) {
            userEmission[i] = calories[i] * emissionFactors[i];
        }

        // Total number of calories
        double totalCal = 0.0;
        for (int i = 0; i < calories.length; i++) {
            totalCal += calories[i];
        }

        // Carbon footprint
        double carbonFootprint = 0.0;
        for (int i = 0; i < userEmission.length; i++) {
            carbonFootprint += userEmission[i];
        }

        // Print information
        System.out.printf("Your total number of calories would be: %.2f%n", totalCal);
        System.out.printf("Your daily carbon footprint would be: %.2f kg of CO2e%n", carbonFootprint);
        System.out.printf("Your annual food carbon footprint would be %.2f tons of CO2e%n",
                carbonFootprint * 365.0 / 1000);
    }

    public static void printMenu(String[] productList, double[] calorieList) {
        int i, j;

        // 2D String array with all the information to be displayed on the screen for formatting purposes.
        String[][] menu = new String[10][8];

        // Populate the menu array with information from food array (productList[]), calories user inputted
        // (calorieList[]); include another column to String "servings" and display a number beside each food
        // item so the user knows what item correlates to what number.

        for (i = 0; i < 10; i++) {
            menu[i][0] = String.valueOf(i + 1) + ".";
            menu[i][1] = productList[i];
            menu[i][2] = "servings: ";
            menu[i][3] = String.format("%.2f", calorieList[i]) + "     ";
            menu[i][4] = String.valueOf(i + 11) + ".";
            menu[i][5] = productList[i + 10];
            menu[i][6] = "servings: ";
            menu[i][7] = String.format("%.2f", calorieList[i + 10]);
        }

        // Determine column widths for proper formatting
        int[] colWidths = new int[8];
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 8; j++) {
                if (menu[i][j] != null && menu[i][j].length() > colWidths[j]) {
                    colWidths[j] = menu[i][j].length();
                }
            }
        }

        // Print menu array with proper formatting
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 8; j++) {
                if (menu[i][j] != null) {
                    System.out.printf("%-" + (colWidths[j] + 2) + "s", menu[i][j]);
                } else {
                    // If the element is null, print an empty string with proper formatting
                    System.out.printf("%-" + (colWidths[j] + 2) + "s", "");
                }
            }
            System.out.println();
        }
    }

}

