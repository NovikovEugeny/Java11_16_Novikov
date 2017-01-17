package by.training.rental_shop.controller.command.impl;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.bean.User;
import by.training.rental_shop.controller.command.Command;
import by.training.rental_shop.service.ShopService;
import by.training.rental_shop.service.exception.ServiceException;
import by.training.rental_shop.service.factory.ServiceFactory;
import by.training.rental_shop.view.impl.ListViewImpl;

/**
 * Created by Евгений on 14.01.2017.
 */
public class RentedGoods implements Command {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String execute() {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ShopService shopService = serviceFactory.getShopService();
        ListViewImpl listViewImpl = new ListViewImpl();

        try {
            listViewImpl.printList(shopService.getRentedGoods(user));
            response = "Ok";
        } catch (ServiceException exc) {
            response = "list of goods is not available";
            System.out.println(exc.getMessage());
        }
        return response;
    }
}
