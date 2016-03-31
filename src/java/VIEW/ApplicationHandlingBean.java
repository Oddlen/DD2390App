package VIEW;

import CONTROLLER.ApplicationController;
import CONTROLLER.CompetenceProfileController;
import CONTROLLER.RecruitmentController;
import DAO.Application;
import DAO.Company;
import DAO.Competenceprofile;
import DAO.Position;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.RollbackException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

@Named("applicationhandler")
@SessionScoped
public class ApplicationHandlingBean implements Serializable
{

    List<String> companies;
    private TreeNode root;  
    private TreeNode selected;
    private Application application;
    private List<Competenceprofile> competenceProfile;

    
    
    
    @PostConstruct
    public void startup()
    {        
        companies = positionController.getAllCompanies();
        
        root = new DefaultTreeNode("Root", null);
        
        for(String c: companies){
            
            List<Position> positions = positionController.getCompanyPositions(c);
            
            boolean noApplications = true;
            for(Position p: positions){                
                List<Application> applications = applicationController.getPendingApplicationsByPosition(p.getId());
                
                if(!applications.isEmpty()){
                    noApplications=false;
                    break;
                }
            }
            
            if(noApplications){
                continue;
            }
                
            TreeNode company = new DefaultTreeNode(c, root);

            for(Position p: positions){
                List<Application> applications = applicationController.getPendingApplicationsByPosition(p.getId());
                if(applications.isEmpty())
                    continue;
                
                TreeNode position = new DefaultTreeNode(p.getPosition(), company);
                for(Application a: applications){
                    TreeNode application = new DefaultTreeNode(a, position);
                }
            }
        }
        
        
    }

    @Inject
    private ApplicationController applicationController;
    @Inject
    private RecruitmentController positionController;
    @Inject
    private CompetenceProfileController competenceProfileController;
    
    
    public void setSelected(TreeNode  selected){
        this.selected = selected;        
    }
    
    public TreeNode getSelected(){
        return selected;
    }
    
    public void setApplication(Application  application){
        this.application = application;        
    }
    
    public Application getApplication(){
        return application;
    }
    public TreeNode getRoot() {
        return root;
    }
     
    public void onNodeSelect(){
        if(selected.isLeaf()){
            application = (Application) selected.getData();
       //      System.out.println(application.getUsername().getUsername());
            competenceProfile = competenceProfileController.getAllCompetenceprofilesByUser(application.getUsername().getUsername());
        
       System.out.println(competenceProfile.get(0).getUser().getUsername());
        }
        
    }
    
    public List<Competenceprofile> getCompetenceProfile() {
        return competenceProfile;
    }

    public void setCompetenceProfile(List<Competenceprofile> competenceProfile) {
        this.competenceProfile = competenceProfile;
    }
    
    public void accept(){
        applicationController.accept(application);
        startup();
    }
    
    public void reject(){
        applicationController.reject(application);
        startup();
    }
}
