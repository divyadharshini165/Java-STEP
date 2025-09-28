// Base class
abstract class Character {
    protected String name;
    protected int health;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // Attack method - to be overridden
    public abstract void attack();
}

// Warrior class
class Warrior extends Character {
    public Warrior(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack() {
        System.out.println("Warrior " + name + " swings a mighty sword! High defense engaged.");
    }
}

// Mage class
class Mage extends Character {
    private int mana;

    public Mage(String name, int health, int mana) {
        super(name, health);
        this.mana = mana;
    }

    @Override
    public void attack() {
        System.out.println("Mage " + name + " casts a powerful spell using mana! Remaining mana: " + (mana - 10));
    }
}

// Archer class
class Archer extends Character {
    private int arrows;

    public Archer(String name, int health, int arrows) {
        super(name, health);
        this.arrows = arrows;
    }

    @Override
    public void attack() {
        System.out.println("🏹 Archer " + name + " shoots a long-range arrow! Arrows left: " + (arrows - 1));
    }
}

// Main class renamed
public class BattleSimulation {
    public static void main(String[] args) {
        // Mixed army array (base type reference)
        Character[] army = new Character[3];
        army[0] = new Warrior("Thor", 150);
        army[1] = new Mage("Merlin", 100, 50);
        army[2] = new Archer("Robin", 120, 20);

        // Same method call, but runtime decides behavior
        for (Character c : army) {
            c.attack(); // Dynamic Method Dispatch in action
        }
    }
}

