
package controller;

import entities.Applicant;
import entities.Applicant_Qualification;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import model.service.ApplicantService;
import model.service.QualificationService;
import org.springframework.dao.DataIntegrityViolationException;


@ManagedBean
@ViewScoped
public class QualificationController {

    @ManagedProperty(value="#{qualificationService}")
    private QualificationService qualificationService;
    
    @ManagedProperty(value="#{applicantService}")
    private ApplicantService applicantService;

    public QualificationService getQualificationService() {
        return qualificationService;
    }

    public void setQualificationService(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    public ApplicantService getApplicantService() {
        return applicantService;
    }

    public void setApplicantService(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }
    
    private int qly_id;
    private String email;
    private String graduation;
    private String passing_year;
    private String board;
    private String skill;
    
    private List<Applicant_Qualification> applicantsQualificationList;
    
    public int getQly_id() {
        return qly_id;
    }

    public List<Applicant_Qualification> getApplicantsQualificationList() {
        return applicantsQualificationList;
    }

    public void setApplicantsQualificationList(List<Applicant_Qualification> applicantsQualificationList) {
        this.applicantsQualificationList = applicantsQualificationList;
    }

    public void setQly_id(int qly_id) {
        this.qly_id = qly_id;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getPassing_year() {
        return passing_year;
    }

    public void setPassing_year(String passing_year) {
        this.passing_year = passing_year;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    
    
    public String save()
    {
        System.out.println("Save Qualification"); 
       
        try{
            Applicant_Qualification old_qualification = qualificationService.getApplicant_Qualification(email);
            qly_id=old_qualification.getQly_id();
        
        }
        catch(Exception e)
        {
            return null;
        }
        System.out.println("Save Qualification qly_id: "+qly_id); 
        
        Applicant_Qualification qualification = new Applicant_Qualification();
        qualification.setQly_id(qly_id);
        qualification.setEmail(email);
        qualification.setBoard(board);
        qualification.setGraduation(graduation);
        qualification.setPassing_year(passing_year);
        qualification.setSkill(skill);
        
        try
        {
            qualificationService.updateQualification(qualification);
        }
        catch(DataIntegrityViolationException e)
        {
             return null;
        }
      applicantsQualificationList.add(qualification);
      return "index";
        
        
    }
    
    
    public QualificationController() {
    }
    
    
    @PostConstruct
    public void init()
    {
        applicantsQualificationList = qualificationService.getApplicantsQualificationList();
    }
    
}
