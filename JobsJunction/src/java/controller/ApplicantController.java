
package controller;

import entities.Applicant;
import entities.Applicant_Qualification;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import model.service.ApplicantService;
import model.service.QualificationService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataIntegrityViolationException;


@ManagedBean
@ViewScoped
public class ApplicantController implements Serializable {

    @ManagedProperty(value="#{applicantService}")
    private ApplicantService applicantService;
    
    @ManagedProperty(value="#{qualificationService}")
    private QualificationService qualificationService;

    
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    
    public QualificationService getQualificationService() {
        return qualificationService;
    }

    public void setQualificationService(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    public ApplicantService getEmployeeService() {
        return applicantService;
    }

    public void setApplicantService(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }
    
   @Transient
    private UIComponent registerButton;
    @Id
    private int reg_id;
    private String email;
    private String name;
    private String contact;
    private String address;
    private Date date_of_birth;
    private String gender;
    private String password;
    private static Applicant applicant;
    private static Applicant_Qualification applicantQualification;
    
    private List<Applicant> applicantsList;
    private List<Applicant> interestedApplicantsList;

    public Applicant_Qualification getApplicantQualification() {
        return applicantQualification;
    }

    public void setApplicantQualification(Applicant_Qualification applicantQualification) {
        this.applicantQualification = applicantQualification;
    }

    public List<Applicant> getInterestedApplicantsList() {
        return interestedApplicantsList;
    }

    public void setInterestedApplicantsList(List<Applicant> interestedApplicantsList) {
        this.interestedApplicantsList = interestedApplicantsList;
    }

    public  Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
    
   
    
    public UIComponent getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(UIComponent registerButton) {
        this.registerButton = registerButton;
    }

    public int getReg_id() {
        return reg_id;
    }

    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
   

    public List<Applicant> getApplicantsList() {
        return applicantsList;
    }

    public void setApplicantsList(List<Applicant> applicantsList) {
        this.applicantsList = applicantsList;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public ApplicantController() {
    }
    
    
    
    
    public String save()
    {
        System.out.println("SaveUser");
        Applicant applicant = new Applicant();
        Applicant_Qualification qualification = new Applicant_Qualification();
        qualification.setEmail(email);
        applicant.setName(name);
        applicant.setEmail(email);
        applicant.setContact(contact);
        applicant.setAddress(address);
        applicant.setDate_of_birth(date_of_birth);
        applicant.setGender(gender);
        applicant.setPassword(password);
        
        try
        {
            applicantService.saveApplicant(applicant);
            qualificationService.saveQualification(qualification);
        }
        catch(DataIntegrityViolationException e)
        {
            createMessage("Applicant Exists", FacesMessage.SEVERITY_ERROR);
            return null;
        }
        applicantsList.add(applicant);
        createMessage("Details Saved", FacesMessage.SEVERITY_INFO);
        return "updateQualification";
    }
    
    
    public String login()
    {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        try{
            if(applicantService.checkLogin(email,password))
            {
                String name=applicantService.getApplicant(email).getName();
                
                session.setAttribute("email", email);
                session.setAttribute("name", name);
                session.setAttribute("loggedin", true);
                session.setAttribute("role", "A");
                
                return "index";
            }
            else
                return null;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
     public String logout() {
         try{
             System.out.println("Logout");
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                return "index";
         }
         catch(Exception e)
         {
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
      
        applicantsList = applicantService.getApplicantsList();
        interestedApplicantsList = applicantService.getInterestedApplicantsList((String)session.getAttribute("name"));
    }
    
    public String showApplicant(String email)
    {
        try{
            applicant  = applicantService.getApplicant(email);
            applicantQualification = qualificationService.getApplicant_Qualification(email);
            System.out.println(applicant.getEmail());
        return "showapplicant";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
