/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import DAO.CourseDAO;
import DAO.CourseDAOImpl;
import DAO.EducationDAO;
import DAO.EducationDAOImpl;
import DAO.StudentDAO;
import DAO.StudentDAOImpl;
import static Menu.MainMenu.menuEducation;
import static Menu.MainMenu.printList;
import Utilities.Utilities;
import futuretechschool.domain.Education;
import futuretechschool.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author GasCan
 */
public class EducationMenu {
    
    public EducationMenu(){
        educationMenu();
    }
    
    private static void educationMenu() {
        menuEducation.clear();
        boolean run = true;
        menuEducation.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuEducation.add(new MenuOption("1) Create Education", () -> Utilities.educationDAO.createEducation(createEducation())));
        menuEducation.add(new MenuOption("2) Read Education", () -> System.out.println(Utilities.educationDAO.readEducation(Utilities.readId()))));
        menuEducation.add(new MenuOption("3) Update Education", () -> Utilities.educationDAO.updateEducation(updateEducation())));
        menuEducation.add(new MenuOption("4) Delete Education", () -> Utilities.educationDAO.deleteEducation(Utilities.readId())));
        menuEducation.add(new MenuOption("5) Add Course To Education", () -> addCourseToEducation()));
        menuEducation.add(new MenuOption("6) List all Educations", () -> Utilities.printList(Utilities.educationDAO.readAllEducations())));
        while (run) {
            System.out.println("--EDUCATION MENU--");
            for (MenuOption menuOption : menuEducation) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = Utilities.readNumber();
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
        String name = Utilities.sc.nextLine();
        education.setName(name);
        return education;
    }

    private static Education updateEducation() {

        System.out.print("ID of education to Update(0 to cancel): "); //TODOD        
        int id = Utilities.readNumber();
        if (id == 0) {
            return null;
        }
        Education education = Utilities.educationDAO.readEducation(id);
        System.out.println("Current name: " + education.getName() + "Leave 'New Name' empty to skip");
        System.out.print("New Name: ");
        String newName = Utilities.sc.nextLine();
        if (!newName.equals("")) {
            education.setName(newName);
        }
        return education;

    }
    
    private static void addStudentToEducation() {
        List<Student> students = new ArrayList<>();
        List<Education> educations = new ArrayList<>();
        students = Utilities.studentDAO.readAllStudents();
        educations = Utilities.educationDAO.readAllEducations();
        System.out.println("List of Students: ");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.print("Select Student ID: ");
        int studentID = Utilities.readNumber();
        Student student = Utilities.studentDAO.readStudent(studentID);
        if (student != null) {

            System.out.println("Available Educations: ");
            for (Education education : educations) {
                System.out.println(education);
            }

            System.out.print("Add Student " + student.getName() + " to Education: ");
            int educationID = Utilities.readNumber();
            Education education = Utilities.educationDAO.readEducation(educationID);
            if (education != null) {
                student.setEducation(education);
                Utilities.studentDAO.updateStudent(student);
                System.out.println(student.getName() + " added to education " + education.getName());
            } else {
                System.out.println("Invalid Education ID");
            }
        } else {
            System.out.println("Invalid Student ID");
        }
    }
    
    private static void addCourseToEducation() {

        printList(Utilities.courseDAO.readAllCourses());

        System.out.print("Course ID: ");
        int courseID = Utilities.readNumber();

        System.out.println(Utilities.educationDAO.readAllEducations());
        System.out.print("Add course #" + courseID + " to Education ID: ");
        int educationID = Utilities.readNumber();
        Education education = Utilities.educationDAO.readEducation(educationID);

        education.addCourse(Utilities.courseDAO.readCourse(courseID));
        Utilities.educationDAO.updateEducation(education);
    }
}
