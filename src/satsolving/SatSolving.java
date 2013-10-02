package satsolving;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Satisfiability Solving GA.
 * @author Deepfreeze32
 */
public class SatSolving {
    public static void main(String[] args) {
        List<String> vars = new ArrayList<>();
        List<String> clauses = new ArrayList<>();
        
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine() && !input.nextLine().equals("stop")) {
            String clause = input.nextLine();
            clauses.add(clause);
            String[] strArr = clause.split(" ");
            for (int i = 0; i < strArr.length; i++) {
                String current = strArr[i];
                if (!current.equals("&") || !current.equals("|")) {
                    if (!vars.contains(current)) {
                        vars.add(current);
                    }
                }
            }
        }
        
    }
}
