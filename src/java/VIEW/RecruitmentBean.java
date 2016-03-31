package VIEW;

import CONTROLLER.RecruitmentController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.RollbackException;

@Named("Recruitment")
@SessionScoped
public class RecruitmentBean implements Serializable
{

    List<String> companyList;


    private String companyName;
    private String newCompanyName;


    private String positionName;
    private String description;
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
        companyList = positionController.getAllCompanies();
        System.out.println("length "+companyList.size());
    }

    @Inject
    private RecruitmentController positionController;
    
    public void addCompany()
    {
          FacesMessage msg;
        try
        {
            positionController.addCompany(newCompanyName);
           // System.out.println(newCompanyName + " added to database");
            companyList = positionController.getAllCompanies();
            companyName = newCompanyName;
            hidden = true;
            
            
        msg = new FacesMessage("Succes", "Company "+newCompanyName+ " added to database!");
        }
        catch (Exception e)
        {
        
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "error occured!");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public void addPosition(){
          FacesMessage msg;
        try
        {
            positionController.addPosition(companyName, positionName, description);
            System.out.println(positionName + " added to database");
            msg = new FacesMessage("Succes", "Position "+positionName+ " added to database!");
        }
        catch (Exception e)
        {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "error occured!");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }
  
    public void onCompanyChange(){
        hidden = !companyName.equals("add new");
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPositionName() {
        return positionName;
    }

    public String getDescription() {
        return description;
    }
        
    public List<String> getCompanyList() {
        return companyList;
    }
    
    public String getNewCompanyName() {
        return newCompanyName;
    }

    public void setNewCompanyName(String newCompanyName) {
        this.newCompanyName = newCompanyName;
    }

    public void setCompanyList(List<String> companyList) {
        this.companyList = companyList;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
