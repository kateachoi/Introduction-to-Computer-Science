/**********************************************************************
 * @file HomePageGenerator.java
 * @brief This program generates a simple homepage for the user with information inputted by the user, incorporating HTML.
 * @author Kate Choi
 * @date 9/15/2023
 ***********************************************************************/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class HomePageGenerator {

    public static void main(String[] args) {

        Scanner hpg = new Scanner(System.in); // "hpg" = HomePageGenerator

        // String everything
        String output;

        String name;
        String birthPlace;
        String mom;
        String dad;
        String currentPlace;
        String job;
        String workPlace;
        String significantOther;
        String gender;

        String hobby1;
        String hobby2;
        String hobby3;

        // Gather user information
        System.out.println("What is your name?");
        name = hpg.nextLine();

        System.out.println("Where were you born, " + name + "?");
        birthPlace = hpg.nextLine();

        System.out.println("Are you a daughter or a son, " + name + "?");
        gender = hpg.nextLine();

        System.out.println("What is your mother's name, " + name + "?");
        mom = hpg.nextLine();

        System.out.println("And your father's name?");
        dad = hpg.nextLine();

        System.out.println("Where do you currently live, " + name + "?");
        currentPlace = hpg.nextLine();

        System.out.println("What do you do for work?");
        job = hpg.nextLine();

        System.out.println("Where do you work as a " + job + ", " + name + "?");
        workPlace = hpg.nextLine();

        System.out.println("Not to get too personal, but who are you dating?");
        significantOther = hpg.nextLine();

        // List of three hobbies
        System.out.println("What is your favorite hobby?");
        hobby1 = hpg.nextLine();

        System.out.println("What is your second favorite hobby?");
        hobby2 = hpg.nextLine();

        System.out.println("What is your third favorite hobby?");
        hobby3 = hpg.nextLine();


        // Layout for HTML homepage file
        output = "<!DOCTYPE html>";
        output = output + "<html>";
        output = output + "<body>";
        output = output + "<h1>" + name + "</h1>";
        output = output + "<h2> About Me <h2>";
        output = output + "<h3>" + "Born in " + birthPlace + ", " + gender + " of " + dad + " and " + mom
                + ". Currently lives in " + currentPlace + " and works as a " + job + " for " + workPlace
                + ". Dating " + significantOther + ". <h3>";
        output = output + "<h4> Favorite Things To Do <h4>";
        output = output + "<h5> - " + hobby1 + "<h5>";
        output = output + "<h6> - " + hobby2 + "<h6>";
        output = output + "<h7> - " + hobby3 + "<h7>";

        writeToFile(output);

    }

    /*
        writeToFile() saves the value provided in parameter str to a file called
        homepage.html

        If the file exists, its contents will be replaced
    */

    public static void writeToFile(String str) {

        File file = new File("homepage.html");

        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            System.out.println("Writing to file... ");
            out.write(str);
            out.newLine();
            out.close();
        }

        catch (Exception e) {
            e.printStackTrace();

        }

    }

}




