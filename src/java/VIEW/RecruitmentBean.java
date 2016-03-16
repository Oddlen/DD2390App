package VIEW;

import CONTROLLER.CompanyController;
import CONTROLLER.PositionController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
        companyList = companyController.getAllCompanies();
        System.out.println("length "+companyList.size());
    }

    @Inject
    private CompanyController companyController;
    @Inject
    private PositionController positionController;
    
    public void addCompany()
    {
        try
        {
            companyController.addCompany(newCompanyName);
            System.out.println(newCompanyName + " added to database");
            companyList = companyController.getAllCompanies();
            companyName = newCompanyName;
            hidden = true;
        }
        catch (Exception e)
        {
         //  e.printStackTrace();
        }
    }
    
    public void addPosition(){
        try
        {
            positionController.addPosition(companyName, positionName, description);
            System.out.println(positionName + " added to database");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
