
package entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@ManagedBean(name="applicant_Qualification")
@RequestScoped
@Entity
public class Applicant_Qualification {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int qly_id;
    private String email;
    private String graduation;
    private String passing_year;
    private String board;
    private String skill;

    public int getQly_id() {
        return qly_id;
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
 
    
    
    
    public Applicant_Qualification() {
        
    }
    
}
