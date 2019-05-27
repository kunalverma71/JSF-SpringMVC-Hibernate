
package model.dao;

import entities.Job_Profile;
import java.util.List;




public interface JobDao {
    
    public void saveJob(Job_Profile job);
    public void updateJob(Job_Profile job);
    public void deleteEmployee(Job_Profile job);
    public Job_Profile getJob(String id);
    public List<Job_Profile> getJobsList();
    public List<Job_Profile> getJobsListC(String name);
    
}
