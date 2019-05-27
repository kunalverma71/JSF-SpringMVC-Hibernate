/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import entities.Job_Profile;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("jobDao")
public class JobDaoImpl implements JobDao{
    
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
    public void saveJob(Job_Profile job) {
    
        getSession().saveOrUpdate(job);
    
    }

    @Override
    public void deleteEmployee(Job_Profile job) {
        
        getSession().delete(job);
    }

    @Override
    public Job_Profile getJob(String id) {
        
        return (Job_Profile)getSession().createQuery("from Job_Profile where job_id=:id").setString("id", id).uniqueResult();
    }

    @Override
    public List<Job_Profile> getJobsList() {
        
        return getSession().createQuery("from Job_Profile").list();
    
    }
    
    @Override
    public List<Job_Profile> getJobsListC(String name) {
        
        return getSession().createQuery("from Job_Profile where comp_name=:name").setString("name", name).list();
    
    }

    @Override
    public void updateJob(Job_Profile job) {
        
       System.out.println("Job Updating");
      
       getSession().update(job);
       
       System.out.println("Job Updated");
    }

    
    
}
