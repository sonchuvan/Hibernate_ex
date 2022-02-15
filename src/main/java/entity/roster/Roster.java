package entity.roster;

import entity.BusRoute;
import entity.Driver;
import repository.busRouteRepository.BusRouteRep;
import repository.busRouteRepository.BusRouteRepImp;
import repository.driverRepository.DriverRep;
import repository.driverRepository.DriverRepImp;
import repository.rosterRepository.RosterRep;
import repository.rosterRepository.RosterRepImp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@Entity
public class Roster implements Serializable {

    private static RosterRep rosterRep = new RosterRepImp();
    private static DriverRep driverRep = new DriverRepImp();
    private static BusRouteRep  busRouteRep = new BusRouteRepImp();

    @Id
    @ManyToOne(targetEntity = Driver.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;
    @Id
    @ManyToOne(targetEntity = BusRoute.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "bus_route_id", nullable = false)
    private BusRoute busRoute;
    @Column
    private int route_quantity;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public BusRoute getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }

    public int getRoute_quantity() {
        return route_quantity;
    }

    public void setRoute_quantity(int route_quantity) {
        this.route_quantity = route_quantity;
    }

    public Roster() {
    }

    public Roster(Driver driver, BusRoute busRoute, int route_quantity) {
        this.driver = driver;
        this.busRoute = busRoute;
        this.route_quantity = route_quantity;
    }

    @Override
    public String toString() {
        return "\t\tMã tuyến bus: " + this.busRoute.getRouteID() +
                ", số chuyến: " + this.route_quantity;
    }

    public void inputInfor() {
        System.out.println("Nhập mã tài xế cần phân công: ");
        int driverID;
        do {
            try {
                driverID = new Scanner(System.in).nextInt();
                if (rosterRep.checkDriverFromDrivingList(driverID)) {
                    System.out.println("Tài xế này đã được phân công, vui lòng chọn tài xế khác.");
                    continue;
                }
                if (driverRep.getById(driverID) != null) {
                    this.driver = driverRep.getById(driverID);
                    break;
                }
                System.out.println("Mã tại xế không tồn tại, vui lòng nhập lại");
            } catch (InputMismatchException ex) {
                System.out.println("mã tài xế phải là số nguyên, vui lòng nhập lại");
            }
        } while (true);
        System.out.println("Bạn đã chọn tài xế: "+driverRep.getById(driverID).getFullName());
        System.out.println("Nhập số lượng tuyến bus được phân công của tài xế: ");
        do {
            try {
                int busRouteQuantity = new Scanner(System.in).nextInt();
                if (busRouteQuantity <= 0) {
                    System.out.println("Số lượng tuyến phải lớn hơn 0, vui lòng nhập lại");
                    continue;
                }
                addNewRoster(this.driver,busRouteQuantity);
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Số lượng tuyến bus phải là số nguyên, vui lòng nhập lại.");
            }
        } while (true);
    }

    public void addNewRoster(Driver driver, int number) {
        for (int i = 0; i < number; i++) {
            if(totalRouteQuantity(driver) >=15){
                System.out.println("Số lượt đã lớn hơn 15, không thể phân công thêm.");
                break;
            }
            System.out.println("Nhập mã tuyến được phân công cho tài xế: ");
            do {
                try {
                    int busRouteID = new Scanner(System.in).nextInt();
                    if (busRouteRep.getById(busRouteID) == null) {
                        System.out.println("Mã tuyến không tồn tại, vui lòng nhập lại");
                        continue;
                    }
                    else if(rosterRep.getBusRouteFromRoster(driver,busRouteRep.getById(busRouteID))!=null) {
                        System.out.println("Đã nhập mã tuyến này, vui lòng chọn mã khác");
                        continue;
                    }
                    this.busRoute = busRouteRep.getById(busRouteID);
                    System.out.println("tài xế " + this.driver.getFullName() + " đã có " + totalRouteQuantity(driver));
                    System.out.println("Nhập số lượt của tuyến bus vừa nhập");
                    int routeQuantity = inputRouteQuantity(this.driver);
                    this.route_quantity = routeQuantity;
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("mã tuyến phải là số nguyên, vui long nhập lại");
                }
            } while (true);
        }
    }
    public int totalRouteQuantity(Driver driver) {
        return Integer.parseInt(String.valueOf(rosterRep.totalRouteQuantity(driver)));
    }
    public int inputRouteQuantity(Driver driver) {
        int number = -1;
        do {
            try {
                number = new Scanner(System.in).nextInt();
                if (number <= 0) {
                    System.out.println("Số lượng chuyến phải lớn hơn 0, vui lòng nhập lại");
                    continue;
                }
                if (totalRouteQuantity(driver)+number >= 15) {
                    System.out.println("đã quá số lượt quy định");
                    continue;
                }
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Số lượng chuyến phải là số nguyên, vui lòng nhập lại");
            }
        } while (true);
        return number;
    }
/*


    public void addNewListRouteDriving(int busRouteNumber) {
        for (int i = 0; i < busRouteNumber; i++) {
            if(totalRouteQuantity() >=15){
                System.out.println("Số lượt đã lớn hơn 15, không thể phân công thêm.");
                break;
            }
            System.out.println("Nhập mã tuyến được phân công cho tài xế: ");
            do {
                try {
                    int busRouteID = new Scanner(System.in).nextInt();
                    if (!checkBusRouteFromList(busRouteID)) {
                        System.out.println("Mã tuyến không tồn tại, vui lòng nhập lại");
                        continue;
                    }
                    if (checkBusRouteFromDriver(busRouteID)) {
                        System.out.println("Đã nhập mã tuyến này, vui lòng chọn mã khác");
                        continue;
                    }
                    System.out.println("tài xế " + this.driver.getFullName() + " đã có " + totalRouteQuantity());
                    System.out.println("Nhập số lượt của tuyến bus vừa nhập");
                    int number = inputNumberOfRoute();
                    routeDrivingList.add(new RouteDriving(getBusRouteByID(busRouteID), number));
                    break;
                } catch (InputMismatchException ex) {
                    System.out.println("mã tuyến phải là số nguyên, vui long nhập lại");
                }
            } while (true);
        }
    }



    public boolean checkBusRouteFromList(int busRouteID) {
        for (BusRoute busRoute : Main.busRouteDBService.getListBusRouteFromDB()) {
            if (busRoute.getRouteID() == busRouteID) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBusRouteFromDriver(int busRouteID) {
        for (RouteDriving routeDriving : this.routeDrivingList) {
            if (routeDriving.getBusRoute().getRouteID() == busRouteID) {
                return true;
            }
        }
        return false;
    }

    public BusRoute getBusRouteByID(int busRouteID) {
        BusRoute busRouteTemp = new BusRoute();
        for (BusRoute busRoute : Main.busRouteDBService.getListBusRouteFromDB()) {
            if (busRoute.getRouteID() == busRouteID) {
                busRouteTemp = busRoute;
                return busRouteTemp;
            }
        }
        return busRouteTemp;
    }

    public boolean checkDriverFromDriverList(int driverID) {
        for (Driver driver : Main.driverDBService.getListDriverFromDB()) {
            if (driver.getDriverId() == driverID) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDriverFromDrivingList(int id) {
        for (Roster driving : Main.drivingDBService.getListDrivingFromDB()) {
            if (driving.driver.getDriverId() == id) {
                return true;
            }
        }
        return false;
    }

    public Driver getDriverByID(int driverID) {
        Driver driverTemp = new Driver();
        for (Driver driver : Main.driverDBService.getListDriverFromDB()) {
            if (driver.getDriverId() == driverID) {
                driverTemp = driver;
                return driverTemp;
            }
        }
        return driverTemp;
    }

    public String showListRouteDriving(List<RouteDriving> routeDriving) {
        String s = "";
        for (RouteDriving r : routeDriving) {
            s += ("\n\t\t mã tuyến: " + r.getBusRoute().getRouteID() + " Số lượng chuyến: " + r.getRouteQuantity());
        }
        return s;
    }
 */
}
