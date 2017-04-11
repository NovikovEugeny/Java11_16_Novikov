package by.tc.online_pharmacy.dao.factory;

import by.tc.online_pharmacy.dao.DrugDao;
import by.tc.online_pharmacy.dao.UserDao;
import by.tc.online_pharmacy.dao.impl.DrugDaoImpl;
import by.tc.online_pharmacy.dao.impl.UserDaoImpl;


public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final DrugDao drugDao = new DrugDaoImpl();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public DrugDao getDrugDao() {
        return drugDao;
    }
}
