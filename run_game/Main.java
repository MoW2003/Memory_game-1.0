package run_game;

import database_connector.JDBC;
import main_menu.Animations;
import main_menu.LoginMenu;

public class Main {
    public static void main(String[] args) {
        try {
            Animations.welcomeMessage();
            JDBC.getConnection();
            LoginMenu i = new LoginMenu();
            i.loginMenu();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
