package repository.driverRepository;

import entity.Driver;

import java.util.List;

public interface DriverRep {
    List<Driver> getAll();

    Driver getById(int id);

    boolean addNew(Driver driver);

    boolean update(Driver driver);

    boolean delete(Driver driver);

    int getMaxId();

}
