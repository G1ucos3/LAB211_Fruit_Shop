package controller;

import common.*;
import java.util.ArrayList;
import java.util.Hashtable;
import model.Fruit;
import model.Order;
import view.Menu;

/**
 *
 * @author ASUS
 */
public class Program extends Menu {

    static String[] mChoice = {"Create Fruit", "View Orders", "Shopping(for buyer)", "Exit"};
    Create create;
    Validate validate;
    ArrayList fr;
    Hashtable ht;

    public Program() {
        super("FRUIT SHOP SYSTEM", mChoice);
        create = new Create();
        validate = new Validate();
        fr = new ArrayList<Fruit>();
        ht = new Hashtable<String, ArrayList<Order>>();
    }

    @Override
    public void execute(int n) {
            switch (n) {
                case 1:
                    create.createFruit(fr);
                    break;
                case 2:
                    create.viewOrder(ht);
                    break;
                case 3:
                    create.shopping(fr, ht);
                    break;
                case 4:
                    System.out.println("Exit!");
                    System.exit(0);
                    
            }
        
    }
}