package service;

import entity.Driver;
import entity.Person;
import entity.roster.Roster;
import repository.rosterRepository.RosterRep;
import repository.rosterRepository.RosterRepImp;

import java.util.*;
import java.util.stream.Collectors;

public class RosterService {
    RosterRep rosterRep = new RosterRepImp();

    public void getAll() {
        List<Roster> rosters = rosterRep.getAll();
        List<Driver> drivers = rosterRep.getDistinct();
        showRosters(rosters, drivers);
    }

    public void addNewListRoster() {
        int rostQuantity = -1;
        System.out.println("Nhập số lượng lái xe: ");
        do {
            try {
                rostQuantity = new Scanner(System.in).nextInt();
                if (rostQuantity > 0) {
                    break;
                } else {
                    System.out.println("Số lượng lái xe phải lớn hơn 0");
                }
            } catch (InputMismatchException ex) {
                System.out.println("số lượng lái xe phải là số nguyên, vui lòng nhập lại.");
            }
        } while (true);
        for (int i = 0; i < rostQuantity; i++) {
            Roster roster = addNewRoster();
            rosterRep.addNew(roster);
        }
    }

    public Roster addNewRoster() {
        Roster roster = new Roster();
        roster.inputInfor();
        System.out.println("abc: " + roster.toString());
        return roster;
    }

    public void showRosters(List<Roster> rosters, List<Driver> drivers) {
        if (!(rosters == null || rosters.isEmpty())) {
            System.out.println("Danh sách phân công: ");
            for (Driver driver : drivers) {
                System.out.println("Tài xế: " + driver.getFullName());
                for (Roster roster : rosters) {
                    if (roster.getDriver().getDriverId() == driver.getDriverId()) {
                        System.out.println(roster.toString());
                    }
                }
                System.out.println("Tổng số chuyến: " + rosterRep.totalRouteQuantity(driver));
            }

        } else {
            System.out.println("không có bản ghi nào trong csdl.");
        }
    }

    public void sortByName() {
        List<Roster> rosters = rosterRep.getAll();
        List<Driver> drivers = rosterRep.getDistinct();
        drivers.sort(Comparator.comparing(Person::getFullName));
        showRosters(rosters, drivers);
    }

    public void sortByBusRouteQuantity() {
        List<Roster> rosters = rosterRep.getAll();
        rosters.sort((a, b) -> (int) rosterRep.totalRouteQuantity(b.getDriver()) - (int) rosterRep.totalRouteQuantity(a.getDriver()));
        List<Driver> drivers = new ArrayList<>();
        for (Roster r : rosters) {
            drivers.add(r.getDriver());
        }
        drivers = drivers.stream().distinct().collect(Collectors.toList());
        showRosters(rosters, drivers);
    }

    public void totalRange() {
        List<Roster> rosters = rosterRep.getAll();
        List<Driver> drivers = rosterRep.getDistinct();
        List<Float> totalRange = new ArrayList<>();
        System.out.println(drivers.toString());
        for (Driver d : drivers) {
            float total = 0;
            for (Roster roster : rosters) {
                if (d.getFullName().equals(roster.getDriver().getFullName())) {
                    System.out.println(roster.getDriver().getFullName());
                    total += (roster.getBusRoute().getRange() * roster.getRoute_quantity());
                }
            }
            totalRange.add(total);
        }
        for (int i = 0; i < drivers.size(); i++) {
            System.out.println("Tài xế: " + drivers.get(i).getFullName() + " Đã chạy " + totalRange.get(i) + "km");
        }
    }
}
