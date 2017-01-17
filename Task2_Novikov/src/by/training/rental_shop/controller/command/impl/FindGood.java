package by.training.rental_shop.controller.command.impl;

import by.training.rental_shop.controller.command.Command;
import by.training.rental_shop.service.ShopService;
import by.training.rental_shop.service.exception.ServiceException;
import by.training.rental_shop.service.factory.ServiceFactory;
import by.training.rental_shop.view.impl.ListViewImpl;

import java.util.Scanner;

/**
 * Created by Евгений on 14.01.2017.
 */
public class FindGood implements Command {

    private String getTitle() {
        System.out.print("enter the goods title: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public String execute() {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ShopService shopService = serviceFactory.getShopService();
        ListViewImpl listViewImpl = new ListViewImpl();

        try {
            listViewImpl.printList(shopService.findGood(getTitle()));
        } catch (ServiceException exc) {
            response = "list of goods is not available";
            System.out.println(exc.getMessage());
        }
        return response;
    }
}
