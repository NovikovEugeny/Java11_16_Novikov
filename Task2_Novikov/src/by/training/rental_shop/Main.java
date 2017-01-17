package by.training.rental_shop;

import by.training.rental_shop.controller.Controller;

/**
 * Created by Евгений on 07.01.2017.
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(controller.executeTask());
    }
}
