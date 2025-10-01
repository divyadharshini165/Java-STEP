// Base interface
interface Animal {
    void eat();
}

// Interface extending another interface
interface Pet extends Animal {
    void play();
}

// Class implementing the extended interface
class Dog implements Pet {
    @Override
    public void eat() {
        System.out.println("Dog is eating.");
    }

    @Override
    public void play() {
        System.out.println("Dog is playing.");
    }
}

// Test class
public class PetTest {
    public static void main(String[] args) {
        Dog myDog = new Dog();

        myDog.eat();   // from Animal interface
        myDog.play();  // from Pet interface
    }
}
