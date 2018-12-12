package DAO;

import futuretechschool.domain.Course;

public interface CourseDAO {


    //COURSE
    void createCourse(Course course);
    Course readCourse(int id);
    void updateCourse(Course course);
    void deleteCourse(Course course);
    void addTeacherToCourse(int teacherId, int courseId);
    void removeTeacherFromCourse(int teacherId, int courseId);

}
