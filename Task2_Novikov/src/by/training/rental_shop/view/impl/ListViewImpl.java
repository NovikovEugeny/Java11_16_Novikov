package by.training.rental_shop.view.impl;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.view.ListView;

import java.util.List;

/**
 * Created by Евгений on 15.01.2017.
 */
public class ListViewImpl implements ListView {
    @Override
    public void printList(List<SportEquipment> list) {
        for (SportEquipment s : list) {
            String str = s.getCategory() + " | " + s.getTitle() + " | " +
                   s.getPrice(); ;
            System.out.println(str);
        }
    }
}
