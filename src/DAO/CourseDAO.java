package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Teacher;

public interface CourseDAO {


    //COURSE
    void createCourse(Course course);
    Course readCourse(int id);
    void updateCourse(Course course, int id);
    void deleteCourse(int id);
    void addTeacherToCourse(int teacherId, int courseId);
    void removeTeacherFromCourse(int teacherId, int courseId);

}
