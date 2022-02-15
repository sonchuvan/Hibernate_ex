package repository.busRouteRepository;

import entity.BusRoute;

import java.util.List;

public interface BusRouteRep {

    List<BusRoute> getAll();

    BusRoute getById(int id);

    boolean addNew(BusRoute busRoute);

    boolean update(BusRoute busRoute);

    boolean delete(BusRoute busRoute);

    int getMaxId();
}
