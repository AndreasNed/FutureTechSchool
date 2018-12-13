
package DAO;

import futuretechschool.domain.Teacher;

public interface TeacherDAO {
    
        //TEACHER
    void createTeacher(Teacher teacher);
    Teacher readTeacher(int id);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int id);
    
}
