package by.tc.onlinepharmacy.service.factory;

import by.tc.onlinepharmacy.service.impl.ClientServiceImpl;
import by.tc.onlinepharmacy.service.ClientService;
import by.tc.onlinepharmacy.service.CommonService;
import by.tc.onlinepharmacy.service.DoctorService;
import by.tc.onlinepharmacy.service.PharmacistService;
import by.tc.onlinepharmacy.service.impl.CommonServiceImpl;
import by.tc.onlinepharmacy.service.impl.DoctorServiceImpl;
import by.tc.onlinepharmacy.service.impl.PharmacistServiceImpl;

/**
 * Implementation of the factory template for the service layer.
 */
public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();
    private final PharmacistService pharmacistService = new PharmacistServiceImpl();
    private final DoctorService doctorService = new DoctorServiceImpl();
    private final CommonService commonService = new CommonServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public PharmacistService getPharmacistService() {
        return pharmacistService;
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public CommonService getCommonService() {
        return commonService;
    }
}