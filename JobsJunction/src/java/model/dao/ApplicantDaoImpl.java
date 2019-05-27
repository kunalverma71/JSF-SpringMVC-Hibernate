
package model.dao;

import entities.Applicant;
import entities.Interview;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("applicantDao")
public class ApplicantDaoImpl implements ApplicantDao,Serializable{
    
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
    public void saveApplicant(Applicant applicant) {
       
        getSession().save(applicant);
        
    }

    @Override
    public void deleteApplicant(Applicant applicant) {
    
        getSession().delete(applicant);
    }

    @Override
    public Applicant getApplicant(String email) {
        
        return (Applicant)getSession().createQuery("from Applicant where email=:email").setString("email", email).uniqueResult();
    }

    @Override
    public List<Applicant> getApplicantsList() {
        
        return getSession().createQuery("from Applicant").list();
    }
    @Override
    public List<Applicant> getInterestedApplicantsList(String name) {
        
        List<Interview> appList =  getSession().createQuery("from Interview where comp_name=:name").setString("name",name).list();
        Applicant applicantDetails;
        Set<Interview> uniqueApplicants = new HashSet<Interview>(appList);
        
        List<Applicant> applicantslist = new LinkedList<Applicant>();
        for(Interview app : uniqueApplicants)
        {
            System.out.println("Interested : "+app.getApplicant_name());
            applicantDetails = (Applicant)getSession().createQuery("from Applicant where email=:email").setString("email",app.getApplicant_name()).uniqueResult();
            
            applicantslist.add(applicantDetails);
        }
        return applicantslist;
    }

    @Override
    public boolean checkLogin(String email, String password) {
    
        if( getSession().createQuery("from Applicant where email=:email and password=:password").setString("email", email).setString("password", password).uniqueResult()!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
