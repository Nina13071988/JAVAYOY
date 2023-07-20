import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

class ToyStore {
    private ArrayList<Toy> toys;
    private ArrayList<Toy> prizeToys;

    public ToyStore() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void drawToy() {
        double totalWeight = 0.0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }
        Random rand = new Random();
        double randNum = rand.nextDouble() * totalWeight;
        double cumulativeWeight = 0.0;
        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randNum <= cumulativeWeight) {
                prizeToys.add(toy);
                break;
            }
        }
    }

    public void getPrizeToy() {
        if (prizeToys.size() > 0) {
            Toy prizeToy = prizeToys.remove(0);
            prizeToy.setQuantity(prizeToy.getQuantity() - 1);
            try (FileWriter writer = new FileWriter("prize_toys.txt", true)) {
                writer.write(prizeToy.getName() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class toy {
    public static void main(String[] args) {
        ToyStore store = new ToyStore();
        store.addToy(new Toy(1, "Мягкая игрушка", 10, 0.2));
        store.addToy(new Toy(2, "Конструктор", 5, 0.3));
        store.addToy(new Toy(3, "Кукла", 8, 0.5));

        store.drawToy();
        store.getPrizeToy();
    }
}
