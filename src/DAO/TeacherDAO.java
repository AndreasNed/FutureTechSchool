
package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Teacher;
import java.util.List;

public interface TeacherDAO {
    
    void createTeacher(Teacher teacher);
    Teacher readTeacher(int id);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int id);
    
    List<Teacher> readAllTeachers();
    List<Course> readAllCourses(Teacher teacher);
    
}
