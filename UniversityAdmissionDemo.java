class Admission{
    public void admissionProcess(){
        System.out.println("General Admission Process");
    }
}
class UndergraduateAdmission extends Admission{
    @Override
    public void admissionProcess(){
        System.out.println("Merit + Entrance Test");
    }
}
class PostgraduateAdmission extends Admission{
    @Override
    public void admissionProcess(){
        System.out.println("Written Test + Interview");
    }
}
public class UniversityAdmissionDemo{
    public static void main(String[] args){
        Admission ug=new UndergraduateAdmission();
        Admission pg=new PostgraduateAdmission();
        ug.admissionProcess();
        pg.admissionProcess();
    }
}
