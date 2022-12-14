import utils.Dialog;
import utils.Printer;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Dialog();
        printer.printFromFile("./src/res/banner.txt");
        printer.printCountdown(3, "\nStarting");
        printer.clearScreen();
        printer.printFromFile("./src/res/menu.txt");
    }
}
