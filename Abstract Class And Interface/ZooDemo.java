// Abstract class Animal
abstract class Animal {
    protected String name;
    protected String habitat;

    public Animal(String name, String habitat) {
        this.name = name;
        this.habitat = habitat;
    }

    // Abstract method
    abstract void eat();

    // Concrete method (optional)
    public void displayInfo() {
        System.out.println("Animal Name: " + name);
        System.out.println("Habitat: " + habitat);
    }
}

// Interface Soundable
interface Soundable {
    void makeSound();
}

// Dog class extending Animal and implementing Soundable
class Dog extends Animal implements Soundable {
    private String breed;

    public Dog(String name, String habitat, String breed) {
        super(name, habitat);
        this.breed = breed;
    }

    @Override
    void eat() {
        System.out.println(name + " eats dog food.");
    }

    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }

    public void displayBreed() {
        System.out.println("Breed: " + breed);
    }
}

// Test class
public class ZooDemo {
    public static void main(String[] args) {
        Dog myDog = new Dog("Buddy", "Domestic", "Labrador");

        myDog.displayInfo();    // concrete method from abstract class
        myDog.displayBreed();   // Dog-specific method
        myDog.eat();            // abstract method implementation
        myDog.makeSound();      // interface method implementation
    }
}
