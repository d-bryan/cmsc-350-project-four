/*******************************************************************************
 * File: Hierarchy.java
 * Author: Dylan Bryan
 * Date: 12/10/20, 9:43 AM
 * Project: ProjectFour
 * Purpose: Provides a hierarchical representation of the graph
 * as an output in the console
 ******************************************************************************/

package project;

import java.util.List;

public class Hierarchy<T> implements DFSActions<T> {
  DirectedGraph graph;
  public StringBuilder stringBuilder = new StringBuilder();

  /**
   * binds the graph class to hierarchy
   * @param graph
   */
  public Hierarchy(DirectedGraph graph) {
    this.graph = graph;
  } // end Hierarchy constructor

  @Override
  public boolean cycledDetected(Vertex vertex) {
    List<Vertex> adjacent = vertex.getAdjacentVertices();
    if (adjacent.contains(vertex)) {
      return true;
    } // end if statement

    return false;
  } // end cycledDetected method

  @Override
  public void processVertex(List<Vertex> list) {
    int count = 0;
    for(int i = 0; i < list.size(); i++) {
      Vertex current = list.get(i);
      if (i < list.size() - 1) {
        Vertex next = list.get(i + 1);
        descend(current, next, ++count);
      } else if (i == list.size() - 1) {
        stringBuilder.append(current.getElement().toString());
      } // end if/else statement
    } // end for loop
  } // end processVertex method

  @Override
  public void descend(Vertex parent, Vertex child, int depth) {
    String tab = "";
    List<Vertex> adjacent = parent.getAdjacentVertices();
    // if both parent and child are present in adjacency list
    if (adjacent.contains(child) && adjacent.contains(parent)) {
      stringBuilder.append(parent.getElement().toString() + " *\n");
    } else {
      // create number of tabs based on adjacency list size
      for (int i = 0; i < adjacent.size(); i++) {
        tab += "\t";
      } // end for loop
      // append the string
      stringBuilder.append(tab + parent.getElement().toString() + "\n");
    } // end if/else statement
  } // end descend method

  @Override
  public void ascend(Vertex vertex) {
    if (vertex.getVisited()) {
      return;
    } // end if statement
  } // end ascend method

} // end Hierarchy class
