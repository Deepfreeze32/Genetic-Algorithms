/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author tcc10a
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
