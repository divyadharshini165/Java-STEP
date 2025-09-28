public class GameBattle {

    // 1. Basic attack
    public void attack(int damage) {
        System.out.println("Basic attack for " + damage + " points!");
    }

    // 2. Attack with weapon
    public void attack(int damage, String weapon) {
        System.out.println("Attacking with " + weapon + " for " + damage + " points!");
    }

    // 3. Critical hit or normal attack
    public void attack(int damage, String weapon, boolean isCritical) {
        if (isCritical) {
            System.out.println("CRITICAL HIT! " + weapon + " deals " + (damage * 2) + " points!");
        } else {
            attack(damage, weapon); // reuse previous overloaded method
        }
    }

    // 4. Team attack
    public void attack(int damage, String[] teammates) {
        int totalDamage = damage * teammates.length;
        System.out.print("Team attack with ");
        for (int i = 0; i < teammates.length; i++) {
            System.out.print(teammates[i]);
            if (i < teammates.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" for " + totalDamage + " total damage!");
    }

    public static void main(String[] args) {
        // Gaming Battle Simulation
        GameBattle battle = new GameBattle();

        // 1. Basic attack
        battle.attack(50);

        // 2. Weapon attack
        battle.attack(75, "Sword");

        // 3. Critical hit
        battle.attack(60, "Bow", true);

        // 4. Team attack
        String[] team = {"Alice", "Bob"};
        battle.attack(40, team);
    }
}
