/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package satsolving;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The candidate solution class for Sat Solving.
 * @author tcc10a
 */
public class Solution {
    private List<String> clauses;
    private Random rand;
    private int fitness;
    private static List<Boolean> variables;
    
    public Solution(int totalVars,List<String> cl) {
        clauses = cl;
        variables = new ArrayList<>();
        for (int i = 0; i < totalVars; i++) {
            variables.add(generateBool());
        }
    }
    
    public Solution(Solution parentA, Solution parentB) {
        
    }
    
    public static List<Boolean> getVars() {
        return variables;
    } 
    
    public static boolean generateBool() {
        int k = 0 + (int)(Math.random() * (1 + 1));
        return (k != 0);
    }
    
    private int getRand(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
    
    public void normalMutate() {
        int chance = getRand(1,100);
        if (chance <= 3) {
            int k = getRand(1, variables.size())-1;
            variables.set(k,generateBool());
        }
        fitness();
    }

    public void mutate() {
        for (Boolean k : variables) {
            int mute = getRand(1, 2);
            if (mute == 1) {
                //mutate this bit
                k = generateBool();
            }
        }
        fitness();
    }

    public int getFitness() {
        return fitness;
    }

    private void fitness() {
        fitness = 0;
        
    }
}
