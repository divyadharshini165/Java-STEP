class Patient {
    String name;
    int age;
    Patient(String name, int age) {
        this.name = name;
        this.age = age;
    }
    void showDetails() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
class InPatient extends Patient {
    int roomNumber;
    InPatient(String name, int age, int roomNumber) {
        super(name, age);
        this.roomNumber = roomNumber;
    }
    void showDetails() {
        super.showDetails();
        System.out.println("Room Number: " + roomNumber);
    }
}
class OutPatient extends Patient {
    String appointmentDate;
    OutPatient(String name, int age, String appointmentDate) {
        super(name, age);
        this.appointmentDate = appointmentDate;
    }
    void showDetails() {
        super.showDetails();
        System.out.println("Appointment Date: " + appointmentDate);
    }
}
public class HospitalSystem {
    public static void main(String[] args) {
        InPatient p1 = new InPatient("Ravi", 45, 101);
        OutPatient p2 = new OutPatient("Meena", 30, "22-Aug-2025");
        p1.showDetails();
        System.out.println();
        p2.showDetails();
    }
}
