package by.training.rental_shop.controller.command.impl;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.bean.User;
import by.training.rental_shop.controller.command.Command;
import by.training.rental_shop.service.ClientService;
import by.training.rental_shop.service.ShopService;
import by.training.rental_shop.service.exception.ServiceException;
import by.training.rental_shop.service.factory.ServiceFactory;

/**
 * Created by Евгений on 14.01.2017.
 */
public class Rent implements Command {

    private User user;
    private SportEquipment sE;

    public void setObjects(User user, SportEquipment sE) {
        this.user = user;
        this.sE = sE;
    }

    @Override
    public String execute() {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ShopService shopService = serviceFactory.getShopService();

        try {
            shopService.rent(user, sE);
            response = "Ok";
        } catch (ServiceException exc) {
            response = "it is unable to rent good";
            System.out.println(exc.getMessage());
        }
        return response;
    }
}
