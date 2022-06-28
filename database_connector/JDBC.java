package database_connector;

import java.sql.*;

import game_code.gameCode;
import main_menu.LoginMenu;

public class JDBC {
    public void getUser() throws Exception { // Connection for logging in.
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT id_players, username, birthdate, password FROM players "
            + "WHERE username = '" + LoginMenu.getUsername() + "' AND password = '" + LoginMenu.getPassword() + "';");
            
            while (results.next()) {
                LoginMenu.setDbPlayerId(results.getString("id_players"));
                LoginMenu.setDbUsername(results.getString("username"));
                LoginMenu.setDbBirthdate(results.getString("birthdate"));
                LoginMenu.setDbPassword(results.getString("password"));
            }
        }
        finally {}
        conn.close();
    }

    public void checkUser() throws SQLException { // Connection to check if a username is available.
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT username FROM players WHERE username = '" + LoginMenu.getDbUsername() + "';");

            while (results.next()) {
                LoginMenu.setCheckUser(results.getString("username"));
            }
        }
        finally {}
        conn.close();
    }

    public void insertUsers() throws Exception { // Connection to register an new account.
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO players (username, birthdate, password) " 
            + "VALUES ('" + LoginMenu.getDbUsername() + "', '" + LoginMenu.getDbBirthdate() + "', '" + LoginMenu.getDbPassword() +"');");
        }
        finally {}
        conn.close();
    }

    public void editUser() throws Exception { // Connection to edit account.
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE players SET username = '" + LoginMenu.getDbUsername() + "', birthdate = '" + LoginMenu.getDbBirthdate() 
            + "', password = '" + LoginMenu.getDbPassword() + "' WHERE username = '" + LoginMenu.getUsername() + "';");
        }
        finally {}
        conn.close();
    }

    public void printUsers() throws SQLException { // Connection to get leaderboard.
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet results =  statement.executeQuery("SELECT username, MAX(score) FROM scoreboard " 
            + "INNER JOIN players ON scoreboard.id_players=players.id_players GROUP BY username ORDER BY score DESC LIMIT 10;");

            while (results.next()) {
                String username = results.getString("username");
                String score = results.getString("MAX(score)");
                // System.out.printf("%-14s%-5s\n","Player","Score");
                System.out.printf("%-16s%-8s\n",username, score);
            }
        }
        finally {}
        conn.close();
    }

    public void getGames() throws SQLException { // Connection to get played games.
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT username, COUNT(score), MAX(score) FROM scoreboard"
            + " INNER JOIN players ON scoreboard.id_players=players.id_players WHERE username = '" + LoginMenu.getUsername() + "' GROUP BY username ORDER BY score DESC;");

            while (results.next()) {
                LoginMenu.setGames(results.getString("COUNT(score)"));
                LoginMenu.setHScore(results.getString("MAX(score)"));
            }
        }
        finally {}
        conn.close();
    }

    public void insertScore() throws SQLException { // Connection to insert score.
        Connection conn = getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO scoreboard (id_players, time, score) "
            + "VALUES (" + LoginMenu.getDbPlayerId() + ", '" + gameCode.getDateTime() + "', " + gameCode.getScore() + ");");
        }
        finally {}
        conn.close();
    }

    public static Connection getConnection() { // Connection to get a connection.
        try {
            String serverName = "localhost";
            String schema = "game"; //database name
            String url = "jdbc:mysql://" + serverName +  "/" + schema;
            String username = "user"; // Database username
            String password = "user"; // Database password
            return DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            System.out.println("Could not connect to the database " + e.getMessage());
        }
        return null;
    }
}
