package by.tc.onlinepharmacy.service;


import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.bean.RERDescription;
import by.tc.onlinepharmacy.service.exception.ValidatorException;

import java.util.List;

/**
 * Interface for {@link by.tc.onlinepharmacy.service.impl.DoctorServiceImpl DoctorServiceImpl}.
 */
public interface DoctorService {

    List<RERDescription> showRecipeExtensionRequestList() throws ServiceException;

    void approve(RERDescription rerDescription) throws ServiceException, ValidatorException;

    void deny(RERDescription rerDescription) throws ServiceException, ValidatorException;
}