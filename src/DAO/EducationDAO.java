/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import futuretechschool.domain.Education;
import java.util.List;

/**
 *
 * @author GasCan
 */
public interface EducationDAO {
void createEducation(Education education);
Education readEducation(int id);
void updateEducation(Education education);
void deleteEducation (int id);

List<Education> readAllEducations();

}
