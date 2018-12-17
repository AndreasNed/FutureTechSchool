/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import futuretechschool.domain.Grade;
import futuretechschool.domain.Student;
import java.util.List;

/**
 *
 * @author GasCan
 */
public interface GradeDAO {
    void createGrade(Grade grade);
    Grade readGrade(int id);
    void updateGrade(Grade grade);
    void deleteGrade(int id);
   
    List<Grade> readGradesByStudent(Student student);
    List<Grade> readAllGrade();
}
