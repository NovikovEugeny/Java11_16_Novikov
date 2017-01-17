package by.training.rental_shop.view;

import java.util.Scanner;

/**
 * Created by Евгений on 15.01.2017.
 */
public class ConsoleInterface {

    private String command;

    public String getCommand() {
        firstLevel();
        return command;
    }

    private void firstLevel() {
        System.out.println("Select an action:");
        System.out.println("If you want to rent good, you have to sign in");
        System.out.println("1.Show available goods");
        System.out.println("2.Find good by title");
        System.out.println("3.Registration");
        System.out.println("4.Sign in");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

        switch (i) {
            case 1: command = "available_goods"; break;
            case 2: command = "find_good"; break;
            case 3: command = "registration"; break;
            case 4: command = "sign_in";break;
            default:
                System.out.println("incorrect number");
        }
    }

    public void secondLevel() {
        System.out.println("Welcome");
        System.out.println("Select an action:");
        System.out.println("1.Rent good");
        System.out.println("2.Show rented goods");
    }

}
