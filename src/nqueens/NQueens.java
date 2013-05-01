/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static nqueens.Genitor.go;

/**
 * The N-Queens GA.
 * @author tcc10a
 */
public class NQueens {
    public static void main(String[] args) {
        //Preparation.
        System.out.println("What is n?\n");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int n = Integer.parseInt(input);
        if (n == -1) {
            System.exit(1);;
        }
        //Initialize genitor instance
        Genitor g = new Genitor(n);
        try {
            Solution queens = go();
            List<Integer> q = queens.getCols();
            Genitor.printQueens(q);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(NQueens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
