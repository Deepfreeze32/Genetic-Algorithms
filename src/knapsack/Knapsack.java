/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

/**
 *
 * @author tcc10a
 */
public class Knapsack {
    public static void main(String[] args) {
        CSVParser csv = new CSVParser(args[0]);
        Items items = csv.parse();
        
    }
}
