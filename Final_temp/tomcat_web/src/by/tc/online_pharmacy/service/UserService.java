package by.tc.online_pharmacy.service;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.service.exception.ServiceException;

/**
 * Created by Евгений on 17.02.2017.
 */
public interface UserService {
    User signIn(String login, String password) throws ServiceException;
    int signUp(User user, String confirmPassword) throws ServiceException;
}
