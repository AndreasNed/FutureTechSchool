
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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Util {
    
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
        } catch (InputMismatchException e) {
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
