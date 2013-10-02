package knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * The item collection class. 
 * @author Deepfreeze32
 */
public class Items {
    private static List<String> names;
    private static List<Integer> weights;
    private static List<Integer> values;
    private static int cap;
    
    public Items() {
        names = new ArrayList<>();
        weights = new ArrayList<>();
        values = new ArrayList<>();
    }
    
    public static void add(int weight, int value, String name) {
        names.add(name);
        values.add(value);
        weights.add(weight);
    }
    
    public int evaluate(List<Boolean> set) {
        int fitness = 0;
        int intCap = 0;
        if (set.size() != weights.size()) {
            return fitness;
        }
        for (int i = 0; i < weights.size(); i++) {
            if (set.get(i)) {
                intCap += weights.get(i);
                fitness += values.get(i);
            }
            if (intCap > cap) {
                fitness = 0;
                break;
            }
        }
        return fitness;
    }
    
    public void printPack(List<Boolean> set) {
        System.out.println("Items taken:\n");
        for (int i = 0; i < set.size(); i++) {
            if (set.get(i)) {
                System.out.println("Name: "+names.get(i)+"; Weight: "+weights.get(i)+"; value: "+values.get(i));
            }
        }
    }
    
    public void setCap(int c) {
        cap = c;
    }
    
    public int getCap() {
        return cap;
    }
    
    public int size() {
        return values.size();
    }
}
