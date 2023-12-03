/**********************************************************************
 * @file Rocket.java
 * @brief This program creates a rocket using different methods to avoid redundancy.
 * @author Kate Choi
 * @date 10/10/2023
 ***********************************************************************/
public class Rocket {

    public static void main(String[] args) {

        // Print order of defined shapes
        printTriangle();
        printSquare();
        printUSA();
        printSquare();
        printLine();
        printUSA();
        printSquare();
        printLine();
        printTriangle();

    }

    // Triangle method
    public static void printTriangle() {

        System.out.println("   /\\   ");
        System.out.println("  /  \\  ");
        System.out.println(" /    \\ ");

    }

    // Square method
    public static void printSquare() {

        System.out.println("+------+");
        System.out.println("|      |");
        System.out.println("|      |");
        System.out.println("+------+");

    }

    // USA method
    public static void printUSA() {

        System.out.println("|United|");
        System.out.println("|States|");

    }

    // Line method
    public static void printLine() {

        System.out.println("+------+");

    }

}
