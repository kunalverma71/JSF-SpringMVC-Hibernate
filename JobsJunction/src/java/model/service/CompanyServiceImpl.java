
package model.service;

import entities.Company;
import java.util.List;
import javax.transaction.Transactional;
import model.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService{
    
    @Autowired
    CompanyDao companyDao;

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public void saveCompany(Company company) {
        
        companyDao.saveCompany(company);
    }

    @Override
    public void deleteCompany(Company company) {
        
        companyDao.deleteCompany(company);
    }

    @Override
    public Company getCompany(String email) {
        
        return companyDao.getCompany(email);
    }

    @Override
    public List<Company> getCompaniesList() {
        
        return companyDao.getCompaniesList();
    }
    
    @Override
    public boolean checkLogin(String email, String password) {
    
        return companyDao.checkLogin(email,password);
    }
    
}
