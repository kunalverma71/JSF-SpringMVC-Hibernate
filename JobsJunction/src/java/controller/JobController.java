
package controller;

import entities.Job_Profile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.service.JobService;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean
@RequestScoped
public class JobController {

    @ManagedProperty(value="#{jobService}")
    private JobService jobService;

    public JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }
    
    private String job_id;
    private String comp_name;
    private String job_location;
    private String job_description;
    private String requirement;
    private String skills;
    private Date last_date;
    private String salary;
    private int no_of_vacancies;
    private static Job_Profile job;
    
    private List<Job_Profile> jobsList;
    private List<Job_Profile> jobsListC;
    
    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

   
    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Date getLast_date() {
        return last_date;
    }

    public void setLast_date(Date last_date) {
        this.last_date = last_date;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getNo_of_vacancies() {
        return no_of_vacancies;
    }

    public void setNo_of_vacancies(int no_of_vacancies) {
        this.no_of_vacancies = no_of_vacancies;
    }

    public List<Job_Profile> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Job_Profile> jobsList) {
        this.jobsList = jobsList;
    }
    public List<Job_Profile> getJobsListC() {
        return jobsListC;
    }

    public void setJobsListC(List<Job_Profile> jobsListC) {
        this.jobsListC = jobsListC;
    }

    public Job_Profile getJob() {
        return job;
    }

    public void setJob(Job_Profile job) {
        this.job = job;
    }

    
    
    public JobController() {
    }
    
    
    public String save()
    {
        Job_Profile job_profile = new Job_Profile();
        job_profile.setJob_id(job_id);
        job_profile.setComp_name(comp_name);
        job_profile.setJob_description(job_description);
        job_profile.setLast_date(last_date);
        job_profile.setJob_location(job_location);
        job_profile.setNo_of_vacancies(no_of_vacancies);
        job_profile.setRequirement(requirement);
        job_profile.setSalary(salary);
        job_profile.setSkills(skills);
        
        try 
        {
            jobService.saveJob(job_profile);
        }
        catch(DataIntegrityViolationException e)
        {
            createMessage("Job Exists", FacesMessage.SEVERITY_ERROR);
            return null;
        }
        jobsList.add(job_profile);
        createMessage("Details Saved", FacesMessage.SEVERITY_INFO);
        return null;
        
    }
    
    public String showJob(String job_id)
    {
        System.out.println("update Job");
        
        try{
            job = jobService.getJob(job_id);
            System.out.println("BeforeUpdateJob : "+job_id);
            System.out.println("UpdateJob : "+job.getNo_of_vacancies());
        return "updatejob";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }
    
    
    public String updateJob()
    {
        Job_Profile job_profile = new Job_Profile();
        job_profile.setJob_id(job.getJob_id());
        job_profile.setComp_name(job.getComp_name());
        job_profile.setJob_description(job.getJob_description());
        job_profile.setLast_date(job.getLast_date());
        job_profile.setJob_location(job.getJob_location());
        job_profile.setNo_of_vacancies(job.getNo_of_vacancies());
        job_profile.setRequirement(job.getRequirement());
        job_profile.setSalary(job.getSalary());
        job_profile.setSkills(job.getSkills());
        
        try
        {
            System.out.println(job.getJob_location());
            jobService.updateJob(job_profile);
            System.out.println(job.getJob_id());
            System.out.println("BeforeUpdateJob : "+job.getJob_id());
            System.out.println("UpdateJob : "+job.getNo_of_vacancies());
            jobsList = jobService.getJobsList();
            return "jobs_list_company";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
  
    public void createMessage(String text,FacesMessage.Severity severity)
    {
            FacesMessage message = new FacesMessage();
            message.setSeverity(severity);
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    @PostConstruct
    public void init()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
       HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
       jobsListC = jobService.getJobsListC((String)session.getAttribute("name"));
        jobsList = jobService.getJobsList();
    }
}
