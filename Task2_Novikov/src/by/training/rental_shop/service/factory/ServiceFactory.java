package by.training.rental_shop.service.factory;

import by.training.rental_shop.service.ClientService;
import by.training.rental_shop.service.ShopService;
import by.training.rental_shop.service.impl.ClientServiceImpl;
import by.training.rental_shop.service.impl.ShopServiceImpl;

/**
 * Created by Евгений on 13.01.2017.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();
    private final ShopService shopService = new ShopServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public ShopService getShopService() {
        return shopService;
    }
}
