package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Teacher;

public interface DAOInterface {

    //TEACHER
    void createTeacher(Teacher teacher);
    Teacher readTeacher(int id);
    void updateTeacher(Teacher teacher, int id);
    void deleteTeacher(int id);
    //COURSE
    void createCourse(Course course);
    Course readCourse(int id);
    void updateCourse(Course course, int id);
    void deleteCourse(int id);
    void addTeacherToCourse(int teacherId, int courseId);
    void removeTeacherFromCourse(int teacherId, int courseId);

}
