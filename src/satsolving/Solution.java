/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package satsolving;

import java.util.ArrayList;
import java.util.List;

/**
 * The candidate solution class for Sat Solving.
 * @author tcc10a
 */
public class Solution {
    private static List<Boolean> variables;
    
    public Solution(int totalVars) {
        variables = new ArrayList<>();
        for (int i = 0; i < totalVars; i++) {
            variables.add(generateBool());
        }
    }
    
    public static List<Boolean> getVars() {
        return variables;
    } 
    
    public static boolean generateBool() {
        int k = 0 + (int)(Math.random() * (1 + 1));
        return (k != 0);
    }
}
