package CONTROLLER;

import DAO.*;
import DTO.CompetenceDTO;
import DTO.CompetenceProfileDTO;
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
        competenceProfileModel.addCompetenceprofile(username,competenceName,comment);
    }
    
    public Competenceprofile getCompetenceprofile(int id)
    {
        return competenceProfileModel.getCompetenceprofile(id);
    }
     
    public List<CompetenceProfileDTO> getAllCompetenceprofiles()
    {
        List<CompetenceProfileDTO> result = competenceProfileModel.getAllCompetenceprofiles();
        return result;
    }
    
    public List<CompetenceProfileDTO> getAllCompetenceprofilesByUser(String userName)
    {
        List<CompetenceProfileDTO> result = competenceProfileModel.getAllCompetenceprofilesByUser(userName);
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
     
    public List<CompetenceDTO> getAllCompetences()
    {
        List<CompetenceDTO> result = competenceModel.getAllCompetences();
        return result;
    }
}
