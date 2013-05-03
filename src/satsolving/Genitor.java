/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package satsolving;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tcc10a
 */
public class Genitor {
    private static List<Solution> solutions;
    private static List<String> clauses;
    private static List<Solution> lastBest;

    public Genitor(int n, List<String> clause) {
        //Generate initial populations\
        clauses = clause;
        solutions = new ArrayList<>();
        lastBest = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Solution s = new Solution(n,clauses);
            solutions.add(s);
        }
    }

    public static void rank() {
        List<Solution> sorted = new ArrayList<>();
        for (int j = 0; j < solutions.size(); j++) {
            Solution bestSoFar = solutions.get(0);
            for (int i = 0; i < solutions.size()-1; i++) {
                if (solutions.get(i).getFitness() > solutions.get(i+1).getFitness()) {
                    bestSoFar = solutions.get(i+1);
                }
            }
            solutions.remove(bestSoFar);
            sorted.add(bestSoFar);
        }
        solutions = sorted;
    }

    public static boolean stagnated() {
        for (int i = 1; i < solutions.size(); i++) {
            if (!solutions.get(i - 1).equals(solutions.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void cataclysmicMutation() {
        Solution keep = solutions.get(0);
        solutions = new ArrayList<>();
        solutions.add(keep);
        for (int i = 0; i < 99; i++) {
            Solution k = new Solution(keep.getVars().size(),clauses);
            k.mutate();
            solutions.add(k);
        }
    }


    public static Solution go() throws Exception {
        Solution best = null;
        while (true) {
            rank();
            
            if (solutions.get(0).getFitness() == 0) {
                best = solutions.get(0);
                break;
            }
            if (stagnated() && !lastBest.isEmpty()) {
                if (lastBest.get(0).equals(solutions.get(0))) {
                    if (lastBest.size() > 1 && lastBest.get(1).equals(solutions.get(0))) {
                        best = solutions.get(0);
                        break;
                    } else {
                        lastBest = new ArrayList<>();
                        lastBest.add(solutions.get(0));
                    }
                } else {
                    lastBest = new ArrayList<>();
                    lastBest.add(solutions.get(0));
                }
                cataclysmicMutation();
            } else if (stagnated() && lastBest.isEmpty()) {
                lastBest.add(solutions.get(0));
                cataclysmicMutation();
            }
            //Now do the fun stuff.
            int n = solutions.size();
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

            Solution child = new Solution(solutions.get(a), solutions.get(b));
            child.normalMutate();
            if (child.getFitness() > solutions.get(solutions.size() - 1).getFitness()) {
                solutions.remove(solutions.size() - 1);
                solutions.add(child);
            }
        }
        return best;
    }
}
