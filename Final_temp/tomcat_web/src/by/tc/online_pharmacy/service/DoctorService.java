package by.tc.online_pharmacy.service;


import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.service.exception.ServiceException;

import java.util.List;

public interface DoctorService {

    List<RERDescription> showRecipeExtensionRequestList() throws ServiceException;

    void approve(RERDescription rerDescription) throws ServiceException;

    void deny(RERDescription rerDescription) throws ServiceException;
}
