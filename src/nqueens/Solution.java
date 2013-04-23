/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

import java.util.List;

/**
 * The candidate solution class for the N-Queens GA.
 * @author tcc10a
 */
public class Solution {
    private static List<Integer> columns;
    public Solution(int n) {
        for (int i = 0; i < n; i++) {
            columns.add(0 + (int)(Math.random() * (n + 1)));
        }
    }
    
    public List<Integer> getCols() {
       return columns;
    }
}
