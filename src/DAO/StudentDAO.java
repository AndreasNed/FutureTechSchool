package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Student;
import java.util.List;


public interface StudentDAO {

    void createStudent(Student student);
    Student readStudent(int id);
    void updateStudent(Student student);
    void deleteStudent(int id);
    int getTotalPoints(Student student);
    
    List<Student> readAllStudents();
    List<Course> readAllCourses(Student student);
  
}
