package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@Entity
@Table(name = "driver")
public class Driver extends Person implements Serializable {
    public static int COUNT;

    @Id
    @Column(name = "driver_id")
    private int driverId;
    @Transient
    public static final String A = "A";
    @Transient
    public static final String B = "B";
    @Transient
    public static final String C = "C";
    @Transient
    public static final String D = "D";
    @Transient
    public static final String E = "E";
    @Transient
    public static final String F = "F";
    @Column(name = "driver_level")
    private String level;

    public int getDriverId() {
        return driverId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Driver() {
    }

    public Driver(String fullName, String address, String phone, String level) {
        super(fullName, address, phone);
        this.level = level;
    }

    @Override
    public void inputInfor() {
        this.driverId = Driver.COUNT;
        System.out.println("Nhập thông tin của lái xe có mã "+this.getDriverId());
        Driver.COUNT++;
        super.inputInfor();
        System.out.println("Nhập trình độ lái xe:");
        System.out.println("1. Lái xe hạng A");
        System.out.println("2. Lái xe hạng B");
        System.out.println("3. Lái xe hạng C");
        System.out.println("4. Lái xe hạng D");
        System.out.println("5. Lái xe hạng E");
        System.out.println("6. Lái xe hạng F");
        int level;
        do {
            try {
                level = new Scanner(System.in).nextInt();
                if (level >= 1 && level <= 6){
                    switch (level) {
                        case 1: this.level = A;break;
                        case 2: this.level = B;break;
                        case 3: this.level = C;break;
                        case 4: this.level = D;break;
                        case 5: this.level = E;break;
                        case 6: this.level = F;break;
                    }
                    break;
                }
                System.out.println("Lựa chọn phải là số nguyên từ 1 đến 6, vui lòng nhập lại.");
            } catch (InputMismatchException ex) {
                System.out.println("Lựa chọn phải là số nguyên, vui lòng nhập lại");
            }
        } while (true);
    }

    public Driver( int driverId, String fullName, String address, String phone, String level) {
        super(fullName, address, phone);
        this.driverId = driverId;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Mã tài xế: " + driverId +" "+super.toString()+
                ", trình độ: '" + level + '\'';
    }
}
