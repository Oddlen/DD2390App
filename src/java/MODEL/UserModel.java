package MODEL;

import javax.ejb.*;
import javax.persistence.*;
import DAO.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserModel
{

User entity = new User();
    @PersistenceContext(unitName = "DD2390AppPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addUser(String name, String password) throws RollbackException
    {
        User user = new User(name, hashPassword(password));
        user.setType(new Accounttype("applicant"));
        em.persist(user);
        em.flush();
    }

    public String hashPassword(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++)
            {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            return "Error hashing password!";
        }
    }
    
    public User getUser(String Name){
        User user = em.find(entity.getClass(), Name);
        return user;
    }
}
