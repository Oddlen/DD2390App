package VIEW;

import CONTROLLER.ApplicationController;
import CONTROLLER.PositionController;
import DAO.Application;
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
import org.primefaces.model.DualListModel;

@Named("Application")
@SessionScoped
public class ApplicationBean implements Serializable
{

    private DualListModel<Position> positions;



    private String user = "hello";
    private String company;
    private String status;
    private String position;
    private String description;

    List<Position> positionList;
    List<Position> applicationList;
    List<Application> applicationsByUser;


        
    @PostConstruct
    public void startup()
    {        
        System.out.println("Application bean Working");
        
        positionList = positionController.getAllPositions();
        applicationList = new ArrayList<>();        
        
        applicationsByUser = applicationController.getApplicationsByUser(user);
        List<Position> temp = new ArrayList<>();  
        
        for(Position p : positionList){                  
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
    private PositionController positionController;
    
    public void addApplication(){  
        applicationController.deleteApplicationsByUser(user);
        for(int i=0; i<positions.getTarget().size(); i++){   
                applicationController.addApplication(user,positions.getTarget().get(i).getId());
        }
          startup();
        
    }
    
  
    public void onSelect(SelectEvent event) { 
        Position selected = (Position)event.getObject();
        description = selected.getDescription();
        if(description.equals(""))
            description = "none";
        positionList = positionController.getAllPositions();
    }

    public DualListModel<Position> getPositions() {
        return positions;
    }

    public void setPositions(DualListModel<Position> Applications) {
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
    public List<Application> getApplicationsByUser() {
        return applicationsByUser;
    }

    public void setApplicationsByUser(List<Application> applicationsByUser) {
        this.applicationsByUser = applicationsByUser;
    }
    
    private boolean appliedTo(Position p) {
       for(Application A: applicationsByUser){
           if(A.getPositionID().getId().equals(p.getId())){
               return true;
           }
       } 
       return false;
    }
  
}
