/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author tcc10a
 */
public class Genitor {

    private static List<Solution> solutions;
    private static List<Solution> lastBest;

    public Genitor(int n) {
        //Generate initial populations
        solutions = new ArrayList<>();
        lastBest = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            solutions.add(new Solution(n));
        }
    }

    public static void rank() {
        Collections.sort(solutions);
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
            Solution k = new Solution(keep.getCols().size());
            k.mutate();
            solutions.add(k);
        }
    }

    public static void printQueens(Solution q) throws Exception {
        printQueens(q.getCols());
    }

    public static void printQueens(List<Integer> q) throws Exception {

        int N = q.size();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (q.get(i) == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printAll() throws Exception {
        for (Solution i : solutions) {
            printQueens(i);
        }
    }

    public static Solution go() throws Exception {
        Solution best = null;
        while (true) {
            rank();
            printAll();
            if (solutions.get(0).getFitness() == 0) {
                best = solutions.get(0);
                break;
            }
            if (stagnated()) {
                if (lastBest.get(0).equals(solutions.get(0))) {
                    if (lastBest.get(1).equals(solutions.get(0))) {
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
            if (child.getFitness() > solutions.get(solutions.size() - 1).getFitness()) {
                solutions.remove(solutions.size() - 1);
                solutions.add(child);
            }
        }
        return best;
    }
}
