import utils.Printer;

public class Main {
    public static void main(String[] args) {
        Printer.printFromFile("./src/res/banner.txt");
        Printer.printCountdown(3, "\nStarting");
        Printer.clearScreen();
    }
}
