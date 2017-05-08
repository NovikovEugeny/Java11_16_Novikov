package by.tc.onlinepharmacy.controller.command;

import by.tc.onlinepharmacy.controller.command.impl.client.*;
import by.tc.onlinepharmacy.controller.command.impl.common.*;
import by.tc.onlinepharmacy.controller.command.impl.doctor.Deny;
import by.tc.onlinepharmacy.controller.command.impl.page.*;
import by.tc.onlinepharmacy.controller.command.impl.pharmacist.*;
import by.tc.onlinepharmacy.resource.CommandName;
import by.tc.onlinepharmacy.controller.command.impl.doctor.Approve;
import by.tc.onlinepharmacy.controller.command.impl.doctor.ShowRecipeExtensionRequests;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a map that stores command name/object-command pairs,
 * and provides the corresponding object-command.
 */
public class CommandProvider {

    private final static CommandProvider INSTANCE = new CommandProvider();

    private final Map<CommandName, Command> repository = new HashMap<>();

    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    private CommandProvider() {

        //pages
        repository.put(CommandName.START_PAGE, new StartPage());
        repository.put(CommandName.SIGN_IN_PAGE, new SignInPage());
        repository.put(CommandName.SIGN_UP_PAGE, new SignUpPage());
        repository.put(CommandName.GROUPS_TO_ORDER_PAGE, new GroupsToOrderPage());
        repository.put(CommandName.ORDER_BY_ERECIPE_PAGE, new OrderByERecipePage());
        repository.put(CommandName.EXTEND_RECIPE_PAGE, new ExtendRecipePage());
        repository.put(CommandName.GROUPS_TO_UPDATE_PAGE, new GroupsToUpdatePage());
        repository.put(CommandName.ADD_DRUG_PAGE, new AddDrugPage());

        //common
        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.LOG_OUT, new LogOut());
        repository.put(CommandName.SHOW_DRUGS, new ShowDrugs());
        repository.put(CommandName.SEARCH, new Search());
        repository.put(CommandName.WRONG_REQUEST_PAGE, new WrongRequestPage());

        //pharmacist
        repository.put(CommandName.ADD_NEW_DRUG, new AddNewDrug());
        repository.put(CommandName.ADD_DRUG_QUANTITY, new AddDrugQuantity());
        repository.put(CommandName.REMOVE_DRUG, new RemoveDrug());
        repository.put(CommandName.SHOW_DRUGS_TO_UPDATE, new ShowDrugsToUpdate());
        repository.put(CommandName.PHARMACIST_SHOW_ORDER_LIST, new PharmacistShowOrderList());
        repository.put(CommandName.SEND, new Send());
        repository.put(CommandName.PHARMACIST_CANCEL_ORDER, new PharmacistCancelOrder());
        repository.put(CommandName.SHOW_SALES_REPORT, new ShowSalesReport());

        //client
        repository.put(CommandName.SIGN_UP, new SignUp());
        repository.put(CommandName.SHOW_DRUGS_TO_ORDER, new ShowDrugsToOrder());
        repository.put(CommandName.ORDER_WITHOUT_RECIPE, new OrderWithoutRecipe());
        repository.put(CommandName.ORDER_WITH_RECIPE, new OrderWithRecipe());
        repository.put(CommandName.SHOW_ERECIPE, new ShowERecipe());
        repository.put(CommandName.CLIENT_SHOW_ORDER_LIST, new ClientShowOrderList());
        repository.put(CommandName.CLIENT_CANCEL_ORDER, new ClientCancelOrder());
        repository.put(CommandName.SEND_RECIPE_EXTENSION_REQUEST, new SendRecipeExtensionRequest());
        repository.put(CommandName.REPORT_ABOUT_DELIVERY, new ReportAboutDelivery());
        repository.put(CommandName.SHOW_MESSAGES, new ShowMessages());
        repository.put(CommandName.DELETE_MESSAGE, new DeleteMessage());
        repository.put(CommandName.SHOW_BALANCE, new ShowBalance());
        repository.put(CommandName.SHOW_SHOPPING_LIST, new ShowShoppingList());
        repository.put(CommandName.REPLENISH_BALANCE, new ReplenishBalance());

        //doctor
        repository.put(CommandName.APPROVE, new Approve());
        repository.put(CommandName.DENY, new Deny());
        repository.put(CommandName.SHOW_RECIPE_EXTENSION_REQUESTS, new ShowRecipeExtensionRequests());
    }

    /**
     * Provides an instance of {@link Command} main.by command name.
     *
     * @param name contains a command name
     * @return object-command of the appropriate type
     */
    public Command getCommand(String name) {

        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        Command command = repository.get(commandName);

        return command;
    }
}