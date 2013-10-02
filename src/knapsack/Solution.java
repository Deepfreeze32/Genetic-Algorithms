package knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Solution class.
 * @author Deepfreeze32
 */
public class Solution {
    private Random rand;
    private static List<Boolean> set;
    private static int fitness;
    
    public Solution(int n) {
        rand = new Random();
        set = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            set.add(getBool(getRand(0,1)));
        }
    }
    
    public void fitness(Items knapsack) {
        fitness = knapsack.evaluate(set);
    }
    
    public List<Boolean> getSet() {
        return set;
    }
    
    public void mutate() {
        int chance = getRand(0,99);
        if (chance < 3) {
            int bitToFlip = getRand(0,set.size()-1);
            set.set(bitToFlip,!set.get(bitToFlip));
        }
    }
    
    public Solution(Solution a, Solution b) {
        int cross = getRand(1,set.size()-1);
        int size = set.size();
        set.clear();
        for (int i = 0; i < size; i++) {
            if (i < cross) {
                set.add(a.getSet().get(i));
            } else {
                set.add(b.getSet().get(i));
            }
        }
    }
    
    public int getFitness() {
        return fitness;
    }
    
    private boolean getBool(int n) {
        if (n != 0) {
            return true;
        }
        return false;
    }
    
    private int getRand(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
}