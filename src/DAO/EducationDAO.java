/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import futuretechschool.domain.Education;

/**
 *
 * @author GasCan
 */
public interface EducationDAO {
    
void createEducation(Education education);
Education readEducation(int id);
void updateEducation(Education education);
void deleteEducation (int id);

void addCourseToEducation(int educationId, int courseId);
void removeCourseFromEducation(int educationId, int courseId);
void addStudentToEducation (int studentId, int educationId);
void removeStudentFromEducation (int studentId, int educationId);

}
