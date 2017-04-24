package by.tc.online_pharmacy.service;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PharmacistService {

    void addDrugQuantity(int id, int quantity) throws ServiceException, ValidatorException;

    void addNewDrug(Drug drug) throws ServiceException, ValidatorException;

    void removeDrug(int id) throws ServiceException;

    List<OrderDescription> pharmacistShowOrderList() throws ServiceException;

    void send(int orderId, int pharmacistId) throws ServiceException;

    Map<Date, List<Drug>> showSalesReport() throws ServiceException;
}
