package by.tc.online_pharmacy.service;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.List;

public interface CommonService {

    User signIn(String login, String password) throws ServiceException, ValidatorException;

    List<Drug> showDrugGroup(String group) throws ServiceException;

    List<Drug> showDrugsByName(String name) throws ServiceException, ValidatorException;

    void cancelOrder(int orderId) throws ServiceException;
}
