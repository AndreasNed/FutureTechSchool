package Menu;

import Utilities.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    static Scanner sc = new Scanner(System.in);
    static List<MenuOption> menuMain = new ArrayList<>();

    public static void menu() {
        menuMain.clear();
        menuMain.add(new MenuOption("0) Exit", () -> System.exit(1)));
        menuMain.add(new MenuOption("1) Student Menu", () -> new StudentMenu()));
        menuMain.add(new MenuOption("2) Teacher Menu", () -> new TeacherMenu()));
        menuMain.add(new MenuOption("3) Course Menu", () -> new CourseMenu()));
        menuMain.add(new MenuOption("4) Education Menu", () -> new EducationMenu()));
        menuMain.add(new MenuOption("5) Grade Menu", () -> new GradeMenu()));
        
        while (true) {
            System.out.println("--MAIN MENU--");
            menuMain.forEach((menuOption) -> {
                System.out.println(menuOption.getString());
            });
            System.out.print("Input: ");
            int input = Util.readNumber();
            try {
                menuMain.get(input).getMenu().menuMethod();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Invalid input - Type a number between 0-" + (menuMain.size() - 1));
            }
        }
    }
}
