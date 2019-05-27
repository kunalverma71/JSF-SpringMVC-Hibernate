


package model.dao;

import entities.Company;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {
    
    
    private SessionFactory sessionFactory;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory= sessionFactory;
    }
    
    public Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveCompany(Company company) {
    
        getSession().save(company);
    }

    @Override
    public void deleteCompany(Company company) {
     
        getSession().delete(company);
    }

    @Override
    public Company getCompany(String email) {
     
        return (Company)getSession().createQuery("from Company where email=:email").setString("email", email).uniqueResult();
    }

    @Override
    public List<Company> getCompaniesList() {
       
        List<Company> companiesList= getSession().createQuery("from Company where name is not null").list();
        
        
        return companiesList;
    }

     @Override
    public boolean checkLogin(String email, String password) {
    
        if( getSession().createQuery("from Company where email=:email and password=:password").setString("email", email).setString("password", password).uniqueResult()!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
}
