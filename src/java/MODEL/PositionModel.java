package MODEL;

import DAO.*;
import DTO.PositionDTO;
import java.util.ArrayList;
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
    public void addPosition(Company company, String position, String description)
    {
        Position pos = new Position();        
        pos.setCompany(company);        
        pos.setPosition(position);        
        pos.setDescription(description);
        
        em.persist(pos);
        em.flush();
    }
    
    public Position getPosition(int id)
    {
        Position position = em.find(entity.getClass(), id);
        return position;
    }
    
      public List<PositionDTO> getAllPositions()
    {
        List<Position> results = em.createNamedQuery("Position.findAll").getResultList();
        return toDTO(results);
    }
    
        public List<PositionDTO> getUnAppliedPositions(String name)
    {
        List<Position> results = em.createNamedQuery("Position.findUnApliedBy").setParameter("username", name).getResultList();
        return toDTO(results);
    }
        public List<PositionDTO> getAppliedPositions(String name)
    {
        List<Position> results = em.createNamedQuery("Position.findApliedBy").setParameter("username", name).getResultList();
        return toDTO(results);
    }
        public List<PositionDTO> getCompanyPositions(Company company)
    {
        List<Position> results = em.createNamedQuery("Position.findByCompany").setParameter("company", company).getResultList();
        return toDTO(results);
    }
        private PositionDTO toDTO(Position p){
            return new PositionDTO(p.getId(),
                        p.getPosition(),
                        p.getDescription(),
                        p.getCompany().getName());
        }
        
        private List<PositionDTO> toDTO(List<Position> p){
            List<PositionDTO> response = new ArrayList<>();
            for(Position a: p){
                response.add(toDTO(a));
             }
            return response;            
        }
           
}
