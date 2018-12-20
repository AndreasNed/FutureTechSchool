
package DAO;

import futuretechschool.domain.Education;
import java.util.List;

public interface EducationDAO {
    
    void createEducation(Education education);
    Education readEducation(int id);
    void updateEducation(Education education);
    void deleteEducation (int id);
    
    List<Education> readAllEducations();

}
