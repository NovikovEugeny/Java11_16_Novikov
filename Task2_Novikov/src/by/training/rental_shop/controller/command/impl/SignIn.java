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
public class SignIn implements Command {

    private User user;

    public User getUser() {
        return user;
    }

    private String getMobilePhone() {
        System.out.print("Enter mobile phone: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private String getPassword() {
        System.out.print("Enter password: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public String execute() {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            user = clientService.signIn(getMobilePhone(), getPassword());
            response = "Welcome";
        } catch (ServiceException exc) {
            response = "Wrong login or password";
            System.out.println(exc.getMessage());
        }
        return response;
    }
}