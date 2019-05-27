
package controller;

import entities.Company;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.service.CompanyService;
import org.springframework.dao.DataIntegrityViolationException;


@ManagedBean
@RequestScoped
public class CompanyController {

    @ManagedProperty(value="#{companyService}")
    private CompanyService companyService;

    public CompanyService getEmployerService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    
    
    private UIComponent registerButton;
   
     
    private List<Company> companiesList;
   
  
    private int comp_id;
    private String email;
    private String name;
    private String contact;
    private String address;
    private String profile;
    private String password;

    public UIComponent getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(UIComponent registerButton) {
        this.registerButton = registerButton;
    }

    public int getComp_id() {
        return comp_id;
    }

    public void setComp_id(int comp_id) {
        this.comp_id = comp_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<Company> getCompaniesList() {
        return companiesList;
    }

    public void setCompaniesList(List<Company> companiesList) {
        this.companiesList = companiesList;
    }


    
    
    public CompanyController() {
    }

    
    public String save()
    {
        System.out.println("SaveUser");
        Company company = new Company();
        company.setName(name);
        company.setEmail(email);
        company.setAddress(address);
        company.setContact(contact);
        company.setPassword(password);
        company.setProfile(profile);
        try
        {
            companyService.saveCompany(company);
        }
        catch(DataIntegrityViolationException e)
        {
            createMessage("Employer Exists", FacesMessage.SEVERITY_ERROR);
            return null;
        }
        companiesList.add(company);
        createMessage("Details Saved", FacesMessage.SEVERITY_INFO);
        return "addJob";
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
        companiesList = companyService.getCompaniesList();
                
    }
    
    
     public String login()
    {
        System.out.println("CompanyLogin");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        try{
            if(companyService.checkLogin(email,password))
            {
                String name=companyService.getCompany(email).getName();
                
                session.setAttribute("email", email);
                session.setAttribute("name", name);
                session.setAttribute("loggedin", true);
                session.setAttribute("role", "C");
                
                System.out.println("CompanyLoggedIn");
        
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
    
}

