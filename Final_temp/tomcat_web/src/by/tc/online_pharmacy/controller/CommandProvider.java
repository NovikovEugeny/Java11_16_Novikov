package by.tc.online_pharmacy.controller;

import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.command.CommandName;
import by.tc.online_pharmacy.controller.command.impl.*;
import by.tc.online_pharmacy.controller.command.impl.temp.ShowOrder;
import by.tc.online_pharmacy.controller.command.impl.temp.ShowRecipe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Евгений on 17.02.2017.
 */
public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_UP, new SignUp());
        repository.put(CommandName.LOG_OUT, new LogOut());
        repository.put(CommandName.SHOW_DRUGS, new ShowDrugs());
        repository.put(CommandName.SEARCH, new Search());
        repository.put(CommandName.ADD_DRUG, new AddDrug());
        repository.put(CommandName.REMOVE_DRUG, new RemoveDrug());
        repository.put(CommandName.SHOW_DRUGS_TO_ORDER, new ShowDrugsToOrder());
        repository.put(CommandName.SHOW_DRUGS_TO_REMOVE, new ShowDrugsToRemove());
        repository.put(CommandName.ORDER_WITHOUT_RECIPE, new OrderWithoutRecipe());
        repository.put(CommandName.ORDER_WITH_RECIPE, new OrderWithRecipe());
        repository.put(CommandName.CHECKOUT, new Checkout());
        repository.put(CommandName.SHOW_ORDER_LIST, new ShowOrderList());
        repository.put(CommandName.SHOW_ORDER, new ShowOrder());
        repository.put(CommandName.SHOW_RECIPE, new ShowRecipe());
        repository.put(CommandName.SEND, new Send());
        repository.put(CommandName.APPROVE, new Approve());
        repository.put(CommandName.DENY, new Deny());
        repository.put(CommandName.SEND_FEEDBACK, new SendFeedback());
    }

    public Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            //command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
