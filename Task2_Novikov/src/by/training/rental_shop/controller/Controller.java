package by.training.rental_shop.controller;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.bean.User;
import by.training.rental_shop.controller.command.Command;
import by.training.rental_shop.controller.command.impl.*;
import by.training.rental_shop.view.ConsoleInterface;

import java.util.Scanner;

/**
 * Created by Евгений on 13.01.2017.
 */
public class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final ConsoleInterface consoleInterface = new ConsoleInterface();

    public void executeIdentificationTask() {
        SignIn signIn = new SignIn();
        signIn.execute();
        User user = signIn.getUser();

        if (user != null) {
            consoleInterface.secondLevel();
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();

            switch (i) {
                case 1: {
                    AvailableGoods availableGoods = new AvailableGoods();
                    availableGoods.execute();
                    System.out.println();
                    ChooseGood chooseGood = new ChooseGood();
                    chooseGood.execute();
                    SportEquipment sE = chooseGood.getSportEquipment();
                    Rent rent = new Rent();
                    rent.setObjects(user, sE);
                    System.out.println(rent.execute());
                }break;

                case 2: {
                    RentedGoods rentedGoods = new RentedGoods();
                    rentedGoods.setUser(user);
                    System.out.println(rentedGoods.execute());
                }break;

            }
        }

    }

    public String executeTask() {
        String commandName = consoleInterface.getCommand();

        if (commandName != "sign_in") {
            Command executionCommand = provider.getCommand(commandName);
            String response = executionCommand.execute();
            return response;
        } else {
            executeIdentificationTask();
        }
        return "";
    }
}
