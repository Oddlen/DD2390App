package VIEW;

import CONTROLLER.CompetenceController;
import CONTROLLER.CompetenceProfileController;
import DAO.Competence;
import DAO.Competenceprofile;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.RollbackException;

@Named("competence")
@SessionScoped
public class CompetenceBean implements Serializable
{

    List<Competence> competenceList;
    List<Competenceprofile> competenceProfile;



    private String competenceName;
    private String newCompetenceName;
    private String description;
    private String username = "hello";
    private String comment;

    
    private boolean hidden = true;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @PostConstruct
    public void startup()
    {        
      
        System.out.println("Recruitment bean Working");
        competenceList = competenceController.getAllCompetences();
        System.out.println("length "+competenceList.size());
        
        competenceProfile = competenceProfileController.getAllCompetenceprofilesByUser(username);
    }

    @Inject
    private CompetenceController competenceController;
    @Inject
    private CompetenceProfileController competenceProfileController;
    
    public void addCompetence()
    {
          FacesMessage msg;
        try
        {
            competenceController.addCompetence(newCompetenceName, description);
           // System.out.println(newCompanyName + " added to database");
            competenceList =  competenceController.getAllCompetences();
            competenceName = newCompetenceName;
            hidden = true;
            
            
        msg = new FacesMessage("Succes", " Competence "+newCompetenceName+ " added to database!");
        }
        catch (Exception e)
        {
        
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "error occured!");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void addCompetenceProfile(){
          FacesMessage msg;
        try
        {
            System.out.println(competenceName + " was input");
            competenceProfileController.addCompetenceprofile(username, competenceName, comment);
            System.out.println(competenceName + " added to database");
            msg = new FacesMessage("Succes", competenceName+ " added to profile!");
        }
        catch (Exception e)
        {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "error occured!");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        competenceProfile = competenceProfileController.getAllCompetenceprofilesByUser(username);
    }
  
    public void onCompetenceChange(){
        System.out.println("changesd");
        hidden = !competenceName.equals("add new");
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public String getDescription() {
        return description;
    }
        
    public List<Competence> getCompetenceList() {
        return competenceList;
    }
    
    public String getNewCompetenceName() {
        return newCompetenceName;
    }

    public void setNewCompetenceName(String newCompetenceName) {
        this.newCompetenceName = newCompetenceName;
    }

    public void setCompetenceList(List<Competence> CompetenceList) {
        this.competenceList = CompetenceList;
    }
    
    public void setCompetenceName(String CompetenceName) {
        this.competenceName = CompetenceName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public List<Competenceprofile> getCompetenceProfile() {
        return competenceProfile;
    }

    public void setCompetenceProfile(List<Competenceprofile> competenceProfile) {
        this.competenceProfile = competenceProfile;
    }
}
