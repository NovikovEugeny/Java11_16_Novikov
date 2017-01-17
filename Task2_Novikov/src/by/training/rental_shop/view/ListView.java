package by.training.rental_shop.view;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.service.ShopService;

import java.util.List;

/**
 * Created by Евгений on 15.01.2017.
 */
public interface ListView {
    void printList(List<SportEquipment> list);
}
