package DAO;

import futuretechschool.domain.Grade;
import futuretechschool.domain.Student;
import java.util.List;

public interface GradeDAO {
    
    void createGrade(Grade grade);
    Grade readGrade(int id);
    void updateGrade(Grade grade);
    void deleteGrade(int id);
   
    List<Grade> readGradesByStudent(Student student);
    List<Grade> readAllGrade();
}
