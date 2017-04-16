package by.tc.online_pharmacy.service.factory;

import by.tc.online_pharmacy.service.*;
import by.tc.online_pharmacy.service.impl.*;


public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();
    private final PharmacistService pharmacistService = new PharmacistServiceImpl();
    private final DoctorService doctorService = new DoctorServiceImpl();
    private final CommonService commonService = new CommonServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
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
