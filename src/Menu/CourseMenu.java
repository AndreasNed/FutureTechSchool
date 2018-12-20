package Menu;

import Utilities.Util;
import futuretechschool.domain.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseMenu {

    static List<MenuOption> menuCourse = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public CourseMenu() {
        courseMenu();
    }

    private static void courseMenu() {
        menuCourse.clear();
        boolean run = true;
        menuCourse.add(new MenuOption("0) Back", () -> System.out.println("")));
        menuCourse.add(new MenuOption("1) Create New Course", () -> Util.courseDAO.createCourse(createCourse())));
        menuCourse.add(new MenuOption("2) Read Course", () -> readCourse()));
        menuCourse.add(new MenuOption("3) Update Course", () -> Util.courseDAO.updateCourse(updateCourse())));
        menuCourse.add(new MenuOption("4) Delete Course", () -> Util.courseDAO.deleteCourse(Util.readId())));
        menuCourse.add(new MenuOption("5) List all Courses", () -> Util.printList(Util.courseDAO.readAllCourses())));
        while (run) {
            System.out.println("--COURSE MENU--");
            menuCourse.forEach((menuOption) -> {
                System.out.println(menuOption.getString());
            });
            System.out.print("Input: ");
            int input = Util.readNumber();
            if (input == 0) {
                run = false;
            } else {
                try {
                    menuCourse.get(input).getMenu().menuMethod();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Invalid input - Type a number between 0-" + (menuCourse.size() - 1));
                }
            }
        }
    }

    private static Course createCourse() {
        Course course = new Course();
        System.out.print("Name: ");
        String name = sc.nextLine();
        course.setName(name);
        return course;
    }

    private static Course updateCourse() {
        System.out.print("ID of course to Update(0 to cancel): ");
        int id = Util.readNumber();
        if (id == 0) {
            return null;
        }
        Course course = Util.courseDAO.readCourse(id);
        if (course == null) {
            return null;
        } else {
            System.out.println("Current name: '" + course.getName() + "'. Leave 'New Name' empty to skip.");
            System.out.print("New Name: ");
            String newName = sc.nextLine();
            if (!newName.equals("")) {
                course.setName(newName);
            }
        }
        return course;
    }

    private static void readCourse() {
        Course course = Util.courseDAO.readCourse(Util.readId());
        if (course != null) {
            System.out.println(course);
        }
    }
}
