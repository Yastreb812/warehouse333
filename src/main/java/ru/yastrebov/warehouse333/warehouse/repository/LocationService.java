package ru.yastrebov.warehouse333.warehouse.repository;
import ru.yastrebov.warehouse333.warehouse.entity.Location;
import java.util.List;

public interface LocationService {

    void create(Location location);

    List<Location> readAll();

    Location read(int id);

    boolean update(Location location, int id);

    boolean delete(int id);
}

