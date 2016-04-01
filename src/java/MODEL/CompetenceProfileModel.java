package MODEL;

import DAO.*;
import DTO.CompetenceProfileDTO;
import java.util.ArrayList;
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
    public void addCompetenceprofile(String username,String competenceName,String comment)
    {
        Competenceprofile competenceprofile = new Competenceprofile();
        CompetenceprofilePK competenceprofilePK = new CompetenceprofilePK();
        competenceprofilePK.setUsername(username);
        competenceprofilePK.setCompetenceName(competenceName);
        competenceprofile.setCompetenceprofilePK(competenceprofilePK);
        competenceprofile.setComment(comment);
        
        em.persist(competenceprofile);
        em.flush();
    }
    
    public Competenceprofile getCompetenceprofile(int ID)
    {
        Competenceprofile competenceprofile = em.find(entity.getClass(), ID);
        return competenceprofile;
    }
    
    public List<CompetenceProfileDTO> getAllCompetenceprofiles()
    {
        List<Competenceprofile> results = em.createNamedQuery("Competenceprofile.findAll").getResultList();
        return toDTO(results);
    }
    
    public List<CompetenceProfileDTO> getAllCompetenceprofilesByUser(String user)
    {   
        List<Competenceprofile> results = em.createNamedQuery("Competenceprofile.findByUsername").setParameter("username", user).getResultList();
        return toDTO(results);
    }
    
    
    private CompetenceProfileDTO toDTO(Competenceprofile p){
        return new CompetenceProfileDTO(p.getUser().getUsername(),
                                        p.getCompetence().getCompetenceName(),
                                        p.getCompetence().getDescription(),
                                        p.getComment());
    }

    private List<CompetenceProfileDTO> toDTO(List<Competenceprofile> p){
        List<CompetenceProfileDTO> response = new ArrayList<>();
        for(Competenceprofile a: p){
            response.add(toDTO(a));
         }
        return response;            
    }
}
