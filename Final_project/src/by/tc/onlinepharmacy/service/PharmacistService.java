package by.tc.onlinepharmacy.service;


import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.bean.OrderDescription;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface for {@link by.tc.onlinepharmacy.service.impl.PharmacistServiceImpl PharmacistServiceImpl}.
 */
public interface PharmacistService {

    void addDrugQuantity(int id, int quantity) throws ServiceException, ValidatorException;

    void addNewDrug(Drug drug) throws ServiceException, ValidatorException;

    void removeDrug(int id) throws ServiceException;

    List<OrderDescription> pharmacistShowOrderList() throws ServiceException;

    void send(int orderId, int pharmacistId) throws ServiceException;

    Map<Date, List<Drug>> showSalesReport() throws ServiceException;
}