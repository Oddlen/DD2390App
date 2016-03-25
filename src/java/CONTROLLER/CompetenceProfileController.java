package CONTROLLER;

import DAO.*;
import MODEL.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CompetenceProfileController
{
    @EJB
    private CompetenceController competenceController;
    @EJB
    private UserController userController;
    
    @Inject
    private CompetenceProfileModel competenceProfileModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addCompetenceprofile(String username,String competenceName,String comment)
    {
        Competenceprofile temp = new Competenceprofile();
        CompetenceprofilePK tempPK = new CompetenceprofilePK();
        tempPK.setUsername(username);
        tempPK.setCompetenceName(competenceName);
        temp.setCompetenceprofilePK(tempPK);
        temp.setComment(comment);
        competenceProfileModel.addCompetenceprofile(temp);
    }
    
     public Competenceprofile getCompetenceprofile(int id)
    {
        return competenceProfileModel.getCompetenceprofile(id);
    }
     
    public List<Competenceprofile> getAllCompetenceprofiles()
    {
        List<Competenceprofile> result = competenceProfileModel.getAllCompetenceprofiles();
        return result;
    }
    
    public List<Competenceprofile> getAllCompetenceprofilesByUser(String userName)
    {
       // User user = userController.getUser(userName);
        List<Competenceprofile> result = competenceProfileModel.getAllCompetenceprofilesByUser(userName);
        return result;
    }
}
