package by.tc.onlinepharmacy.dao.factory;

import by.tc.onlinepharmacy.dao.ClientDAO;
import by.tc.onlinepharmacy.dao.CommonDAO;
import by.tc.onlinepharmacy.dao.DoctorDAO;
import by.tc.onlinepharmacy.dao.PharmacistDAO;
import by.tc.onlinepharmacy.dao.impl.ClientDAOImpl;
import by.tc.onlinepharmacy.dao.impl.CommonDAOImpl;
import by.tc.onlinepharmacy.dao.impl.DoctorDAOImpl;
import by.tc.onlinepharmacy.dao.impl.PharmacistDAOImpl;


public class DAOFactory {

    private static final DAOFactory INSTANCE = new DAOFactory();

    private final ClientDAO clientDAO = new ClientDAOImpl();
    private final DoctorDAO doctorDAO = new DoctorDAOImpl();
    private final PharmacistDAO pharmacistDAO = new PharmacistDAOImpl();
    private final CommonDAO commonDAO = new CommonDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
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