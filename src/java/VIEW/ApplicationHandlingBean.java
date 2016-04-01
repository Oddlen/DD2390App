package VIEW;

import CONTROLLER.ApplicationController;
import CONTROLLER.CompetenceProfileController;
import CONTROLLER.RecruitmentController;
import DTO.ApplicationDTO;
import DTO.CompetenceProfileDTO;
import DTO.PositionDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.RollbackException;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named("applicationhandler")
@SessionScoped
public class ApplicationHandlingBean implements Serializable
{

    List<String> companies;
    private TreeNode root;  
    private TreeNode selected;
    private ApplicationDTO application;
    private List<CompetenceProfileDTO> competenceProfile;

    
    
    
    @PostConstruct
    public void startup()
    {
        root = applicationController.generateTree();
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
    
    public void setApplication(ApplicationDTO  application){
        this.application = application;        
    }
    
    public ApplicationDTO getApplication(){
        return application;
    }
    public TreeNode getRoot() {
        return root;
    }
     
    public void onNodeSelect(){
        if(selected.isLeaf()){
            int id= (int) selected.getData();
            application = applicationController.getApplicationDTO(id);
            competenceProfile = competenceProfileController.getAllCompetenceprofilesByUser(application.getUsername());
            
       System.out.println(competenceProfile.get(0).getUserName());
        }
        
    }
    
    public List<CompetenceProfileDTO> getCompetenceProfile() {
        return competenceProfile;
    }

    public void setCompetenceProfile(List<CompetenceProfileDTO> competenceProfile) {
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
