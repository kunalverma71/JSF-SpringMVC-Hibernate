
package model.dao;

import entities.Interview;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("interviewDao")
public class InterviewDaoImpl implements InterviewDao{
    
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
    public void saveInterview(Interview interview) {
    
        getSession().save(interview);
    }

    @Override
    public void updateInterview(Interview interview) {
       
        getSession().update(interview);
    }

    @Override
    public void deleteInterview(Interview interview) {
        
        getSession().delete(interview);
    }

    @Override
    public Interview getInterview(String id) {
    
        return (Interview)getSession().createQuery("from Interview where interview_id=:id").setString("id", id).uniqueResult();
    }

    @Override
    public List<Interview> getInterviewList() {
    
        return getSession().createQuery("from Interview").list();
    }

    @Override
    public int getMaxId() {
       
    
        return (Integer)getSession().createQuery("from Interview where interview_id=:MAX(interview_id)").uniqueResult();
                
    }
    
    
}
