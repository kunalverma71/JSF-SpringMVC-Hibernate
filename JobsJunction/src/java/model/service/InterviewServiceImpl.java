
package model.service;

import entities.Interview;
import java.util.List;
import javax.transaction.Transactional;
import model.dao.InterviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("interviewService")
@Transactional
public class InterviewServiceImpl implements InterviewService {
    
    @Autowired
    InterviewDao interviewDao;

    public InterviewDao getInterviewDao() {
        return interviewDao;
    }

    public void setInterviewDao(InterviewDao interviewDao) {
        this.interviewDao = interviewDao;
    }

    @Override
    public void saveInterview(Interview interview) {
    
        getInterviewDao().saveInterview(interview);
    }

    @Override
    public void updateInterview(Interview interview) {
    
        getInterviewDao().updateInterview(interview);
    }

    @Override
    public void deleteInterview(Interview interview) {
    
        getInterviewDao().deleteInterview(interview);
    }

    @Override
    public Interview getInterview(String id) {
    
        return getInterviewDao().getInterview(id);
    }

    @Override
    public List<Interview> getInterviewList() {
    
        return getInterviewDao().getInterviewList();
    }

    @Override
    public int getMaxId() {
        
        return getInterviewDao().getMaxId();
    }
    
    
    
}
