/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import DAO.CourseDAO;
import DAO.CourseDAOImpl;
import DAO.EducationDAO;
import DAO.EducationDAOImpl;
import DAO.GradeDAO;
import DAO.GradeDAOImpl;
import DAO.StudentDAO;
import DAO.StudentDAOImpl;
import DAO.TeacherDAO;
import DAO.TeacherDAOImpl;
import static Menu.MainMenu.readNumber;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author GasCan
 */
public class Utilities {
    
    public static Scanner sc = new Scanner(System.in);
        
    public static EducationDAO educationDAO = new EducationDAOImpl();
    public static CourseDAO courseDAO = new CourseDAOImpl();
    public static StudentDAO studentDAO = new StudentDAOImpl();
    public static TeacherDAO teacherDAO = new TeacherDAOImpl();
    public static GradeDAO gradeDAO = new GradeDAOImpl();
    
    public static <T> void printList(List<T> list) {
        System.out.println("__________________________________________________________________");
        list.stream().forEach(System.out::println);
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }
    public static int readNumber() {
        String strNum;
        int num;
        try {
            strNum = sc.nextLine();
            num = Integer.parseInt(strNum);
        } catch (Exception e) {
            num = -1;
        }
        return num;
    }
    public static int readId() {
        System.out.print("Enter ID: ");
        int i = readNumber();
        return i;
    }
}
