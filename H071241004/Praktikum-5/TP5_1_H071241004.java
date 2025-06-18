import java.util.Scanner;

class Hero {
    String name;
    int health;
    int attackpower;
    
    public Hero(String name, int health, int attackpower) {
        this.name = name;
        this.health = health;
        this.attackpower = attackpower;
    }

    public void attack() {
        System.out.println(name + " Menyerang dengan kekuatan " + attackpower + "!");
    }

}

class Archer extends Hero {
    public Archer(String name) {
        super(name, 100, 15);
    }
}

class Wizard extends Hero{
    public Wizard(String name) {
        super(name, 100, 20);
    }
}

class Fighter extends Hero{
    public Fighter(String name) {
        super(name, 100, 18);
    }
}

public class game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pilih karakter: ");
        System.out.println("1. Archer");
        System.out.println("2. Wizard");
        System.out.println("3. Fighter");

        System.out.print("Masukkan Pilihan : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        Hero character = null;

        switch (choice) {
            case 1:
                character = new Archer("Archer");
                break;
            case 2:
                character = new Wizard("Wizard");
                break;
            case 3:
                character = new Fighter("Fighter");
                break;
            default:
                System.out.print("Pilihan tidak tersedia");
                break;
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Serang");
            System.out.println("2. Keluar");
            System.out.print("Pilih Aksi : ");
            int action = scanner.nextInt();

            if (action == 1) {
                character.attack();
            } else if (action == 2) {
                System.out.println("Game Selesai...");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }
}
