
package model.dao;

import entities.Company;
import java.util.List;


public interface CompanyDao {
   
    public void saveCompany(Company company);
    public void deleteCompany(Company company);
    public Company getCompany(String email);
    public List<Company> getCompaniesList();
    public boolean checkLogin(String email,String password);

    
}
