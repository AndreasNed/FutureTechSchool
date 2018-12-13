package DAO;

import futuretechschool.domain.Student;


public interface StudentDAO {

    public void createStudent(Student student);
    public Student readStudent(int id);
    public void updateStudent(Student student);
    public void deleteStudent(int id);
    
//    Dessa ska inte behövas.
//    public void addToCourse(int studentId, int courseId);
//    public void removeFromCourse(int studentId, int courseId);
    
}
