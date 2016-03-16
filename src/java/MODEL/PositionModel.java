package MODEL;

import DAO.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PositionModel
{

Position entity = new Position();

    @PersistenceContext(unitName = "DD2390AppPU")
    private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addPosition(Position position)
    {
        em.persist(position);
        em.flush();
    }
    
    public Position getPosition(int id)
    {
        Position position = em.find(entity.getClass(), id);
        return position;
    }
    
      public List<Position> getAllPositions()
    {
        List<Position> results = em.createNamedQuery("Company.findAll").getResultList();
        return results;
    }
    
}
