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
    @Inject
    private CompetenceProfileModel competenceProfileModel;    
    @Inject
    private CompetenceModel competenceModel;

    @PostConstruct
    public void startup()
    {}
    
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
        List<Competenceprofile> result = competenceProfileModel.getAllCompetenceprofilesByUser(userName);
        return result;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addCompetence(String name, String description)
    {
        competenceModel.addCompetence(new Competence(name, description));
    }
    
     public Competence getCompetence(String name)
    {
        return competenceModel.getCompetence(name);
    }
     
    public List<Competence> getAllCompetences()
    {
        List<Competence> result = competenceModel.getAllCompetences();
        return result;
    }
}
