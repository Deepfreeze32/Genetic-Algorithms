package knapsack;

/**
 * The main class for the Knapsack solver
 * @author Deepfreeze32
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
