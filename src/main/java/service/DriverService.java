package service;

import entity.Driver;
import repository.driverRepository.DriverRep;
import repository.driverRepository.DriverRepImp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DriverService {

    static DriverRep driverRep = new DriverRepImp();

    public void getAll(){
        List<Driver> drivers = driverRep.getAll();
        if (!(drivers == null || drivers.isEmpty())){
            System.out.println("Danh sách lái xe: ");
            drivers.forEach(System.out::println);
        }else {
            System.out.println("không có bản ghi nào trong csdl.");
        }
    }

    public void addNewListDriver(){
        int driverNumber = -1;
        System.out.println("Nhập số lượng lái xe: ");
        do {
            try {
                driverNumber = new Scanner(System.in).nextInt();
                if (driverNumber > 0) {
                    break;
                }else {
                    System.out.println("Số lượng lái xe phải lớn hơn 0");
                }
            } catch (InputMismatchException ex) {
                System.out.println("số lượng lái xe phải là số nguyên, vui lòng nhập lại.");
            }
        } while (true);
        for (int i = 0; i < driverNumber; i++) {
            Driver driver = addNewDriver();
            driverRep.addNew(driver);
        }
    }

    public Driver addNewDriver() {
        Driver driver = new Driver();
        driver.inputInfor();
        return driver;
    }

    public int getMaxId(){
        return driverRep.getMaxId();
    }
}
