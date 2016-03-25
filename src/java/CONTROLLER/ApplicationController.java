package CONTROLLER;

import DAO.*;
import MODEL.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicationController
{
    @EJB
    private StatusController statusController;    
    @EJB
    private UserController userController;    
    @EJB
    private PositionController positionController;
    
    @Inject
    private ApplicationModel applicationModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addApplication(String userName, int positionID)
    {
        User user = userController.getUser(userName);
        Application app = new Application();
        
        assert(user != null);
        app.setUsername(user);
        
        Position pos = positionController.getPosition(positionID);        
        assert(pos != null);
        app.setPositionID(pos);
        
        Status stat = statusController.getStatus("Pending");
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
        User user = userController.getUser(userName);
        return applicationModel.getApplicationsByUser(user);
    }
    
    public List<Application> getPendingApplicationsByPosition(int positionID)
    {
        Position position = positionController.getPosition(positionID);
        Status pending = statusController.getStatus("Pending");
        return applicationModel.getPendingApplicationsByPosition(pending, position);
    }
    
        public int deleteApplicationsByUser(String userName)
    {
        User user = userController.getUser(userName);
        return applicationModel.deleteApplicationsByUser(user);
    }

    public void accept(Application application) {
        Status status = statusController.getStatus("Accepted");
        applicationModel.setStatus(application, status);
        application = null;
    }

    public void reject(Application application) {
        Status status = statusController.getStatus("Rejected");
        applicationModel.setStatus(application, status);
           application = null;
    }
}
