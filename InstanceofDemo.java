package week4;

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

class Vehicle {}
class Car extends Vehicle {}
class Motorcycle extends Vehicle {}

public class InstanceofDemo {
    public static void main(String[] args) {
        Animal a1 = new Dog();       // Dog object
        Animal a2 = new Cat();       // Cat object
        Vehicle v1 = new Car();      // Car object
        Animal a3 = null;            // null reference

        // Checking Dog object
        System.out.println(a1 instanceof Dog);     // true
        System.out.println(a1 instanceof Animal);  // true

        // Checking Cat object
        System.out.println(a2 instanceof Cat);     // true
        System.out.println(a2 instanceof Dog);     // false

        // Checking Vehicle object
        System.out.println(v1 instanceof Car);         // true
        System.out.println(v1 instanceof Vehicle);     // true
        System.out.println(v1 instanceof Motorcycle);  // false

        // Checking null reference
        System.out.println(a3 instanceof Animal);  // false
    }
}