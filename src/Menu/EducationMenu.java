package Menu;

import Utilities.Util;
import futuretechschool.domain.Education;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EducationMenu {

    static Scanner sc = new Scanner(System.in);
    static List<MenuOption> menuEducation = new ArrayList<>();

    public EducationMenu() {
        educationMenu();
    }

    private static void educationMenu() {
        menuEducation.clear();
        boolean run = true;
        menuEducation.add(new MenuOption("0) Back", () -> System.out.println("")));
        menuEducation.add(new MenuOption("1) Create Education", () -> Util.educationDAO.createEducation(createEducation())));
        menuEducation.add(new MenuOption("2) Read Education", () -> readEducation()));
        menuEducation.add(new MenuOption("3) Update Education", () -> Util.educationDAO.updateEducation(updateEducation())));
        menuEducation.add(new MenuOption("4) Delete Education", () -> Util.educationDAO.deleteEducation(Util.readId())));
        menuEducation.add(new MenuOption("5) Add Course To Education", () -> addCourseToEducation()));
        menuEducation.add(new MenuOption("6) List all Educations", () -> Util.printList(Util.educationDAO.readAllEducations())));
        while (run) {
            System.out.println("--EDUCATION MENU--");
            menuEducation.forEach((menuOption) -> {
                System.out.println(menuOption.getString());
            });
            System.out.print("Input: ");
            int input = Util.readNumber();
            if (input == 0) {
                run = false;
            } else {
                try {
                    menuEducation.get(input).getMenu().menuMethod();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Invalid input - Type a number between 0-" + (menuEducation.size() - 1));
                }
            }
        }
    }

    private static Education createEducation() {
        Education education = new Education();
        System.out.print("Name: ");
        String name = sc.nextLine();
        education.setName(name);
        if (!name.equals("")) {
            return education;
        } else {
            System.out.println("Must input proper name.");
        }
        return null;
    }

    private static Education updateEducation() {
        System.out.print("ID of education to Update(0 to cancel): ");
        int id = Util.readNumber();
        if (id == 0) {
            return null;
        }
        Education education = Util.educationDAO.readEducation(id);
        if (education == null) {
            return null;
        } else {
            System.out.println("Current name: '" + education.getName() + "'. Leave 'New Name' empty to skip.");
            System.out.print("New Name: ");
            String newName = sc.nextLine();
            if (!newName.equals("")) {
                education.setName(newName);
            }
            return education;
        }
    }

    private static void addCourseToEducation() {
        Util.printList(Util.courseDAO.readAllCourses());

        System.out.print("Course ID: ");
        int courseID = Util.readNumber();

        System.out.println(Util.educationDAO.readAllEducations());
        System.out.print("Add course #" + courseID + " to Education ID: ");
        int educationID = Util.readNumber();
        Education education = Util.educationDAO.readEducation(educationID);

        education.addCourse(Util.courseDAO.readCourse(courseID));
        Util.educationDAO.updateEducation(education);
    }

    private static void readEducation() {
        Education education = Util.educationDAO.readEducation(Util.readId());
        if (education != null) {
            System.out.println(education);
        }
    }
}
