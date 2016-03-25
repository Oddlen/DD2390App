package CONTROLLER;

import DAO.*;
import MODEL.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CompetenceController
{
    @Inject
    private CompetenceModel competenceModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
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
