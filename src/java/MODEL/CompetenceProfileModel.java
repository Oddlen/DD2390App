package MODEL;

import DAO.*;
import java.util.List;
import javax.ejb.*;
import javax.persistence.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CompetenceProfileModel
{

Competenceprofile entity = new Competenceprofile();

    @PersistenceContext(unitName = "DD2390AppPU")
    private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addCompetenceprofile(Competenceprofile competenceprofile)
    {
        em.persist(competenceprofile);
        em.flush();
    }
    
    public Competenceprofile getCompetenceprofile(int ID)
    {
        Competenceprofile competenceprofile = em.find(entity.getClass(), ID);
        return competenceprofile;
    }
    
    public List<Competenceprofile> getAllCompetenceprofiles()
    {
        List<Competenceprofile> results = em.createNamedQuery("Competenceprofile.findAll").getResultList();
        return results;
    }
    
    public List<Competenceprofile> getAllCompetenceprofilesByUser(String user)
    {   
        List<Competenceprofile> results = em.createNamedQuery("Competenceprofile.findByUsername").setParameter("username", user).getResultList();
        return results;
    }
    
}
