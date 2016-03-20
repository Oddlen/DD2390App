package MODEL;

import DAO.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicationModel
{

Application entity = new Application();

    @PersistenceContext(unitName = "DD2390AppPU")
    private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addApplication(Application application)
    {
        em.persist(application);
        em.flush();
    }
    
    public Application getApplication(int id)
    {
        Application application = em.find(entity.getClass(), id);
        return application;
    }
    
      public List<Application> getAllApplications()
    {
        List<Application> results = em.createNamedQuery("Application.findAll").getResultList();
        return results;
    }
      
      public List<Application> getApplicationsByUser(User user)
    {
        List<Application> results = em.createNamedQuery("Application.findByName").setParameter("username", user).getResultList();
        return results;
    }
      
      public int deleteApplicationsByUser(User user)
    {
        int results = em.createNamedQuery("Application.deleteByName").setParameter("username", user).executeUpdate();
        return results;
    }
    
}
