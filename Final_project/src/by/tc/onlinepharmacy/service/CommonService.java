package by.tc.onlinepharmacy.service;


import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;

import java.util.List;

/**
 * Interface for {@link by.tc.onlinepharmacy.service.impl.CommonServiceImpl CommonServiceImpl}.
 */
public interface CommonService {

    User signIn(String login, String password) throws ServiceException, ValidatorException;

    List<Drug> showDrugGroup(String group) throws ServiceException;

    List<Drug> showDrugsByName(String name) throws ServiceException, ValidatorException;

    void cancelOrder(int orderId) throws ServiceException;
}