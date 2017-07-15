package by.training.rental_shop.controller.command.impl;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.bean.User;
import by.training.rental_shop.controller.command.Command;
import by.training.rental_shop.service.ClientService;
import by.training.rental_shop.service.ShopService;
import by.training.rental_shop.service.exception.ServiceException;
import by.training.rental_shop.service.factory.ServiceFactory;

import java.util.Scanner;

/**
 * Created by Евгений on 15.01.2017.
 */
public class ChooseGood implements Command{

    private SportEquipment sportEquipment;

    public SportEquipment getSportEquipment() {
        return sportEquipment;
    }

    private String getTitle() {// какое еще чтение с клавиатуры
        // что это за бред?
        // ты вообще понял, зачем и как на слое контроллера применяется шаблон Команда?
        System.out.print("Enter goods title that you want to rent: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public String execute() {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ShopService shopService = serviceFactory.getShopService();

        try {
            sportEquipment = shopService.chooseGood(getTitle());
        } catch (ServiceException exc) {
            response = "Wrong login or password";
            System.out.println(exc.getMessage());
        }
        return response;
    }
}
