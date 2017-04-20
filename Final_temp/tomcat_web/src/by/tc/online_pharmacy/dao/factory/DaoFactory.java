package by.tc.online_pharmacy.dao.factory;

import by.tc.online_pharmacy.dao.ClientDao;
import by.tc.online_pharmacy.dao.CommonDao;
import by.tc.online_pharmacy.dao.DoctorDao;
import by.tc.online_pharmacy.dao.PharmacistDao;
import by.tc.online_pharmacy.dao.impl.ClientDaoImpl;
import by.tc.online_pharmacy.dao.impl.CommonDaoImpl;
import by.tc.online_pharmacy.dao.impl.DoctorDaoImpl;
import by.tc.online_pharmacy.dao.impl.PharmacistDaoImpl;


public class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    private final ClientDao clientDao = new ClientDaoImpl();
    private final DoctorDao doctorDao = new DoctorDaoImpl();
    private final PharmacistDao pharmacistDao = new PharmacistDaoImpl();
    private final CommonDao commonDao = new CommonDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }


    public ClientDao getClientDao() {
        return clientDao;
    }

    public DoctorDao getDoctorDao() {
        return doctorDao;
    }

    public PharmacistDao getPharmacistDao() {
        return pharmacistDao;
    }

    public CommonDao getCommonDao() {
        return commonDao;
    }

}
