
package model.service;

import entities.Interview;
import java.util.List;


public interface InterviewService {
    public void saveInterview(Interview interview);
    public void updateInterview(Interview interview);
    public void deleteInterview(Interview interview);
    public int getMaxId();
    public Interview getInterview(String id);
    public List<Interview> getInterviewList();
}
