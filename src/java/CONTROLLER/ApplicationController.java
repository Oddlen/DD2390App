package CONTROLLER;

import DAO.*;
import DTO.Response;
import MODEL.ApplicationModel;
import MODEL.StatusModel;
import MODEL.UserModel;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;

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
        Application app = new Application();
        
        assert(user != null);
        app.setUsername(user);
        
        Position pos = positionController.getPosition(positionID);        
        assert(pos != null);
        app.setPositionID(pos);
        
        Status stat = getStatus("Pending");
        app.setStatus(stat);
        
        applicationModel.addApplication(app);
    }
    
    public Application getApplication(int ID)
    {
        return applicationModel.getApplication(ID);
    }
     
    public List<Application> getAllApplications()
    {
        return applicationModel.getAllApplications();
    }
    
    public List<Application> getApplicationsByUser(String userName)
    {
        User user = getUser(userName);
        return applicationModel.getApplicationsByUser(user);
    }
    
    public List<Application> getPendingApplicationsByPosition(int positionID)
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

    public void accept(Application application) {
        Status status = getStatus("Accepted");
        applicationModel.setStatus(application, status);
        application = null;
    }

    public void reject(Application application) {
        Status status = getStatus("Rejected");
        applicationModel.setStatus(application, status);
           application = null;
    }
    
     public Status getStatus(String name)
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
    
        
    public User getUser(String username){
        return userModel.getUser(username);
        
    }
}
