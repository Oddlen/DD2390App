package CONTROLLER;

import DAO.*;
import DTO.*;
import MODEL.ApplicationModel;
import MODEL.StatusModel;
import MODEL.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicationController
{    
    @EJB
    private RecruitmentController positionController;
    
    @Inject
    private ApplicationModel applicationModel;
    @Inject
    private StatusModel statusModel;
    @Inject
    private UserModel userModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addApplication(String userName, int positionID)
    {
        User user = getUser(userName);
        Position pos = positionController.getPosition(positionID);      
        Status stat = getStatus("Pending");          
        assert(user != null);
        assert(pos != null);
        
        applicationModel.addApplication(user, pos, stat);
    }
   
    
    public List<ApplicationDTO> getApplicationsByUser(String userName)
    {
        User user = getUser(userName);
        return applicationModel.getApplicationsByUser(user);
    }
        public List<ApplicationDTO> getInverseApplicationsByUser(String userName)
    {
        User user = getUser(userName);
        return applicationModel.getInverseApplicationsByUser(user);
    }
    
    public List<PositionDTO> getUnAppliedPositions(String name){
        List<PositionDTO> positionList = positionController.getAllPositions();
        List<ApplicationDTO> applicationsByUser = getApplicationsByUser(name);
        List<PositionDTO> temp = new ArrayList<>();  
        
        for(PositionDTO p : positionList){                  
            if(appliedTo(p,applicationsByUser)){
                temp.add(p);
            }
        }        
        ArrayList<PositionDTO> applicationList = new ArrayList<>();  
        applicationList.addAll(temp);
        
        return applicationList;
    }
    
    public List<PositionDTO> getAppliedPositions(String name){
        List<PositionDTO> positionList = positionController.getAllPositions();
        List<ApplicationDTO> applicationsByUser = getApplicationsByUser(name);
        List<PositionDTO> temp = new ArrayList<>();  
        
        for(PositionDTO p : positionList){                  
            if(appliedTo(p,applicationsByUser)){
                temp.add(p);
            }
        }
        positionList.removeAll(temp);
        
        return positionList;
    }
      
    public List<ApplicationDTO> getPendingApplicationsByPosition(int positionID)
    {
        Position position = positionController.getPosition(positionID);
        Status pending = getStatus("Pending");
        return applicationModel.getPendingApplicationsByPosition(pending, position);
    }
    
    public int deleteApplicationsByUser(String userName)
    {
        User user = getUser(userName);
        return applicationModel.deleteApplicationsByUser(user);
    }

    public void accept(ApplicationDTO application) {
        Status status = getStatus("Accepted");
        Application app = getApplication(application.getId());
        applicationModel.setStatus(app, status);
    }

    public void reject(ApplicationDTO application) {
        Status status = getStatus("Rejected");
        
        Application app = getApplication(application.getId());
        applicationModel.setStatus(app, status);
    }
    
    private Status getStatus(String name)
    {
        return statusModel.getStatus(name);
    }
     

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Response addUser(String name, String password)
    {
        try
        {
            User user = userModel.getUser(name);
            if (user != null)
            {
                return new Response("User already exsists", false);
            }
            userModel.addUser(name, password);
            return new Response("User " + name + " has been created", true);
        }
        catch (Exception e)
        {
            System.out.println(e + " At UserController in addUser method.");
            e.printStackTrace();
        }
        return new Response("System error, try again later", false);
    }
    
 
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)       
    public User getUser(String username){
        return userModel.getUser(username);
        
    }

    private Application getApplication(int id) {
        return applicationModel.getApplication(id);
    }

    public ApplicationDTO getApplicationDTO(int id) {
        
        return applicationModel.getApplicationDTO(id);
    }
    
    private boolean appliedTo(PositionDTO p,List<ApplicationDTO> applicationsByUser) {
       for(ApplicationDTO A: applicationsByUser){
           if(A.getPositionID()==p.getId()){
               return true;
           }
       } 
       return false;
    }
  
    public TreeNode generateTree(){
        List<String> companies = positionController.getAllCompanies();
        
        DefaultTreeNode root = new DefaultTreeNode("Root", null);
        
        for(String c: companies){
            
            List<PositionDTO> positions = positionController.getCompanyPositions(c);
            
            boolean noApplications = true;
            for(PositionDTO p: positions){                
                List<ApplicationDTO> applications = getPendingApplicationsByPosition(p.getId());
               
                if(!applications.isEmpty()){
                    noApplications=false;
                    break;
                }
            }
            
            if(noApplications){
                continue;
            }
                
            TreeNode company = new DefaultTreeNode(c, root);

            for(PositionDTO p: positions){
                List<ApplicationDTO> applications = getPendingApplicationsByPosition(p.getId());
                if(applications.isEmpty())
                    continue;
                
                TreeNode position = new DefaultTreeNode(p.getPosition(), company);
                for(ApplicationDTO a: applications){
                    TreeNode node = new DefaultTreeNode(a.getId(), position);
                }
            }
        }
        return root;
    }
    
    }

