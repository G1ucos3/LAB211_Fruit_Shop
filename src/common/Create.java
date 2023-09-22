package common;

import java.util.*;
import model.*;

/**
 *
 * @author ASUS
 */
public class Create {

    public void createFruit(ArrayList<Fruit> fr) {
        Validate validate = new Validate();
        while (true) {
            System.out.print("Enter fruit id: ");
            String fruitId = validate.checkString();
            if (!validate.checkIdExist(fr, fruitId)) {
                return;
            }
            System.out.print("Enter fruit name: ");
            String fruitName = validate.checkString();
            System.out.print("Enter price: ");
            double price = validate.inputDouble();
            System.out.print("Enter quantity: ");
            int quantity = validate.inputInt();
            System.out.print("Enter origin: ");
            String origin = validate.checkString();
            fr.add(new Fruit(fruitId, fruitName, price, quantity, origin));
            System.out.println("Do you want to add more? (Y/N): ");
            if (!validate.inputYN()) {
                return;
            }
        }
    }

    public void viewOrder(Hashtable<String, ArrayList<Order>> ht) {
        if(ht.isEmpty()){
            System.out.println("No order!");
        }
        for (String name : ht.keySet()) {
            System.out.println("Customer: " + name);
            ArrayList<Order> lo = ht.get(name);
            displayListOrder(lo);
        }
    }

    public void shopping(ArrayList<Fruit> fr, Hashtable<String, ArrayList<Order>> ht) {
        Validate validate = new Validate();
        if (fr.isEmpty()) {
            System.out.println("Empty!");
            return;
        }
        ArrayList<Order> od = new ArrayList<>();
        while (true) {
            displayListFruit(fr);
            System.out.print("Enter item: ");
            int item = validate.checkLimit(1, fr.size());
            Fruit fruit = getFruitByItem(fr, item);
            System.out.print("Enter quantity: ");
            int quantity = validate.checkLimit(1, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            if (!validate.checkItemExist(od, fruit.getFruitID())) {
                updateOrder(od, fruit.getFruitID(), quantity);
            } else {
                od.add(new Order(fruit.getFruitID(), fruit.getFruitName(),
                        quantity, fruit.getPrice()));
            }
            System.out.print("Do you want to buy more? (Y/N): ");
            if (!validate.inputYN()) {
                break;
            }
        }
        displayListOrder(od);
        System.out.print("Enter name: ");
        String name = validate.checkString();
        ht.put(name, od);
        System.out.println("Add successfull!");
    }

    public void displayListFruit(ArrayList<Fruit> fr) {
        int count = 1;
        System.out.printf("%-15s%-10s%-10s%-10s\n", "| ++ Item ++ |", " ++ Fruit name ++ |", " ++ Origin ++ |", " ++ Price ++ |");
        for (Fruit fruit : fr) {
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-7s%-15d%-18s%-15s%-1.0f$\n", "",count++,
                        fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice());
            }
        }
    }

    public Fruit getFruitByItem(ArrayList<Fruit> fr, int item) {
        int count = 1;
        for (Fruit fruit : fr) {
            if (fruit.getQuantity() != 0) {
                count++;
            }
            if (count - 1 == item) {
                return fruit;
            }
        }
        return null;
    }

    public void displayListOrder(ArrayList<Order> od) {
        double total = 0;
        System.out.printf("%-2s%-2s%-2s%-1s\n", "Product |", " Quantity |", " Price |", " Amount");
        for (Order order : od) {
            System.out.printf("%-3s%-10s%-6d%5.0f$%6.0f$\n", "", order.getFruitName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
        }
        System.out.println("Total: " + total);
    }

    public void updateOrder(ArrayList<Order> od, String id, int quantity) {
        for (Order order : od) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }
}
