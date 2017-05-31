package by.tc.onlinepharmacy.service;

import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.service.exception.ServiceException;

import java.util.List;

/**
 * Interface for {@link by.tc.onlinepharmacy.service.impl.AdminServiceImpl AdminServiceImpl}.
 */
public interface AdminService {

    List<User> showEmployeeList() throws ServiceException;

    void removeEmployee(int id) throws ServiceException;
}