
package model.dao;

import entities.Applicant;
import entities.Applicant_Qualification;
import java.util.List;


public interface QualificationDao {
    
    public void saveQualifiction(Applicant_Qualification qualification);
    public void updateQualification(Applicant_Qualification qualification);
    public List<Applicant_Qualification> getApplicantsQualificationList();
    public Applicant_Qualification getApplicant_Qualification(String email);
}
