Project Description
===================

A Genetic Algorithms project for CS 467: Introduction to Artificial Intelligence

Files
-----

Files k05-k40 are Knapsack data sets. 

There are three GA implementations in this project:
* N-Queens
* Knapsack
* Satisfiability Solving (Not implemented fully)

Instructions
------------

First run the following commands from this directory:  
cd src/knapsack/  
javac CSVParser.java Genitor.java Items.java Solution.java Knapsack.java  
cd ../nqueens/  
javac Genitor.java NQueens.java Solution.java  
cd ../  
java nqueens.NQueens  
java knapsack.Knapsack <parameters>  

Valid parameters for Knapsack are: A csv file of data items.  
Example: java knapsack.Knapsack k05.csv

NQueens requires no parameters, simply input N into the prompt.

As Satisfiability Solving is not functional, there are no instructions  
for it. 