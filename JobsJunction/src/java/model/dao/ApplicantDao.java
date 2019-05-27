
package model.dao;

import entities.Applicant;
import java.util.List;


public interface ApplicantDao {
    
    public void saveApplicant(Applicant applicant);
    public void deleteApplicant(Applicant applicant);
    public Applicant getApplicant(String email);
    public List<Applicant> getApplicantsList();
    public List<Applicant> getInterestedApplicantsList(String name);
    public boolean checkLogin(String email,String password);
}
