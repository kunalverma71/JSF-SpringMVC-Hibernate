
package model.service;

import entities.Job_Profile;
import java.util.List;
import javax.transaction.Transactional;
import model.dao.JobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service("jobService")
@Transactional
public class JobServiceImpl implements JobService{
    
    @Autowired
    JobDao jobDao;

    public JobDao getJobDao() {
        return jobDao;
    }

    public void setJobDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Override
    public void saveJob(Job_Profile job) {
        
       jobDao.saveJob(job);
    }

    @Override
    public void deleteEmployee(Job_Profile job) {
        
        jobDao.deleteEmployee(job);
    }

    @Override
    public Job_Profile getJob(String id) {
        
        return jobDao.getJob(id);
    }

    @Override
    public List<Job_Profile> getJobsList() {
        
        return jobDao.getJobsList();
    
    }
    
    @Override
    public List<Job_Profile> getJobsListC(String name) {
        
        return jobDao.getJobsListC(name);
    
    }

    @Override
    public void updateJob(Job_Profile job) {
    
        jobDao.updateJob(job);
    }
    
    
    
}
