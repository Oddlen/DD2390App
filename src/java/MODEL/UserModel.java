package MODEL;

import javax.ejb.*;
import javax.persistence.*;
import DAO.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserModel
{

    @PersistenceContext(unitName = "DD2390AppPU")
    private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addUser(User user)
    {
        em.persist(user);
        em.flush();
    }
}
