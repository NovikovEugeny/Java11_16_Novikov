package by.tc.onlinepharmacy.dao.factory;

import by.tc.onlinepharmacy.dao.*;
import by.tc.onlinepharmacy.dao.impl.*;


public class DAOFactory {

    private static final DAOFactory INSTANCE = new DAOFactory();

    private final AdminDAO adminDAO = new AdminDAOImpl();
    private final ClientDAO clientDAO = new ClientDAOImpl();
    private final DoctorDAO doctorDAO = new DoctorDAOImpl();
    private final PharmacistDAO pharmacistDAO = new PharmacistDAOImpl();
    private final CommonDAO commonDAO = new CommonDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    public DoctorDAO getDoctorDAO() {
        return doctorDAO;
    }

    public PharmacistDAO getPharmacistDAO() {
        return pharmacistDAO;
    }

    public CommonDAO getCommonDAO() {
        return commonDAO;
    }

}