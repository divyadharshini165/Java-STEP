// Abstract class Shape
abstract class Shape {
    protected double area;
    protected double perimeter;

    // Abstract methods
    abstract void calculateArea();
    abstract void calculatePerimeter();
}

// Interface Drawable
interface Drawable {
    void draw();
}

// Circle class extending Shape and implementing Drawable
class Circle extends Shape implements Drawable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    void calculateArea() {
        area = Math.PI * radius * radius;
    }

    @Override
    void calculatePerimeter() {
        perimeter = 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }

    public void display() {
        System.out.println("Circle Area: " + area);
        System.out.println("Circle Perimeter: " + perimeter);
    }
}

// Public test class with updated filename
public class CircleDemo {
    public static void main(String[] args) {
        Circle c = new Circle(5);

        c.calculateArea();
        c.calculatePerimeter();
        c.draw();
        c.display();
    }
}
