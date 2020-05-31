package ru.yastrebov.warehouse333.warehouse.repository;
import org.springframework.stereotype.Service;
import ru.yastrebov.warehouse333.warehouse.entity.Location;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void create(Location location) {
        locationRepository.save(location);
    }

    @Override
    public List<Location> readAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location read(int id) {
        return locationRepository.getOne(id);
    }

    @Override
    public boolean update(Location location, int id) {
        if (locationRepository.existsById(id)) {
            location.setId(id);
            locationRepository.save(location);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
