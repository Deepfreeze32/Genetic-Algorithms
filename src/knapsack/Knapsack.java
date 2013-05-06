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
        Genitor g = new Genitor(items);
        Solution s = g.go();
        if (s == null) {
            System.out.println("Some error occurred.");
        } else {
            System.out.println("Solution found: ");
            items.printPack(s.getSet());
            System.out.println("\nTotal value: "+s.getFitness());
        }
    }
}
