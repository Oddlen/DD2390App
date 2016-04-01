package VIEW;

import CONTROLLER.ApplicationController;
import CONTROLLER.RecruitmentController;
import DTO.ApplicationDTO;
import DTO.PositionDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.RollbackException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

@Named("Application")
@SessionScoped
public class ApplicationBean implements Serializable
{

    private DualListModel<PositionDTO> positions;



    private String user = "hello";
    private String company;
    private String status;
    private String position;
    private String description;

    List<PositionDTO> positionList;
    List<PositionDTO> applicationList;
    List<ApplicationDTO> applicationsByUser;


        
    @PostConstruct
    public void startup()
    {        
        System.out.println("Application bean Working");
        
        positionList = positionController.getAllPositions();
        applicationList = new ArrayList<>();        
        
        applicationsByUser = applicationController.getApplicationsByUser(user);
        List<PositionDTO> temp = new ArrayList<>();  
        
        for(PositionDTO p : positionList){                  
            if(appliedTo(p)){
                temp.add(p);
            }
        }
        positionList.removeAll(temp);
        applicationList.addAll(temp);
        
        positions = new DualListModel<>(positionList, applicationList);
      

    }

    @Inject
    private ApplicationController applicationController;
    @Inject
    private RecruitmentController positionController;
    
    public void addApplication(){  
        applicationController.deleteApplicationsByUser(user);
        for(int i=0; i<positions.getTarget().size(); i++){   
                applicationController.addApplication(user,positions.getTarget().get(i).getId());
        }
          startup();
        
    }
    
  
    public void onSelect(SelectEvent event) { 
        PositionDTO selected = (PositionDTO)event.getObject();
        description = selected.getDescription();
        if(description.equals(""))
            description = "none";
        positionList = positionController.getAllPositions();
    }

    public DualListModel<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(DualListModel<PositionDTO> Applications) {
        this.positions = Applications;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<ApplicationDTO> getApplicationsByUser() {
        return applicationsByUser;
    }

    public void setApplicationsByUser(List<ApplicationDTO> applicationsByUser) {
        this.applicationsByUser = applicationsByUser;
    }
    
    private boolean appliedTo(PositionDTO p) {
       for(ApplicationDTO A: applicationsByUser){
           if(A.getPositionID()==p.getId()){
               return true;
           }
       } 
       return false;
    }
  
}
