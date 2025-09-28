// Immutable Class
final class Person {

    // step 1: private + final fields
    private final String name;
    private final int birthYear;

    // step 2: Initialize through constructor
    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    // step 3: Only getters (no setters!)
    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }
};
public class ImmutableDemo{
    public static void main(String[] args){
        Person p = new Person("keerthi",2000);
        System.out.println("name:"+p.getName());
        System.out.println("birth year:"+p.getBirthYear());
    }
};