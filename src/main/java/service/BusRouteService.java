package service;

import entity.BusRoute;
import repository.busRouteRepository.BusRouteRep;
import repository.busRouteRepository.BusRouteRepImp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BusRouteService {
    static BusRouteRep busRouteRep = new BusRouteRepImp();

    public void getAll(){
        List<BusRoute> busRoutes = busRouteRep.getAll();
        if (!(busRoutes == null || busRoutes.isEmpty())){
            System.out.println("Danh sách sinh viên: ");
            busRoutes.forEach(System.out::println);
        }else {
            System.out.println("không có bản ghi nào trong csdl.");
        }
    }

    public void addNewListBusRoute() {
        System.out.println("Nhập số tuyến bus muốn thêm:");
        do {
            try {
                int number = new Scanner(System.in).nextInt();
                if (number > 0) {
                    for (int i = 0; i < number; i++) {
                        BusRoute busRoute = addNewBusRoute();
                        busRouteRep.addNew(busRoute);
                    }
                    break;
                }
                System.out.println("Số tuyến bus phải lớn hơn 0, vui lòng nhập lại.");
            } catch (InputMismatchException ex) {
                System.out.println("Số lượng tuyến bus phải là số nguyên, vui lòng nhập lại");
            }
        } while (true);
    }

    public BusRoute addNewBusRoute(){
        BusRoute busRoute = new BusRoute();
        busRoute.inputInfor();
        return busRoute;
    }

    public int getMaxId(){
        return busRouteRep.getMaxId();
    }
}
