
package DAO;

import futuretechschool.domain.Teacher;
import java.util.List;

public interface TeacherDAO {
    
    //TEACHER
    void createTeacher(Teacher teacher);
    Teacher readTeacher(int id);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int id);
    List<Teacher> readAllTeachers();
    
}
