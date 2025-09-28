class Shape{
    public double area(){
        return 0;
    }
}
class Circle extends Shape{
    private double radius;
    Circle(double radius){
        this.radius=radius;
    }
    @Override
    public double area(){
        return Math.PI*radius*radius;
    }
}
class Rectangle extends Shape{
    private double length,width;
    Rectangle(double length,double width){
        this.length=length;
        this.width=width;
    }
    @Override
    public double area(){
        return length*width;
    }
}
public class ShapeAreaDemo{
    public static void main(String[] args){
        Shape c=new Circle(5);
        Shape r=new Rectangle(4,6);
        System.out.println("Circle Area:"+c.area());
        System.out.println("Rectangle Area:"+r.area());
    }
}
