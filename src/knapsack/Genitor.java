/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tcc10a
 */
public class Genitor {
    private static Items knapsack;
    private static List<Solution> pop;
    private static List<Solution> lastBest;
    
    public Genitor(Items items) {
        knapsack = items;
        pop = new ArrayList<>();
        lastBest = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            pop.add(new Solution(knapsack.size()));
        }
    }
    
    public void rank() {
        List<Solution> sorted = new ArrayList<>();
        for (int j = 0; j < pop.size(); j++) {
            Solution bestSoFar = pop.get(0);
            for (int i = 0; i < pop.size()-1; i++) {
                if (pop.get(i).getFitness() > pop.get(i+1).getFitness()) {
                    bestSoFar = pop.get(i+1);
                }
            }
            pop.remove(bestSoFar);
            sorted.add(bestSoFar);
        }
        pop = sorted;
    }
    
    public static boolean stagnated() {
        for (int i = 1; i < pop.size(); i++) {
            if (!pop.get(i - 1).equals(pop.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static void cataclysmicMutation() {
        Solution keep = pop.get(0);
        pop = new ArrayList<>();
        pop.add(keep);
        for (int i = 0; i < 99; i++) {
            Solution k = new Solution(keep.getSet().size());
            k.mutate();
            pop.add(k);
        }
    }
    
    public Solution go() {
        Solution best = null;
        while (true) {
            rank();
            if (pop.get(0).getFitness() == 0) {
                best = pop.get(0);
                break;
            }
            if (stagnated() && !lastBest.isEmpty()) {
                if (lastBest.get(0).equals(pop.get(0))) {
                    if (lastBest.size() > 1 && lastBest.get(1).equals(pop.get(0))) {
                        best = pop.get(0);
                        break;
                    } else {
                        lastBest = new ArrayList<>();
                        lastBest.add(pop.get(0));
                    }
                } else {
                    lastBest = new ArrayList<>();
                    lastBest.add(pop.get(0));
                }
                cataclysmicMutation();
            } else if (stagnated() && lastBest.isEmpty()) {
                lastBest.add(pop.get(0));
                cataclysmicMutation();
            }
            //Now do the fun stuff.
            int n = pop.size();
            int a;
            int b;

            int rand1 = 0 + (int) (Math.random() * (n + 1));
            int rand2 = 0 + (int) (Math.random() * (n + 1));
            if (rand1 > rand2) {
                a = rand2;
            } else {
                a = rand1;
            }

            rand1 = 0 + (int) (Math.random() * (n + 1));
            rand2 = 0 + (int) (Math.random() * (n + 1));
            if (rand1 > rand2) {
                b = rand2;
            } else {
                b = rand1;
            }
            if (a == n) {
                a -= 1;
            } else if (a < 0) {
                a = 0;
            }
            if (b == n) {
                b -= 1;
            } else if (b < 0) {
                b = 0;
            }

            Solution child = new Solution(pop.get(a), pop.get(b));
            child.mutate();
            if (child.getFitness() > pop.get(pop.size() - 1).getFitness()) {
                pop.remove(pop.size() - 1);
                pop.add(child);
            }
        }
        return best;
    }
}
