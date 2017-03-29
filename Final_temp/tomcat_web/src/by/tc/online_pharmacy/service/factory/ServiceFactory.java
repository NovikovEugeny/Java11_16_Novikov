package by.tc.online_pharmacy.service.factory;

import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.UserService;
import by.tc.online_pharmacy.service.impl.PharmServiceImpl;
import by.tc.online_pharmacy.service.impl.UserServiceImpl;

/**
 * Created by Евгений on 17.02.2017.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final PharmService pharmService = new PharmServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public PharmService getPharmService() {
        return pharmService;
    }
}
