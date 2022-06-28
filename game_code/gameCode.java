package game_code;

import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

import database_connector.JDBC;
import main_menu.Animations;
import main_menu.Menu;

public class gameCode {

    static boolean[][] down = new boolean[4][5]; // 3D array to open and close cards.
    static String[][] open = new String[4][5]; // 3D array to save matched cards.
    static String[][] letters = {{"A", "B", "C", "D", "E"}, // 3D array containing cards.
                                 {"F", "G", "H", "I", "J"},
                                 {"A", "B", "C", "D", "E"},
                                 {"F", "G", "H", "I", "J"}
    };

    public void game() throws InterruptedException, SQLException {
        JDBC j = new JDBC();
            setScore(score = 0);
            setLives(lives = 10);
            setCardsDown(cardsDown = 20);
            randomizer();
            resetOpen();
            runGame();
            calcScore();
            j.insertScore();
    }

    public static void randomizer() { // Randomizes cards.
        for (int r = 0; r < letters.length; r++) {
            for (int c = 0; c < letters[r].length; c++) {
                down[r][c] = false;
                int rs = (int)(Math.random() * letters.length);
                int cs = (int)(Math.random() * letters[r].length);

                String temp = letters[r][c]; // Replaces cards whithin array resulting in a randomization.
                letters[r][c] = letters[rs][cs];
                letters[rs][cs] = temp;
            }
        }
    }

    public static void resetOpen() { // Resets open array for a new game.
        for (int r = 0; r < open.length; r++) {
            for (int c = 0; c < open.length; c++) {
                open[r][c] = null;
            }
        }
    }

    public static void displayBoard() { // Displays board.
        System.out.println("    ~ Memory Impossible ~" + "\r\n");
        System.out.println("  | 1 2 3 4 5" + "     lives: " + getLives());
        System.out.println("--+-----------" + "    points: " + getScore());
        for (int r = 0; r < letters.length; r++) {
            System.out.print((r + 1) + " | ");
            for (int c = 0; c < letters[r].length; c++) {
                if (down[r][c]) {
                    System.out.print(letters[r][c] + " ");
                }
                else {
                    System.out.print("\u0004 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void runGame() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        while (true) {

            if (getLives() == 0) { // Ends game if you're out of lives.
                System.out.println(">> Out of lives <<");
                Menu.enterContinue();
                break;
            }

            if (getCardsDown() == 0) { // Ends game if you've opened all cards.
                System.out.println(">> You win <<");
                Menu.enterContinue();
                break;
            }
            while (true) {
                try {
                    displayBoard();
                    System.out.print("Cord one: ");
                    c1 = scan.next();

                    c1x = Integer.valueOf(c1.substring(0, 1))-1; // Separates x and y cord from string.
                    c1y = Integer.valueOf(c1.substring(1, 2))-1;

                    if (c1.length() != 2) { // Checks if cord has a length of 2.
                        System.out.println("\r\n" + ">> Invalid cord <<");
                        Thread.sleep(1000);
                        Animations.border();
                    }
                    else if (letters[c1x][c1y] == open[c1x][c1y]) { // Checks if card was already matched.
                        System.out.println("\r\n" + ">> Can't open already open card <<");
                        Thread.sleep(1500);
                        Animations.border();
                    } 
                    else {
                        down[c1x][c1y] = true; // Opens card.
                        break;
                    }
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\r\n" + ">> Invalid cord <<");
                    Thread.sleep(1000);
                    Animations.border();
                }
                catch (NumberFormatException e) {
                    System.out.println("\r\n" + ">> Enter cords <<" + "\r\n"
                                              + "  e.g 11 or 45");
                    Thread.sleep(2000);
                    Animations.border();
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\r\n" + ">> Invalid cord <<");
                    Thread.sleep(1000);
                    Animations.border();
                }
                catch (Exception e) {
                    System.out.println("Exception: " + e);
                    Menu.enterReturn();
                }
            }
            while (true) {
                try {
                    Animations.border();
                    displayBoard();
                    System.out.print("Cord two: ");
                    c2 = scan.next();

                    c2x = Integer.valueOf(c2.substring(0, 1))-1; // Separates x and y cord from string.
                    c2y = Integer.valueOf(c2.substring(1, 2))-1;

                    if (c2.length() != 2) { // Checks if cord has a length of 2.
                        System.out.println("\r\n" + ">> Invalid cord <<");
                        Thread.sleep(1000);
                        Animations.border();
                    }
                    else if ((c1x == c2x) && (c1y == c2y)) { // Checks if its not the same card.
                        System.out.println("\r\n" + ">> Can't open the same card <<");
                        Thread.sleep(1500);
                        Animations.border();
                    }
                    else if (letters[c2x][c2y] == open[c2x][c2y]) { // Checks if card were already matched.
                        System.out.println("\r\n" + ">> Can't open already open card <<");
                        Thread.sleep(1500);
                        Animations.border();
                    }
                    else {
                        down[c2x][c2y] = true; // Opens card.
                        break;
                    }
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\r\n" + ">> Invalid cord <<");
                    Thread.sleep(1000);
                    Animations.border();
                }
                catch (NumberFormatException e) {
                    System.out.println("\r\n" + ">> Enter cords <<" + "\r\n"
                                              + "  e.g 11 or 45");
                    Thread.sleep(2000);
                    Animations.border();
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\r\n" + ">> Invalid cord <<");
                    Thread.sleep(1000);
                    Animations.border();
                }
                catch (Exception e) {
                    System.out.println("\r\n" + "Exception: " + e);
                    Menu.enterReturn();
                }
            }
            displayBoard();

            if (letters[c1x][c1y] == letters[c2x][c2y]) { // Compares cards.
                System.out.println(" >> You found a match <<");
                Thread.sleep(1500);
                Animations.border();

                setCardsDown(cardsDown -= 2);
                setScore(score += 2);

                open[c1x][c1y] = letters[c1x][c1y]; // Saves matched cards in open array.
                open[c2x][c2y] = letters[c2x][c2y];
            }
            else { // If cards do not match.
                System.out.println(">> Incorrect <<");
                Thread.sleep(1000);
                Animations.border();

                lives -= 1;

                down[c1x][c1y] = false; // Closes cards.
                down[c2x][c2y] = false;
            }
        }
    }

    public static void calcScore() { // Calculates score.
        System.out.println("Score: " + getScore());
        int bonus = (getLives() * 3);
        System.out.println("Bonus: " + bonus);
        setScore(score += bonus);
        System.out.println("You're final score: " + getScore());
        formTime();
        Menu.enterContinue();
    }

    public static void formTime() { // Generates a date & time in mySQL format.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm:ss");
        gameCode.setDateTime(formatter.format(LocalDateTime.now()));
    }

    // Variables to save cords.
    private static String c1;
    private static int c1x, c1y;
    private static String c2;
    private static int c2x, c2y;


    private static int score;
    private static int lives;
    private static int cardsDown;
    private static String dateTime;

    public static int getScore() {
        return score;
    }

    public static void setScore(int newScore) {
        score = newScore;
    }

    public static int getLives() {
        return lives;
    }

    public static void setLives(int newLives) {
        lives = newLives;
    }
    
    public static int getCardsDown() {
        return cardsDown;
    }
    
    public static void setCardsDown(int newCardsDown) {
        cardsDown = newCardsDown;
    }

    public static String getDateTime() {
        return dateTime;
    }

    public static void setDateTime(String newDateTime) {
        dateTime = newDateTime;
    }
}