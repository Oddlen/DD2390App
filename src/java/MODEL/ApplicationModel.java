package MODEL;

import DAO.*;
import DTO.ApplicationDTO;
import java.util.ArrayList;
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
    public void addApplication(User user, Position pos, Status stat)
    {
        Application app = new Application();
        app.setUsername(user);        
        app.setPositionID(pos);
        app.setStatus(stat);
        em.persist(app);
        em.flush();
    }
    
      public List<ApplicationDTO> getApplicationsByUser(User user)
    {
        List<Application> results = em.createNamedQuery("Application.findByName").setParameter("username", user).getResultList();
        List<ApplicationDTO> response = new ArrayList<>();
        for(Application a: results){
            response.add(toDTO(a));
        }
        return response;
    }
        public List<ApplicationDTO> getApplicationsByPosition(Position position)
    {
        List<Application> results = em.createNamedQuery("Application.findByPositionId").setParameter("positionID", position).getResultList();
        List<ApplicationDTO> response = new ArrayList<>();
        for(Application a: results){
            response.add(toDTO(a));
        }
        return response;
    }
      
      public int deleteApplicationsByUser(User user)
    {
        int results = em.createNamedQuery("Application.deleteByName").setParameter("username", user).executeUpdate();
        return results;
    }

    public void setStatus(Application application, Status status) {
        application.setStatus(status);
        em.merge(application);
    }

    public List<ApplicationDTO> getPendingApplicationsByPosition(Status status, Position position) {
        
        List<Application> results = em.createNamedQuery("Application.findPendingByPositionId").setParameter("positionID", position).setParameter("status", status).getResultList();
        List<ApplicationDTO> response = new ArrayList<>();
        for(Application a: results){
            response.add(toDTO(a));
        }
        return response;
    }
    
    private ApplicationDTO toDTO(Application a){
        return new ApplicationDTO(a.getId(),
                    a.getPositionID().getId(),
                    a.getPositionID().getPosition(),
                    a.getStatus().getName(),
                    a.getUsername().getUsername());
    }
}
