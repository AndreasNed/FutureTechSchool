package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Student;
import java.util.List;


public interface StudentDAO {

    public void createStudent(Student student);
    public Student readStudent(int id);
    public void updateStudent(Student student);
    public void deleteStudent(int id);
    List<Student> readAllStudents();
    List<Course> readAllCourses(Student student);
  
}
