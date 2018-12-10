package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Student;
import java.util.List;

public interface StudentDAO {

    public void createStudent(Student student);

    public Student readStudent(int id);

    public void updateStudent(Student newStudentInfo, int id);

    public void deleteStudent(int id);

    public void addToCourse(int studentId, int courseId);

    public void removeFromCourse(int studentId, int courseId);
    
}
