package VIEW;

import CONTROLLER.UserController;
import DTO.Response;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("NewUser")
@SessionScoped
public class NewUserBean implements Serializable
{

    private String name;
    private String password;
    private String passwordConfirm;
    private String message;

    @PostConstruct
    public void startup()
    {
        System.out.println("New user bean started and working");
    }

    @Inject
    private UserController userController;

    public void addUser()
    {
        try
        {
            Response response = userController.addUser(name, password);
            System.out.println(name + " added to database");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(response.getMessage()));
        }
        catch (Exception e)
        {
            System.out.println("Error when adding user " + name + "\n" + e);
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unknown system error, try again later."));
        }

    }

    public String getPasswordConfirm()
    {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm)
    {
        this.passwordConfirm = passwordConfirm;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
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
