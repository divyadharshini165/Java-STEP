// Abstract class Fruit
abstract class Fruit {
    protected String color;
    protected String taste;

    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    // Abstract method
    abstract void showDetails();
}

// Interface Edible
interface Edible {
    void nutrientsInfo();
}

// Apple class
class Apple extends Fruit implements Edible {
    private String variety;

    public Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    @Override
    void showDetails() {
        System.out.println("Apple: Color = " + color + ", Taste = " + taste + ", Variety = " + variety);
    }

    @Override
    public void nutrientsInfo() {
        System.out.println("Apple is rich in fiber and vitamin C.");
    }
}

// Mango class
class Mango extends Fruit implements Edible {
    private String variety;

    public Mango(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    @Override
    void showDetails() {
        System.out.println("Mango: Color = " + color + ", Taste = " + taste + ", Variety = " + variety);
    }

    @Override
    public void nutrientsInfo() {
        System.out.println("Mango is rich in vitamin A and C.");
    }
}

// Orange class
class Orange extends Fruit implements Edible {
    private String variety;

    public Orange(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    @Override
    void showDetails() {
        System.out.println("Orange: Color = " + color + ", Taste = " + taste + ", Variety = " + variety);
    }

    @Override
    public void nutrientsInfo() {
        System.out.println("Orange is rich in vitamin C.");
    }
}

// Banana class
class Banana extends Fruit implements Edible {
    private String variety;

    public Banana(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    @Override
    void showDetails() {
        System.out.println("Banana: Color = " + color + ", Taste = " + taste + ", Variety = " + variety);
    }

    @Override
    public void nutrientsInfo() {
        System.out.println("Banana is rich in potassium and vitamin B6.");
    }
}

// Test class
public class FruitTest {
    public static void main(String[] args) {
        Fruit[] fruits = {
            new Apple("Red", "Sweet", "Fuji"),
            new Mango("Yellow", "Sweet", "Alphonso"),
            new Orange("Orange", "Tangy", "Valencia"),
            new Banana("Yellow", "Sweet", "Cavendish")
        };

        for(Fruit fruit : fruits) {
            fruit.showDetails();
            if(fruit instanceof Edible) {
                ((Edible) fruit).nutrientsInfo();
            }
            System.out.println();
        }
    }
}
