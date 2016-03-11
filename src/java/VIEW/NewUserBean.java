package VIEW;

import CONTROLLER.UserController;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.RollbackException;

@Named("NewUser")
@SessionScoped
public class NewUserBean implements Serializable
{

    private String name;
    private String password;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working");
    }

    @Inject
    private UserController userController;
    
    public void addUser()
    {
        try
        {
            userController.addUser(name, password);
            System.out.println(name + " added to database");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

}
