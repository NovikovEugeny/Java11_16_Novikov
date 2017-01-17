package by.training.rental_shop.controller.command.impl;

import by.training.rental_shop.bean.User;
import by.training.rental_shop.controller.command.Command;
import by.training.rental_shop.service.ClientService;
import by.training.rental_shop.service.exception.ServiceException;
import by.training.rental_shop.service.factory.ServiceFactory;

import java.util.Scanner;

/**
 * Created by Евгений on 13.01.2017.
 */
public class Registration implements Command {

    private User userInit() {
        User user = new User();
        System.out.println("Enter users data");
        System.out.print("name: ");
        Scanner scan = new Scanner(System.in);
        user.setName(scan.nextLine());

        System.out.print("mobile phone: ");
        user.setPhone(scan.nextLine());

        System.out.print("password: ");
        user.setPassword(scan.nextLine());

        return user;
    }

    @Override
    public String execute() {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            clientService.registration(userInit());
            response = "Registration completed successfully";
        } catch (ServiceException exc) {
            response = "Error during registration";
            System.out.println(exc.getMessage());
        }
        return response;
    }

}
