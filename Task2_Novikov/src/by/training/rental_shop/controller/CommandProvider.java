package by.training.rental_shop.controller;

import by.training.rental_shop.controller.command.Command;
import by.training.rental_shop.controller.command.CommandName;
import by.training.rental_shop.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Евгений on 13.01.2017.
 */
public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.RENT, new Rent());
        repository.put(CommandName.AVAILABLE_GOODS, new AvailableGoods());
        repository.put(CommandName.RENTED_GOODS, new RentedGoods());
        repository.put(CommandName.CHOOSE_GOOD, new RentedGoods());
        repository.put(CommandName.FIND_GOOD, new FindGood());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
