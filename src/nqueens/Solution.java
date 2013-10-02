package nqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The candidate solution class for the N-Queens GA.
 *
 * @author Deepfreeze32
 */
public class Solution {

    public static Random rand;
    public static List<Integer> columns;
    public static int fitness;

    public Solution(int n) {
        rand = new Random();
        columns = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int r;
            while (true) {
                r = getRand(1, n);
                if (!columns.contains(r)) {
                    break;
                }
            }
            columns.add(r);
        }
        System.out.print(columns);
        System.out.println();
        fitness();
    }

    public Solution(Solution a, Solution b) throws Exception {
        List<Integer> aCols = a.getCols();
        List<Integer> bCols = b.getCols();
        if (aCols.size() != bCols.size()) {
            throw new Exception("This shouldn't have happened! Solution sizes don't match");
        }

        ;
        columns = new ArrayList<>();
        for (int i = 0; i < aCols.size(); i++) {
            int crossover = getRand(1,2);
            if (crossover == 1) {
                columns.add(aCols.get(i));
            } else {
                columns.add(bCols.get(i));
            }
        }
        fitness();
    }

    public boolean conflict(List<Integer> q, int n) {
        for (int i = 0; i < n; i++) {
            if (q.get(i) == q.get(n)) {
                return false;
            }
            if ((q.get(i) - q.get(n)) == (n - i)) {
                return false;
            }
            if ((q.get(n) - q.get(i)) == (n - i)) {
                return false;
            }
        }
        return true;
    }

    private int getRand(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
    
    public void normalMutate() {
        int chance = getRand(1,100);
        if (chance <= 3) {
            int k = getRand(1, columns.size())-1;
            columns.set(k,getRand(1, columns.size()));
        }
        fitness();
    }

    public void mutate() {
        for (Integer k : columns) {
            int mute = getRand(1, 2);
            if (mute == 1) {
                //mutate this bit
                k = getRand(1, columns.size());
            }
        }
        fitness();
    }

    public int getFitness() {
        return fitness;
    }

    private void fitness() {
        fitness = 0;

        for (int i = 0; i < columns.size(); i++) {
            for (int j = 0; j < columns.size(); j++) {
                if (i != j && columns.get(i) == columns.get(j)) {
                    fitness++;
                }
            }
            for (int k = 0; k < i; k++) {
                if (columns.get(k) == columns.get(i)) {
                    fitness++;
                }
                if ((columns.get(k) - columns.get(i)) == (i - k)) {
                    fitness++;
                }
                if ((columns.get(i) - columns.get(k)) == (i - k)) {
                    fitness++;
                }
            }
        }
    }

    public List<Integer> getCols() {
        return columns;
    }
}
