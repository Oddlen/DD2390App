package CONTROLLER;

import DAO.*;
import DTO.PositionDTO;
import MODEL.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RecruitmentController
{
    
    @Inject
    private PositionModel positionModel;    
    @Inject
    private CompanyModel companyModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addPosition(String companyName, String position, String description)
    {
        Company company = getCompany(companyName);
        assert(company != null);
        assert(!"".equals(position));
        if(!"".equals(description))
        
        positionModel.addPosition(company, position, description );
    }
    
    public Position getPosition(int ID)
    {
        return positionModel.getPosition(ID);
    }
     
    public List<PositionDTO> getAllPositions()
    {
        return positionModel.getAllPositions();
    }
    
    public List<PositionDTO> getCompanyPositions(String name)
    {
        Company company = getCompany(name);
        return positionModel.getCompanyPositions(company);
    }
    
        @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addCompany(String name)
    {
        companyModel.addCompany(new Company(name));
    }
    
     public Company getCompany(String name)
    {
        return companyModel.getCompany(name);
    }
     
    public List<String> getAllCompanies()
    {
        List<String> names = new ArrayList<>();
        List<Company> result = companyModel.getAllCompanies();
        for (Company c : result) {
            names.add(c.getName());
        }
        return names;
    }
}
