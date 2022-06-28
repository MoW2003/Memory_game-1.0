package main_menu;

import java.util.InputMismatchException;
import java.sql.SQLException;
import java.util.Scanner;

import database_connector.JDBC;
import game_code.gameCode;

public class Menu {
    public void mainMenu() throws InterruptedException { // Displays the main menu.
        String[] menu = {
            "      Menu ",
            "----------------",
            "1. Play as " + LoginMenu.getUsername(),
            "2. Account",
            "3. Leaderboard",
            "4. Credits",
            "5. Exit",
            " "
        };
        int option;
        while (true) {
            for (String i : menu) {
                System.out.println(i);
            }
            System.out.print("Choose your option: ");
            Scanner answer = new Scanner(System.in);
            try {
                option = answer.nextInt();
                Animations.border();

                switch (option) {
                    case 1:
                        gameCode g = new gameCode();
                        g.game();
                        break;

                    case 2:
                        SubMenu a = new SubMenu();
                        a.manageAccount();
                        break;

                    case 3:
                        printScoreboard();
                        break;

                    case 4:
                        printCredits();
                        break;

                    case 5:
                        exitMenu();
                        break;
                }
            }
            catch (InputMismatchException e) {
                Animations.menuExeption();
            }
            catch (Exception e) {
                Animations.border();
                System.out.println("Exeption: " + e);
                enterReturn();
            }
        }
    }

    public void printScoreboard() throws SQLException {
        System.out.println("  ~ Leaderboard ~" + "\r\n"); // Displays the leaderboard.

        JDBC j = new JDBC();
        j.printUsers();
        
        enterReturn();
    }

    public void printCredits() { // Displays credits.
        String[] credits = {
            "        ~ Developers ~        ",
            "                              ",
            "Cheung  Shahrukh   SE/1121/062",
            "Joghi   Vishal     BI/1121/019",
            "Bonoo   Kevin      BI/1121/004",
            "Kaman   Keano      BI/1121/020"
        };
        for (String i : credits) {
            System.out.println(i);
        }
        enterReturn();
    }

    public static void exitMenu() { // Exits the application.
        String[] exitMessage = {
            "Thank you for playing!",
            " "
        };
        for (String i : exitMessage) {
            System.out.println(i);
        }
        System.exit(0);
    }

    public static void enterReturn() { // Waits for the user input to return.
        Scanner scan = new Scanner(System.in);
        System.out.print("\r\n" + "Press 'Enter' to return.");
        scan.nextLine();
        Animations.border();
        // scan.close();
    }

    public static void enterContinue() { // Waits for the user input to continue.
        Scanner scan = new Scanner(System.in);
        System.out.print("\r\n" + "Press 'Enter' to continue.");
        scan.nextLine();
        Animations.border();
        // scan.close();
    }
}
