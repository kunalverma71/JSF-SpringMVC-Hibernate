
package model.service;


import entities.Applicant;
import entities.Applicant_Qualification;
import java.util.List;
import javax.transaction.Transactional;
import model.dao.QualificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qualificationService")
@Transactional
public class QualificationServiceImpl implements QualificationService{
    
    @Autowired
    QualificationDao qualificationDao;
    
    public QualificationDao getQualificationDao()
    {
        return qualificationDao;
    }
    public void setQualificationDao(QualificationDao qualificationDao)
    {
        this.qualificationDao = qualificationDao;
    }

    @Override
    public void saveQualification(Applicant_Qualification qualification) {
        getQualificationDao().saveQualifiction(qualification);
    }

    @Override
    public void updateQualification(Applicant_Qualification qualification) {
        getQualificationDao().updateQualification(qualification);
    }
    
    @Override
    public Applicant_Qualification getApplicant_Qualification(String email) {
        
        System.out.println("Get Applicant_Qualification reg_id : "+email);
        
        return getQualificationDao().getApplicant_Qualification(email);
    }
    @Override
    public List<Applicant_Qualification> getApplicantsQualificationList() {
       
        return qualificationDao.getApplicantsQualificationList();
    }
}
