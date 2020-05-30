package ru.yastrebov.warehouse333.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yastrebov.warehouse333.warehouse.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>  {
}
