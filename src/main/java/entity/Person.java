package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Scanner;

@MappedSuperclass
public class Person implements Serializable {
    @Column(name = "driver_name")
    protected String fullName;
    @Column(name = "driver_address")
    protected String address;
    @Column(name = "driver_phone")
    protected String phone;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Person() {
    }

    public Person(String fullName, String address, String phone) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }
    public void inputInfor(){
        System.out.println("Nhập họ tên:");
        this.fullName = new Scanner(System.in).nextLine();
        System.out.println("Nhập địa chỉ:");
        this.address = new Scanner(System.in).nextLine();
        System.out.println("Nhập số điện thoại:");
        this.phone = new Scanner(System.in).nextLine();
    }

    @Override
    public String toString() {
        return "họ tên: '" + fullName + '\'' +
                ", địa chỉ: '" + address + '\'' +
                ", số điện thoại: '" + phone + '\'';
    }
}
