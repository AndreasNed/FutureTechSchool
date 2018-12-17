/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import DAO.CourseDAO;
import DAO.CourseDAOImpl;
import static Menu.MainMenu.menuCourse;
import Utilities.Utilities;
import futuretechschool.domain.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author GasCan
 */
public class CourseMenu {
  
    static List<MenuOption> menuCourse = new ArrayList<>();
    
    public CourseMenu(){
        courseMenu();
    }
    
    private static void courseMenu() {
        menuCourse.clear();
        boolean run = true;
        menuCourse.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuCourse.add(new MenuOption("1) Create New Course", () -> Utilities.courseDAO.createCourse(createCourse())));
        menuCourse.add(new MenuOption("2) Read Course", () -> System.out.println(Utilities.courseDAO.readCourse(Utilities.readId()))));
        menuCourse.add(new MenuOption("3) Update Course", () -> Utilities.courseDAO.updateCourse(updateCourse())));
        menuCourse.add(new MenuOption("4) Delete Course", () -> Utilities.courseDAO.deleteCourse(Utilities.readId())));
        menuCourse.add(new MenuOption("5) List all Courses", () -> Utilities.printList(Utilities.courseDAO.readAllCourses())));
        while (run) {
            System.out.println("--COURSE MENU--");
            for (MenuOption menuOption : menuCourse) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = Utilities.readNumber();
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
        String name = Utilities.sc.nextLine();
        course.setName(name);
        return course;
    }

    private static Course updateCourse() {
        System.out.print("ID of course to Update(0 to cancel): "); //TODOD        
        int id = Utilities.readNumber();
        if (id == 0) {
            return null;
        }
        Course course = Utilities.courseDAO.readCourse(id);
        System.out.println("Current name: '" + course.getName() + "'. Leave 'New Name' empty to skip");
        System.out.print("New Name: ");
        String newName = Utilities.sc.nextLine();
        if (!newName.equals("")) {
            course.setName(newName);
        }
        return course;
    }
}
