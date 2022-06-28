package main_menu;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import database_connector.JDBC;

public class LoginMenu {
    public void loginMenu() { // Displays login menu.
        String[] menu = {
            "   Welcome",
            "-------------",
            "1. Login",
            "2. Register",
            "3. Help",
            "4. Exit",
            " "
        };
        int option;
        Animations.border();

        while (true) {
            for (String i : menu) {
            System.out.println(i);
            }
            System.out.print("Your option: ");
            Scanner answer = new Scanner(System.in);
            try {
                option = answer.nextInt();
                Animations.border();

                switch (option) {
                    case 1:
                        getCred();
                        break;

                    case 2:
                        enterCred();
                        break;

                    case 3:
                        help();
                        break;

                    case 4:
                        Menu.exitMenu();
                        break;
                }
            }
            catch (Exception e) {
                // System.out.println("Exeption loginMenu: " + e);
                Animations.menuExeption();
            }
        }
        
    }

    public void getCred() { // User login.
        Scanner userCred = new Scanner(System.in);
        try {
            System.out.println("       ~ Login ~" + "\r\n");

            System.out.print("Username: ");
            setUsername(userCred.nextLine());

            System.out.print("Password: ");
            setPassword(userCred.nextLine());

            JDBC j = new JDBC();
            j.getUser();

            if (LoginMenu.getUsername().equals(LoginMenu.getDbUsername()) &&
                LoginMenu.getPassword().equals(LoginMenu.getDbPassword())) {

                System.out.println("\r\n" + ">> Login succesfull <<");
                setBirthdate(getDbBirthdate());
                Menu.enterContinue();

                Animations.gameMessage();

                Menu m = new Menu();
                m.mainMenu();
            }
            else {
                System.out.println("\r\n" + ">> Incorrect login credentials <<");
                Menu.enterReturn();
                LoginMenu i = new LoginMenu();
                i.loginMenu();
            }
        }
        catch (Exception e) {
            System.out.println("Exeption in loginMenu.getCred: " + e);
        }
    }

    public void enterCred() { // User register.
        Scanner userCred = new Scanner(System.in);
        try {
            System.out.println("        ~ register ~" + "\r\n");
            while (true) {
                try {
                    System.out.print("Usernames need to be unique," + "\r\n" 
                                   + "so be creative." + "\r\n" 
                                   + "Max 10 characters." + "\r\n" + "\r\n"
                                   + "Username: ");

                    setDbUsername(userCred.nextLine());

                    JDBC j = new JDBC();
                    j.checkUser();

                    if (LoginMenu.getDbUsername().equals(LoginMenu.getCheckUser())) {  
                        System.out.println("\r\n" + ">> Username already exists <<");
                        Menu.enterReturn();
                    }
                    else if (!(getDbUsername().length() <= 10)) {
                        System.out.println("\r\n" + ">> Username is too long <<");
                        Menu.enterReturn();
                    }
                    else {
                        break;
                    }
                }
                catch (Exception e) {
                    System.out.println("Exeption: " + e);
                }
            }
            Animations.border();
            while (true) {
                try {
                    System.out.print("Please use the following" + "\r\n" 
                                   + "  format: yyyy-mm-dd" + "\r\n" 
                                   + "     e.g: 2000-12-30" + "\r\n"+ "\r\n"
                                   + "Birthdate: ");
                                   
                    setDbBirthdate(userCred.nextLine());

                    if (!dateValidate()) {
                        System.out.println("\r\n" + ">> Incorrect date or format <<");
                        Menu.enterReturn();
                    }
                    else {
                        break;
                    }
                }
                catch (Exception e) {
                    System.out.println("Exeption: " + e);
                }
            }
            Animations.border();
            while (true) {
                try {
                    System.out.print("To make a stong password:" + "\r\n" 
                           + "use Caps, Numbers & Symbols." + "\r\n"
                           + "Max 10 characters" + "\r\n" + "\r\n"
                           + "Password: ");

                    setDbPassword(userCred.nextLine());

                    if (!(getDbPassword().length() <= 10)) {
                        System.out.println("\r\n" + ">> Password is too long <<");
                        Menu.enterReturn();
                    }
                    else {
                        break;
                    }
                }
                catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
            }

            JDBC j = new JDBC();
            j.insertUsers();
            
            System.out.println("\r\n" + ">> Registration successful <<");
            Menu.enterContinue();
        }
        catch (Exception e) {
            Animations.border();
            System.out.println("Exeption: " + e);
            Menu.enterReturn();
        }
    }

    public boolean dateValidate() { // Validates the date.
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(getDbBirthdate());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void help() { //  Login/Register instructrions.
        String[] help = {
            "   ~ Login instructions ~",
            " ",
            "'Login' to continue and play.",
            " ",
            "'Register' to create an account,",
            " then login.",
            " ",
            " Don't forget to read README file.",
            " "
        };
        test();
        for (String i : help) {
            System.out.println(i);
        }
        Menu.enterReturn();
    }

    private static String username;
    private static String birthdate;
    private static String password;
    private static String games;
    private static String hScore;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }

    public static String getBirthdate() {
        return birthdate;
    }

    public static void setBirthdate(String newBirthdate) {
        birthdate = newBirthdate;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String newPassword) {
        password = newPassword;
    }

    public static String getGames() {
        return games;
    }

    public static void setGames(String newGames) {
        games = newGames;
    }

    public static String getHScore() {
        return hScore;
    }

    public static void setHScore(String newHScore) {
        hScore = newHScore;
    }

    private void test() {
        try {
            URI uri= new URI("https://c.tenor.com/CTpG8Qr1A_AAAAAM/rick-roll-rick-astley.gif");
            java.awt.Desktop.getDesktop().browse(uri);
        }
        catch (Exception e) {}
    }

    private static String dbUsername;
    private static String dbBirthdate;
    private static String dbPassword;
    private static String dbPlayerId;

    public static String getDbUsername() {
        return dbUsername;
    }

    public static void setDbUsername(String newDbUsername) {
        dbUsername = newDbUsername;
    }

    public static String getDbBirthdate() {
        return dbBirthdate;
    }

    public static void setDbBirthdate(String newDbBirthdate) {
        dbBirthdate = newDbBirthdate;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    public static void setDbPassword(String newDbPassword) {
        dbPassword = newDbPassword;
    }

    public static String getDbPlayerId() {
        return dbPlayerId;
    }

    public static void setDbPlayerId(String newDbPlayerId) {
        dbPlayerId = newDbPlayerId;
    }

    private static String checkUser;

    public static String getCheckUser() {
        return checkUser;
    }

    public static void setCheckUser(String newCheckUser) {
        checkUser = newCheckUser;
    }
}
