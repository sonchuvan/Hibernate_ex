package repository.rosterRepository;

import entity.BusRoute;
import entity.Driver;
import entity.roster.Roster;

import java.util.List;

public interface RosterRep {

    List<Roster> getAll();

    Roster getById(int id);

    boolean addNew(Roster roster);

    boolean update(Roster roster);

    boolean delete(Roster roster);

    List<Driver> getDistinct();

    boolean checkDriverFromDrivingList(int driverId);

    long totalRouteQuantity(Driver driver);

    Roster getBusRouteFromRoster(Driver driver, BusRoute busRoute);

}
