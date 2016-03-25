package MODEL;

import DAO.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CompetenceModel
{

Competence entity = new Competence();

    @PersistenceContext(unitName = "DD2390AppPU")
    private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCompetence(Competence competence)
    {
        em.persist(competence);
        em.flush();
    }
    
    public Competence getCompetence(String name)
    {
        Competence competence = em.find(entity.getClass(), name);
        if(competence==null)
            System.out.println("its null, name was "+name);
        
        return competence;
    }
    
      public List<Competence> getAllCompetences()
    {
        List<Competence> results = em.createNamedQuery("Competence.findAll").getResultList();
        System.out.println(results.size()+" this many");
        return results;
    }
    
}
