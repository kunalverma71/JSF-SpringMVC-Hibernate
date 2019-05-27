
package model.service;

import entities.Applicant;
import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;
import model.dao.ApplicantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("applicantService")
@Transactional
public class ApplicantServiceImpl implements ApplicantService,Serializable {
    
    @Autowired
    ApplicantDao applicantDao;
    
    public ApplicantDao getEmployeeDao()
    {
        return applicantDao;
    }
    
    public void setEmployeeDao(ApplicantDao applicantDao)
    {
        this.applicantDao = applicantDao;
    }

    @Override
    public void saveApplicant(Applicant applicant) {
    
        applicantDao.saveApplicant(applicant);
    }

    @Override
    public void deleteApplicant(Applicant applicant) {
        
        applicantDao.deleteApplicant(applicant);
    }

    @Override
    public Applicant getApplicant(String email) {
        
        System.out.println("Get Applicant : "+email);
        
        return applicantDao.getApplicant(email);
    }

    @Override
    public List<Applicant> getApplicantsList() {
       
        return applicantDao.getApplicantsList();
    }
    @Override
    public List<Applicant> getInterestedApplicantsList(String name) {
       
        return applicantDao.getInterestedApplicantsList(name);
    }
    
    @Override
    public boolean checkLogin(String email, String password) {
    
        return applicantDao.checkLogin(email,password);
    }
}
