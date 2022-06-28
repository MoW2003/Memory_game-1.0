package main_menu;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import database_connector.JDBC;

public class SubMenu {
    public void manageAccount() { // Displays manage account.
        String[] options = {
            "  Manage Account",
            "------------------",
            "1. User profile",
            "2. Switch account",
            "3. Create Account",
            "4. Edit Account",
            "5. Return",
            " "
        };
        int option;

        while (true) {
            for (String subMenu : options) {
            System.out.println(subMenu);
            }
            System.out.print("Choose your option: ");
            Scanner answer = new Scanner(System.in);

            Menu m = new Menu();
            LoginMenu l = new LoginMenu();

            try {
                option = answer.nextInt();
                Animations.border();
                switch (option) {
                    case 1:
                        userProfile();
                        break;

                    case 2:
                        l.getCred();
                        break;

                    case 3:
                        l.enterCred();
                        break;

                    case 4:
                        editCred();
                        break;

                    case 5:
                        m.mainMenu();
                        break;
                }
                
            }
            catch (InputMismatchException e) {
                Animations.menuExeption();
            }
            catch (Exception e) {
                Animations.border();
                System.out.println("Exeption in subMenu: " + e);
                Menu.enterReturn();
            }
        }
    }

    public void userProfile() throws SQLException {

        JDBC j = new JDBC();
        j.getGames();

        String[] print = {
            "    Profile",
            "---------------",
            " Name: " + LoginMenu.getUsername(),
            " Played " + LoginMenu.getGames() + " games",
            " Highscore: " + LoginMenu.getHScore()
        };

        for (String i : print) {
            System.out.println(i);
        }
        Menu.enterReturn();
    }

    public void editCred() { // User edit account.
        Scanner userCred = new Scanner(System.in);
        while (true) {
            String[] options = {
                "     Account details",
                "-------------------------",
                "1. Username:  " + LoginMenu.getUsername(),
                "2. Birthdate: " + LoginMenu.getBirthdate(),
                "3. Password:  " + LoginMenu.getPassword(),
                "4. Return",
                " "
            };
            int option;
            for (String i : options) {
            System.out.println(i);
            }
            System.out.print("Choose your option: ");
            Scanner answer = new Scanner(System.in);
            JDBC j = new JDBC();
            try {
                option = answer.nextInt();
                Animations.border();
                switch (option) {
                    case 1:
                        System.out.print("New username: ");
                        LoginMenu.setDbUsername(userCred.nextLine());
                        try {
                            LoginMenu.setDbBirthdate(LoginMenu.getBirthdate());
                            LoginMenu.setDbPassword(LoginMenu.getPassword());
                            j.editUser();
                        }
                        catch (SQLIntegrityConstraintViolationException e) {
                            System.out.println("\r\n" + ">> Username already taken! <<");
                            Menu.enterReturn();
                            break;
                        }
                        catch (MysqlDataTruncation e) {
                            System.out.println("\r\n" + ">> Username is too long! <<");
                            Menu.enterReturn();
                            break;
                        }
                        catch (Exception e) {
                            System.out.println("exeption in subMenu.editCred: " + e);
                            // e.printStackTrace();
                            break;
                        }
                        LoginMenu.setUsername(LoginMenu.getDbUsername());
                        Animations.border();
                        break;
                    case 2:
                        System.out.print("New birthdate: ");
                        LoginMenu.setDbBirthdate(userCred.nextLine());
                        try {
                            LoginMenu.setDbUsername(LoginMenu.getUsername());
                            LoginMenu.setDbPassword(LoginMenu.getPassword());
                            j.editUser();
                        }
                        catch (Exception e) {
                            System.out.println("\r\n" + ">> Incorrect date or format! <<" + "\r\n" 
                                                     + "      Format: yyyy-mm-dd" + "\r\n" 
                                                     + "         e.g: 2000-12-30");
                            Menu.enterReturn();
                            break;
                        }
                        LoginMenu.setBirthdate(LoginMenu.getDbBirthdate());
                        Animations.border();
                        break;
                    case 3:
                        System.out.print("New password: ");
                        LoginMenu.setDbPassword(userCred.nextLine());
                        try {
                            LoginMenu.setDbUsername(LoginMenu.getUsername());
                            LoginMenu.setDbBirthdate(LoginMenu.getBirthdate());
                            j.editUser();
                        }
                        catch (Exception e) {
                            System.out.println("\r\n" + ">> Password is too long! <<");
                            Menu.enterReturn();
                            break;
                        }
                        LoginMenu.setPassword(LoginMenu.getDbPassword());
                        Animations.border();
                        break;
                    case 4:
                        manageAccount();
                        break;
                }
            }
            catch (InputMismatchException e) {
                // System.out.println("subMenu.editCred: " + e);
                Animations.menuExeption();
            }
            catch (Exception e) {
                Animations.border();
                System.out.println("subMenu.editCred: " + e);
                Menu.enterReturn();
            }
        }
    }
}
