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
public class StatusController
{
    @Inject
    private StatusModel statusModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addStatus(String name)
    {
        statusModel.addStatus(new Status(name));
    }
    
     public Status getStatus(String name)
    {
        return statusModel.getStatus(name);
    }
     
    public List<String> getAllStatus()
    {
        List<String> names = new ArrayList<>();
        List<Status> result = statusModel.getAllStatus();
        for (Status c : result) {
            names.add(c.getName());
        }
        return names;
    }
}
