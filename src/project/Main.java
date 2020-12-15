/*******************************************************************************
 * File: Main.java
 * Author: Dylan Bryan
 * Date: 12/10/20, 9:42 AM
 * Project: ProjectFour
 * Purpose: Main class which initializes the Directed Graph and
 * JFileChooser class. Allows the user to read in a file and create
 * graph objects.
 ******************************************************************************/

package project;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

  /**
   * Reads the file once and creates a set based on the number of
   * unique vertices in the file
   * @param selectedFile FILE file selected by the user
   * @return Set set of unique vertices for passing into directed graph
   * @throws FileNotFoundException
   */
  public static Set<String> getTotalItems(File selectedFile) throws FileNotFoundException {
    Set<String> totalItems = new LinkedHashSet<>();
    // read in the selected file
    Scanner sc = new Scanner(selectedFile);
    // while there is more lines to read
    while (sc.hasNextLine()) {
      String edgeString = sc.nextLine();
      String[] edge = edgeString.split(" ");
      for (int i = 0; i < edge.length; i++) {
        totalItems.add(edge[i]);
      } // end for loop
    } // end while loop

    return totalItems;
  } // end getTotalItems method

  /**
   * Creates a Directed Graph object and adds items to the graph
   * @param totalItems INTEGER number of unique items from Set
   * @param selectedFile FILE selected file by user
   * @return DirectedGraph graph object
   * @throws FileNotFoundException
   */
  public static DirectedGraph addItemsToGraph(Set<String> totalItems, File selectedFile)
          throws FileNotFoundException {
    // construct the graph
    DirectedGraph graph = new DirectedGraph(totalItems.size(), totalItems);
    // read in the selected file
    Scanner input = new Scanner(selectedFile);
    // while there is more lines to read
    while (input.hasNextLine()) {
      String edgeString = input.nextLine();
      String[] edge = edgeString.split(" ");
      for (int i = 0; i < edge.length; i++) {
        if (i < edge.length - 1) {
          String vertexOne = edge[i];
          String vertexTwo = edge[i + 1];
          graph.addEdge(vertexOne, vertexTwo);
        } // end if statement
      } // end for loop
    } // end while loop

    return graph;
  } // end addItemsToGraph method

  /**
   * main method
   * @param args
   */
  public static void main(String[] args) {
    try {
      // get the main directory to choose from
      JFileChooser file = new JFileChooser(new File("src"));
      int option = file.showOpenDialog(null);
      // if the user selects the open button
      if (option == JFileChooser.APPROVE_OPTION) {
          File selectedFile = new File(String.valueOf(file.getSelectedFile()));
          // get the non duplicate values of the list
          Set<String> totalItems = getTotalItems(selectedFile);
          // add items to the graph
          DirectedGraph graph = addItemsToGraph(totalItems, selectedFile);
          // print the graph
          graph.displayGraph();
      } // end if statement
    } catch (FileNotFoundException ex) {
      System.out.println("Could not locate " + ex.getMessage());
    } // end try/catch block
  } // end main method
} // end Main class
