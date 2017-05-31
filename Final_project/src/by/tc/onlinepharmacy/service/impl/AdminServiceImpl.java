package by.tc.onlinepharmacy.service.impl;

import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.dao.AdminDAO;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.factory.DAOFactory;
import by.tc.onlinepharmacy.service.AdminService;
import by.tc.onlinepharmacy.service.exception.ServiceException;

import java.util.List;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.service.AdminService AdminService}.
 * Commands call these methods, which in turn call up DAO layer methods to receiving or transfer information.
 */
public class AdminServiceImpl implements AdminService {

    @Override
    public List<User> showEmployeeList() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            AdminDAO adminDAO = daoFactory.getAdminDAO();

            return adminDAO.takeEmployeeList();
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void removeEmployee(int id) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            AdminDAO adminDAO = daoFactory.getAdminDAO();

            adminDAO.removeEmployee(id);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }
}