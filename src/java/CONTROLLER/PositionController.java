package CONTROLLER;

import DAO.*;
import MODEL.*;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PositionController
{
    @EJB
    private CompanyController companyController;
    
    @Inject
    private PositionModel positionModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addPosition(String companyName, String position, String description)
    {
        Company company = companyController.getCompany(companyName);
        System.out.println("found compnay");
        Position pos = new Position();
        
        assert(company != null);
        pos.setCompany(company);
        
        assert(!"".equals(position));
        pos.setPosition(position);
        
        if(!"".equals(description))
            pos.setDescription(description);
        
        positionModel.addPosition(pos);
    }
    
    public Position getPosition(int ID)
    {
        return positionModel.getPosition(ID);
    }
     
    public List<Position> getAllPositions()
    {
        return positionModel.getAllPositions();
    }
    
    public List<Position> getCompanyPositions(String name)
    {
        Company company = companyController.getCompany(name);
        return positionModel.getCompanyPositions(company);
    }
}
