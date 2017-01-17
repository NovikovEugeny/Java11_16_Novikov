package by.training.rental_shop.controller.command.impl;

import by.training.rental_shop.controller.command.Command;

/**
 * Created by Евгений on 13.01.2017.
 */
public class WrongRequest implements Command {
    @Override
    public String execute() {
        return "incorrect request";
    }
}
