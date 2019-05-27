
package model.dao;

import entities.Interview;
import java.util.List;


public interface InterviewDao {
    
    public void saveInterview(Interview interview);
    public void updateInterview(Interview interview);
    public void deleteInterview(Interview interview);
    public int getMaxId();
    public Interview getInterview(String id);
    public List<Interview> getInterviewList();
    
}
