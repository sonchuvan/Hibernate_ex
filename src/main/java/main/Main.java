package main;

import entity.BusRoute;
import entity.Driver;
import service.BusRouteService;
import service.DriverService;
import service.RosterService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static DriverService driverService = new DriverService();
    static BusRouteService busRouteService = new BusRouteService();
    static RosterService rosterService = new RosterService();

    public static void main(String[] args) {
        initializeData();
        menu();
    }

    private static void initializeData(){
        BusRoute.COUNT = busRouteService.getMaxId();
        Driver.COUNT = driverService.getMaxId();
    }

    public static int choice() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("QUẢN LÝ PHÂN CÔNG LÁI XE BUS");
        System.out.println("1. Nhập danh sách lái xe mới và in ra danh sách lái xe");
        System.out.println("2. Nhập danh sách tuyến xe và in ra danh sách tuyến xe");
        System.out.println("3. Nhập danh sách phân công cho mỗi lái xe");
        System.out.println("4. Sắp xếp danh sách phân công theo tên lái xe");
        System.out.println("5. sắp xếp danh sách theo số lượng tuyến đảm nhiệm trong ngày");
        System.out.println("6. Lập bảng thống kê khoảng cách chạy xe của mỗi lái xe");
        System.out.println("0. kết thúc chương trình");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Mời chọn chức năng");
        do {
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice >= 0 && choice <= 6){
                    return choice;
                }
                System.out.println("vui lòng nhập lại lựa chọn là một số nguyên từ 0 đến 6");
            } catch (InputMismatchException ex) {
                System.out.println("Chức năng phải là một số nguyên, vui lòng nhập lại");
            }
        } while (true);
    }

    public static void menu(){
        do {
            int choice = choice();
            switch (choice){
                case 1:
                    driverService.addNewListDriver();
                    driverService.getAll();
                    break;
                case 2:

                    busRouteService.addNewListBusRoute();
                    busRouteService.getAll();
                    break;
                case 3:
                    //rosterService.addNewListRoster();
                    rosterService.getAll();
                    break;
                case 4:
                    rosterService.sortByName();
                    break;
                case 5:
                    rosterService.sortByBusRouteQuantity();
                    break;
                case 6:
                    rosterService.totalRange();
                    break;
                case 0:
                    System.exit(0);
            }
        }while (true);
    }
}
