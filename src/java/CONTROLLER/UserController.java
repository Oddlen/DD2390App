package CONTROLLER;

import DAO.*;
import MODEL.*;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserController
{
    @Inject
    private UserModel userModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addUser(String name, String password)
    {
        userModel.addUser(new User(name, password));
    }
}
