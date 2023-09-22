package common;

import java.util.ArrayList;
import java.util.Scanner;
import model.Fruit;
import model.Order;

/**
 *
 * @author ASUS
 */
public class Validate {

    private static final Scanner sc = new Scanner(System.in);

    public boolean checkIdExist(ArrayList<Fruit> fr, String id) {
        for (Fruit fruit : fr) {
            if (fruit.getFruitID().equalsIgnoreCase(id)) {
                System.err.println("Id exist!");
                return false;
            }
        }
        return true;
    }

    public String checkString() {
        while (true) {
            String result = sc.nextLine();
            if (result.isBlank() || result.isEmpty()) {
                System.err.println("Empty string!");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public int inputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Input integer!");
                System.out.print("Enter again: ");
            }
        }
    }

    public double inputDouble() {
        while (true) {
            try {
                double result = Double.parseDouble(sc.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Input double!");
                System.out.print("Enter again: ");
            }
        }
    }

    public boolean checkItemExist(ArrayList<Order> od, String id) {
        for (Order order : od) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                return false;
            }
        }
        return true;
    }

    public boolean inputYN() {
        while (true) {
            String result = checkString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Only Y or N");
            System.out.print("Enter again: ");
        }
    }

    public int checkLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Input number in range (" + min + ", " + max + ")");
                System.out.print("Enter again: ");
            }
        }
    }
}
