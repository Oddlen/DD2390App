package MODEL;

import DAO.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StatusModel
{

Status entity = new Status();

    @PersistenceContext(unitName = "DD2390AppPU")
    private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addStatus(Status status)
    {
        em.persist(status);
        em.flush();
    }
    
    public Status getStatus(String name)
    {
        Status status = em.find(entity.getClass(), name);
        return status;
    }
    
      public List<Status> getAllStatus()
    {
        List<Status> results = em.createNamedQuery("Status.findAll").getResultList();
        return results;
    }
    
}
