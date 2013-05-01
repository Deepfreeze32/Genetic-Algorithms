/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The candidate solution class for the N-Queens GA.
 *
 * @author tcc10a
 */
public class Solution implements Comparable {
    public static Random rand;
    public static List<Integer> columns;
    public static int fitness;

    public Solution(int n) {
        rand = new Random();
        columns = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            columns.add(getRand(1,n));
        }
        fitness();
    }
    
    public Solution(Solution a, Solution b) throws Exception {
        List<Integer> aCols = a.getCols();
        List<Integer> bCols = b.getCols();
        if (aCols.size() != bCols.size()) {
            throw new Exception("This shouldn't have happened! Solution sizes don't match");
        }
            
        int crossover = getRand(0,aCols.size());
        columns = new ArrayList<>();
        for (int i = 0; i < aCols.size();i++) {
            if (i <= crossover) {
                columns.add(aCols.get(i));
            } else {
                columns.add(bCols.get(i));
            }
        }
        fitness();
    }

    public static boolean conflict(List<Integer> q, int n) {
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
    
    public static int getRand(int min, int max) {
        return rand.nextInt(max-min+1)+min;
    }
    
    public void mutate() {
        for (Integer k : columns) {
            int mute = getRand(1,2);
            if (mute == 1) {
                //mutate this bit
                k = getRand(1,columns.size());
            }
        }
    }

    public static int getFitness() {
        return fitness;
    }
    
    public static void fitness() {
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

    public int compareTo(Solution s) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        //this optimization is usually worthwhile, and can
        //always be added
        if (this == s) {
            return EQUAL;
        }

        //primitive numbers follow this form
        if (this.fitness < s.fitness) {
            return BEFORE;
        }
        if (this.fitness > s.fitness) {
            return AFTER;
        }


        return EQUAL;
    }

    @Override
    public int compareTo(Object o) {
        return compareTo((Solution) o);
    }
}
