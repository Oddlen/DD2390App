package MODEL;

import DAO.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CompanyModel
{

Company entity = new Company();

    @PersistenceContext(unitName = "DD2390AppPU")
    private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCompany(Company company)
    {
        em.persist(company);
        em.flush();
    }
    
    public Company getCompany(String name)
    {
        Company company = em.find(entity.getClass(), name);
        return company;
    }
    
      public List<Company> getAllCompanies()
    {
        List<Company> results = em.createNamedQuery("Company.findAll").getResultList();
        System.out.println(results.size()+" this many");
        return results;
    }
    
}
