package ru.yastrebov.warehouse333.warehouse.repository;
import ru.yastrebov.warehouse333.warehouse.entity.Item;
import java.util.List;

public interface ItemService {

    void create(Item item);

    List<Item> readAll();

    Item read(int id);

    boolean update(Item item, int id);

    boolean delete(int id);
}
