
package model.dao;

import entities.Applicant;
import entities.Applicant_Qualification;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("qualificationDao")
public class QualificationDaoImpl implements QualificationDao{
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory= sessionFactory;
    }
    
    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveQualifiction(Applicant_Qualification qualification) {
        getSession().save(qualification);
    }

    @Override
    public void updateQualification(Applicant_Qualification qualification) {
        getSession().update(qualification);
    }

    @Override
    public Applicant_Qualification getApplicant_Qualification(String email) {
        
        Applicant_Qualification applicant_Qualification = (Applicant_Qualification)getSession().createQuery("from Applicant_Qualification where email='"+email+"'").uniqueResult();
        System.out.println("Qualification Dao : "+applicant_Qualification.getQly_id());
        return applicant_Qualification;
    }
    
    @Override
    public List<Applicant_Qualification> getApplicantsQualificationList() {
        
        return getSession().createQuery("from Applicant_Qualification").list();
    }
    
    
}
