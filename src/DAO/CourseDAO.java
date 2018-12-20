package DAO;

import futuretechschool.domain.Course;
import java.util.List;

public interface CourseDAO {

    void createCourse(Course course);
    Course readCourse(int id);
    void updateCourse(Course course);
    void deleteCourse(int id);
    
    List<Course> readAllCourses();
}
