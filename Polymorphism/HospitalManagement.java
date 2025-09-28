// Parent class
class MedicalStaff {
    protected String name;
    protected String staffId;

    public MedicalStaff(String name, String staffId) {
        this.name = name;
        this.staffId = staffId;
    }

    public void scheduleShift(String shiftTime) {
        System.out.println(name + " (ID: " + staffId + ") scheduled for shift: " + shiftTime);
    }

    public void accessIDCard() {
        System.out.println(name + " accessed ID card system.");
    }

    public void processPayroll() {
        System.out.println("Payroll processed for " + name + " (ID: " + staffId + ")");
    }
}

// Doctor subclass
class Doctor extends MedicalStaff {
    public Doctor(String name, String staffId) {
        super(name, staffId);
    }

    public void diagnosePatient(String patient) {
        System.out.println("Doctor " + name + " is diagnosing patient: " + patient);
    }

    public void performSurgery(String patient) {
        System.out.println("Doctor " + name + " is performing surgery on: " + patient);
    }

    public void prescribeMedicine(String patient, String medicine) {
        System.out.println("Doctor " + name + " prescribes " + medicine + " to " + patient);
    }
}

// Nurse subclass
class Nurse extends MedicalStaff {
    public Nurse(String name, String staffId) {
        super(name, staffId);
    }

    public void administerMedicine(String patient, String medicine) {
        System.out.println("Nurse " + name + " administers " + medicine + " to " + patient);
    }

    public void monitorPatient(String patient) {
        System.out.println("Nurse " + name + " is monitoring patient: " + patient);
    }

    public void assistProcedure(String procedure) {
        System.out.println("Nurse " + name + " assists in " + procedure);
    }
}

// Technician subclass
class Technician extends MedicalStaff {
    public Technician(String name, String staffId) {
        super(name, staffId);
    }

    public void operateEquipment(String equipment) {
        System.out.println("Technician " + name + " operates " + equipment);
    }

    public void runTest(String test) {
        System.out.println("Technician " + name + " runs test: " + test);
    }

    public void maintainInstruments(String instrument) {
        System.out.println("Technician " + name + " maintains " + instrument);
    }
}

// Administrator subclass
class Administrator extends MedicalStaff {
    public Administrator(String name, String staffId) {
        super(name, staffId);
    }

    public void scheduleAppointment(String patient) {
        System.out.println("Administrator " + name + " schedules appointment for: " + patient);
    }

    public void manageRecords(String recordType) {
        System.out.println("Administrator " + name + " manages records: " + recordType);
    }
}

// Test class
public class HospitalManagement {
    public static void main(String[] args) {
        // Upcasting: all stored as MedicalStaff
        MedicalStaff[] staffList = new MedicalStaff[4];
        staffList[0] = new Doctor("Dr. Alice", "D101");
        staffList[1] = new Nurse("Nurse Bob", "N202");
        staffList[2] = new Technician("Tech Carol", "T303");
        staffList[3] = new Administrator("Admin Dave", "A404");

        // Common operations (available via parent class)
        for (MedicalStaff staff : staffList) {
            staff.scheduleShift("Morning");
            staff.accessIDCard();
            staff.processPayroll();
            System.out.println("--------------------------------");
        }

        // Specialized operations using downcasting
        System.out.println(" Specialized Duties:");
        ((Doctor) staffList[0]).diagnosePatient("Patient X");
        ((Doctor) staffList[0]).performSurgery("Patient Y");
        ((Doctor) staffList[0]).prescribeMedicine("Patient Z", "Paracetamol");

        ((Nurse) staffList[1]).administerMedicine("Patient X", "Ibuprofen");
        ((Nurse) staffList[1]).monitorPatient("Patient Y");
        ((Nurse) staffList[1]).assistProcedure("Surgery A");

        ((Technician) staffList[2]).operateEquipment("MRI Scanner");
        ((Technician) staffList[2]).runTest("Blood Test");
        ((Technician) staffList[2]).maintainInstruments("Ventilator");

        ((Administrator) staffList[3]).scheduleAppointment("Patient X");
        ((Administrator) staffList[3]).manageRecords("Lab Reports");
    }
}
