package CONTROLLER;

import DAO.*;
import DTO.Response;
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
}
