// HospitalApp.java - Simplified Hospital Patient Management System

import java.util.*;

class Patient {
    String patientId, patientName, contactInfo;
    int age;
    String gender;
    List<String> medicalHistory = new ArrayList<>();
    List<String> currentTreatments = new ArrayList<>();
    static int totalPatients = 0;

    Patient(String id, String name, int age, String gender, String contact) {
        this.patientId = id; this.patientName = name; this.age = age; this.gender = gender; this.contactInfo = contact;
        totalPatients++;
    }

    void addHistory(String note) { medicalHistory.add(note); }
    void addTreatment(String t) { currentTreatments.add(t); }
    void discharge() { currentTreatments.clear(); System.out.println(patientName + " discharged."); }

    public String toString() { return patientId + " - " + patientName + " (" + age + ")"; }
}

class Doctor {
    String doctorId, doctorName, specialization;
    List<String> availableSlots = new ArrayList<>();
    int patientsHandled = 0;
    double consultationFee;
    Doctor(String id,String name,String spec,double fee){ doctorId=id; doctorName=name; specialization=spec; consultationFee=fee; }
    void addSlot(String s){ availableSlots.add(s); }
    public String toString(){ return doctorId+" - "+doctorName+" ("+specialization+")"; }
}

class Appointment {
    String appointmentId;
    Patient patient;
    Doctor doctor;
    String date, time;
    String type; // Consultation, Follow-up, Emergency
    String status; // Scheduled, Cancelled, Completed
    static int totalAppointments = 0;

    Appointment(String id, Patient p, Doctor d, String date, String time, String type) {
        this.appointmentId = id; this.patient = p; this.doctor = d; this.date = date; this.time = time; this.type = type;
        this.status = "Scheduled"; totalAppointments++;
    }

    void cancel(){ status = "Cancelled"; System.out.println("Cancelled " + appointmentId); }
    void complete(){ status = "Completed"; doctor.patientsHandled++; System.out.println("Completed " + appointmentId); }

    public String toString() {
        return appointmentId + " | " + patient.patientName + " -> " + doctor.doctorName + " | " + date + " " + time + " | " + type + " | " + status;
    }
}

class Hospital {
    static String hospitalName = "City Care Hospital";
    static int totalAppointments = 0;
    static double totalRevenue = 0.0;

    Map<String, Patient> patients = new HashMap<>();
    Map<String, Doctor> doctors = new HashMap<>();
    Map<String, Appointment> appointments = new HashMap<>();

    // appointment type multipliers
    Map<String, Double> typeMultiplier = Map.of("Consultation", 1.0, "Follow-up", 0.6, "Emergency", 2.0);

    void addPatient(Patient p){ patients.put(p.patientId, p); }
    void addDoctor(Doctor d){ doctors.put(d.doctorId, d); }

    // schedule: check doctor's slot exists (simple), create appointment
    Appointment scheduleAppointment(String appId, String pid, String did, String date, String time, String type) {
        Patient p = patients.get(pid); Doctor d = doctors.get(did);
        if(p==null||d==null) { System.out.println("Invalid patient or doctor."); return null; }
        String slot = date + " " + time;
        if(!d.availableSlots.contains(slot)) { System.out.println("Slot not available."); return null; }
        d.availableSlots.remove(slot);
        Appointment a = new Appointment(appId,p,d,date,time,type);
        appointments.put(appId,a);
        totalAppointments++; Hospital.totalAppointments++;
        System.out.println("Scheduled: " + a);
        return a;
    }

    boolean cancelAppointment(String appId) {
        Appointment a = appointments.get(appId);
        if(a==null) return false;
        if(!a.status.equals("Scheduled")) { System.out.println("Cannot cancel."); return false; }
        a.cancel();
        // return slot
        a.doctor.availableSlots.add(a.date + " " + a.time);
        return true;
    }

    double generateBill(String appId) {
        Appointment a = appointments.get(appId);
        if(a==null) return 0.0;
        double base = a.doctor.consultationFee;
        double mult = typeMultiplier.getOrDefault(a.type,1.0);
        double amount = base * mult;
        totalRevenue += amount; Hospital.totalRevenue += amount;
        System.out.println("Billed " + a.patient.patientName + " : " + amount);
        return amount;
    }

    void updateTreatment(String pid, String treatment) {
        Patient p = patients.get(pid);
        if(p!=null){ p.addTreatment(treatment); System.out.println("Added treatment for " + p.patientName); }
    }

    void dischargePatient(String pid) {
        Patient p = patients.get(pid);
        if(p!=null){ p.discharge(); }
    }

    // reports
    static String generateHospitalReport(Hospital h) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hospital: ").append(hospitalName).append("\n");
        sb.append("Total Patients: ").append(Patient.totalPatients).append("\n");
        sb.append("Total Appointments: ").append(Appointment.totalAppointments).append("\n");
        sb.append("Total Revenue: ").append(totalRevenue).append("\n");
        return sb.toString();
    }

    static Map<String, Double> getDoctorUtilization(Hospital h) {
        Map<String, Double> util = new HashMap<>();
        for(Doctor d: h.doctors.values()) {
            // utilization = patientsHandled / (patientsHandled + 1) *100 simple
            double v = d.patientsHandled==0 ? 0.0 : (d.patientsHandled / (double)(d.patientsHandled + 1)) * 100.0;
            util.put(d.doctorName, Math.round(v*100.0)/100.0);
        }
        return util;
    }

    static String getPatientStatistics(Hospital h) {
        int total = h.patients.size();
        int withHistory = 0;
        for(Patient p: h.patients.values()) if(!p.medicalHistory.isEmpty()) withHistory++;
        return "Patients: " + total + " | With history: " + withHistory;
    }
}

/* ===== Demo main ===== */
public class HospitalApp {
    public static void main(String[] args) {
        Hospital hosp = new Hospital();

        // seed doctors
        Doctor d1 = new Doctor("D01","Dr. Meera","Cardiology",800);
        d1.addSlot("2025-09-01 10:00"); d1.addSlot("2025-09-01 11:00");
        Doctor d2 = new Doctor("D02","Dr. Arun","General",400);
        d2.addSlot("2025-09-01 10:00");

        hosp.addDoctor(d1); hosp.addDoctor(d2);

        // seed patients
        Patient p1 = new Patient("P001","Asha",30,"F","99999");
        Patient p2 = new Patient("P002","Balu",45,"M","88888");
        p1.addHistory("Hypertension");
        hosp.addPatient(p1); hosp.addPatient(p2);

        // schedule appointments
        hosp.scheduleAppointment("A001","P001","D01","2025-09-01","10:00","Consultation");
        hosp.scheduleAppointment("A002","P002","D02","2025-09-01","10:00","Emergency");

        // complete and bill
        Appointment a = hosp.appointments.get("A001");
        if(a!=null){ a.complete(); hosp.generateBill("A001"); }

        Appointment a2 = hosp.appointments.get("A002");
        if(a2!=null){ a2.complete(); hosp.generateBill("A002"); }

        // update treatment & discharge
        hosp.updateTreatment("P001","Start BP medication");
        hosp.dischargePatient("P001");

        // cancel attempt (should fail if completed)
        hosp.cancelAppointment("A001");

        // reports
        System.out.println("\n--- Hospital Report ---");
        System.out.println(Hospital.generateHospitalReport(hosp));
        System.out.println("Doctor Utilization: " + Hospital.getDoctorUtilization(hosp));
        System.out.println("Patient Stats: " + Hospital.getPatientStatistics(hosp));
    }
}
