package main_menu;

public class Animations {
    public static void welcomeMessage() throws InterruptedException {
        String[] welcome = {
            " ",
            "              _",
            "__      _____| | ___ ___  _ __ ___   ___",
            "\\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\",
            " \\ V  V /  __/ | (_| (_) | | | | | |  __/",
            "  \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|",
            " "
        };
        for (String a : welcome) {
            System.out.println(a);
        }
        Thread.sleep(500);
    }
    public static void gameMessage() throws InterruptedException {
        

        String[] memory = {
            " _ __ ___   ___ _ __ ___   ___  _ __ _   _",
            "| '_ ` _ \\ / _ \\ '_ ` _ \\ / _ \\| '__| | | |",
            "| | | | | |  __/ | | | | | (_) | |  | |_| | ",
            "|_| |_| |_|\\___|_| |_| |_|\\___/|_|   \\__, |",
            "                                     |___/"
        };
        for (String b : memory) {
            System.out.println(b);
        }
        Thread.sleep(1500);
        String[] impossible = {
            "              _                               _ _     _",
            "             (_)_ __ ___  _ __   ___  ___ ___(_) |__ | | ___",
            "             | | '_ ` _ \\| '_ \\ / _ \\/ __/ __| | '_ \\| |/ _ \\",
            "             | | | | | | | |_) | (_) \\__ \\__ \\ | |_) | |  __/ ",
            "             |_|_| |_| |_| .__/ \\___/|___/___/_|_.__/|_|\\___|",
            "                         |_|",
            " "
        };
        for (String c : impossible) {
            System.out.println(c);
        }
        Thread.sleep(1500);
    }

    public static void menuExeption() {
        String[] error = {
            "          _   ___   _ ",
            "         | | |__ \\ | |",
            "         | |   / / | |",
            "         |_|  |_|  |_|",
            "         (_)  (_)  (_)",
            " ",
            ">> Pls enter an option number <<"
        };
        Animations.border();
        for (String i : error) {
            System.out.println(i);
        }
        Menu.enterReturn();
    }

    public static void border() {
        System.out.println("==============================" + "\r\n");
    }
}
