
package entities;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@ManagedBean(name="job_Profile")
@RequestScoped
@Entity
public class Job_Profile {

    @Id
    private String job_id;
    private String comp_name;
    private String job_description;
    private String job_location;
    private String requirement;
    private String skills;
    private Date last_date;
    private String salary;
    private int no_of_vacancies;

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
    
    
    public Job_Profile() {
    }
    
}
