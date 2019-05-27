
package controller;

import entities.Interview;
import entities.Job_Profile;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.service.InterviewService;
import model.service.JobService;


@ManagedBean
@RequestScoped
public class InterviewController {

    @ManagedProperty(value="#{interviewService}")
    private InterviewService interviewService;

    @ManagedProperty(value="#{jobService}")
    private JobService jobService;

    public InterviewService getInterviewService() {
        return interviewService;
    }

    public void setInterviewService(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    public JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }
    
    private String interview_id;
    private String comp_name;
    private String applicant_name;
    private Date interview_date;
    private String result;
    
    private Job_Profile job;

    private List<Interview> interviewList;
    
    public String getInterview_id() {
        return interview_id;
    }

    public void setInterview_id(String interview_id) {
        this.interview_id = interview_id;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public Date getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(Date interview_date) {
        this.interview_date = interview_date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Interview> getInterviewList() {
        return interviewList;
    }

    public void setInterviewList(List<Interview> interviewList) {
        this.interviewList = interviewList;
    }

    public Job_Profile getJob() {
        return job;
    }

    public void setJob(Job_Profile job) {
        this.job = job;
    }
    
    
    
    public InterviewController() {
    }
    
    public String applyJob(String job_id)
    {
        job = jobService.getJob(job_id);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Interview interview = new Interview();
        interview.setApplicant_name((String)session.getAttribute("email"));
        interview.setComp_name(job.getComp_name());
        
        
        try
        {
            interviewService.saveInterview(interview);
            return "index";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    @PostConstruct
    public void init()
    {
        interviewList = interviewService.getInterviewList();
    }
    
    
}
