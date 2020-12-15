/*******************************************************************************
 * File: ParenthesizedList.java
 * Author: Dylan Bryan
 * Date: 12/10/20, 9:44 AM
 * Project: ProjectFour
 * Purpose: Provides a parenthesized list representation of the graph
 * as output in the console
 ******************************************************************************/

package project;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ParenthesizedList<T> implements DFSActions<T> {
  DirectedGraph graph;
  public StringBuilder stringBuilder = new StringBuilder();

  /**
   * binds the graph class to parenthesized list
   * @param graph
   */
  public ParenthesizedList(DirectedGraph graph) {
    this.graph = graph;
  } // end ParenthesizedList constructor

  /**
   * Creates the string to add to output for unreachable items in graph
   * @param list LIST list of vertices
   * @return STRING output
   */
  public String unreachable(List<Vertex> list) {
    StringBuilder unreachable = new StringBuilder();
    Set<Vertex> vertexListSet = new LinkedHashSet<>();
    Set<Vertex> dfsVertexSet = new LinkedHashSet<>();
    // add to sets
    graph.vertexList.forEach(vertex -> vertexListSet.add((Vertex) vertex));
    list.forEach(vertex -> dfsVertexSet.add(vertex));
    // iterate over sets and compare
    Iterator iterator = dfsVertexSet.iterator();
    while (iterator.hasNext()) {
      Vertex next = (Vertex) iterator.next();
      if (vertexListSet.contains(next)){
        vertexListSet.remove(next);
      } // end if statement
    } // end while loop
    // add to unreachable string
    vertexListSet.forEach(item -> unreachable.append(item.getElement().toString() + " "));
    if (unreachable.length() > 0) {
      unreachable.append("is unreachable");
    } // end if statement

    return unreachable.toString();
  } // end unreachable method

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
        stringBuilder.append(current.getElement().toString() + ")");
      } // end if/else statement
    } // end for loop
  } // end processVertex method

  @Override
  public void descend(Vertex parent, Vertex child, int depth) {
    String left = "(";
    String right = ")";
    List<Vertex> adjacent = parent.getAdjacentVertices();
    // if both parent and child are present in adjacency list
    String toAppend = (adjacent.contains(child) && adjacent.contains(parent)) ?
            "" + left + parent.getElement().toString() + " * "
            :
            "" + left + parent.getElement().toString() + " ";

    if (child.getAdjacentVertices().size() > 0) {
      stringBuilder.append(toAppend);
    } else {
      stringBuilder.append(toAppend + right);
    } // end if/else statement
  } // end descend method

  @Override
  public void ascend(Vertex vertex) {
    if (vertex.getVisited()) {
      return;
    } // end if statement
  } // end ascend method
} // end ParenthesizedList class
