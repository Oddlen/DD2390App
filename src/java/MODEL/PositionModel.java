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
        List<Position> results = em.createNamedQuery("Position.findAll").getResultList();
        return results;
    }
    
        public List<Position> getUnAppliedPositions(String name)
    {
        List<Position> results = em.createNamedQuery("Position.findUnApliedBy").setParameter("username", name).getResultList();
        return results;
    }
        public List<Position> getAppliedPositions(String name)
    {
        List<Position> results = em.createNamedQuery("Position.findApliedBy").setParameter("username", name).getResultList();
        return results;
    }
        public List<Position> getCompanyPositions(Company company)
    {
        List<Position> results = em.createNamedQuery("Position.findByCompany").setParameter("company", company).getResultList();
        return results;
    }
}
