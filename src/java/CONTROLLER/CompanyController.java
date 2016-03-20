package CONTROLLER;

import DAO.*;
import MODEL.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CompanyController
{
    @Inject
    private CompanyModel companyModel;

    @PostConstruct
    public void startup()
    {
        System.out.println("Working controller");
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
