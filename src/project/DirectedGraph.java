/*******************************************************************************
 * File: DirectedGraph.java
 * Author: Dylan Bryan
 * Date: 12/10/20, 9:42 AM
 * Project: ProjectFour
 * Purpose: Directed Graph class which takes input from a file of the users
 * choice, creates a graph from the text and displays two outputs from it
 ******************************************************************************/

package project;

import java.util.*;

public class DirectedGraph<T> implements Graph<T> {
  public List<Vertex> vertexList = new ArrayList<>();
  private int numberOfVertices = 0;
  public ParenthesizedList parenthesizedList;
  public Hierarchy hierarchy;

  /**
   * Creates a new graph
   * @param numberOfVertices INTEGER number of vertices
   * @param totalItems SET total vertices
   */
  public DirectedGraph(int numberOfVertices, Set<String> totalItems) {
    this.numberOfVertices = numberOfVertices;
    // create iterator from class set
    Iterator iterator = totalItems.iterator();
    // iterate over the class set
    while (iterator.hasNext()) { // create new vertex
      String item = iterator.next().toString();
      vertexList.add(new Vertex(item));
    } // end while loop

    // initialize other helper classes
    parenthesizedList = new ParenthesizedList(this);
    hierarchy = new Hierarchy(this);
  } // end DirectedGraph constructor

  /**
   * Initial call to starting vertex for Depth First Search
   * @param start INTEGER Starting vertex
   * @return LIST
   */
  public List<Vertex> depthFirstSearch(int start) {
    List<Vertex> list = new ArrayList<>();
    depthFirstSearch(start, start, list);
    return list;
  }

  /**
   * Recursive call for Depth First Search, adds items to the list
   * @param previous INTEGER previously processed element
   * @param current INTEGER current processed element
   * @param list LIST list of vertices
   */
  public void depthFirstSearch(int previous, int current, List<Vertex> list) {
    if (vertexList.get(current).getVisited()) {
      return;
    } // end if statement
    // get the currently processed vertex
    Vertex currentVertex = vertexList.get(current);
    // add it to the list
    list.add(currentVertex);
    // set visited to true
    currentVertex.setVisited(true);
    // get adjacent vertices
    List<Vertex> adjacent = currentVertex.getAdjacentVertices();
    // recurse based on the number of vertices in the list
    // and add any reachable items to the list
    for (int next = 0; next < getNumberOfVertices(); next++) {
      if (next != previous && adjacent.contains(vertexList.get(next))) {
        depthFirstSearch(current, next, list);
      } // end if statement
    } // end for loop
  } // end depthFirstSearch method

  @Override
  public void addEdge(String vertexOne, String vertexTwo) {
    int index = 0;
    Vertex vertex = null;

    for (int i = 0; i < vertexList.size(); i++) {
      String currentItem = vertexList.get(i).getElement().toString();
      if (currentItem.equalsIgnoreCase(vertexTwo)) {
        vertex = vertexList.get(i);
        break;
      }
      if (currentItem.equalsIgnoreCase(vertexOne)) {
        index = i;
      } // end if statement
    } // end for loop
    // as long as the item exists in the set add an edge
    if (index >= 0) {
      vertexList.get(index).addEdge(vertex);
    } // end if statement

  } // end addEdge method

  /**
   * NOT REQUIRED CAN REMOVE todo: remove this
   */
  public void additionalDisplay() {
    System.out.println("Adjacency List:\n");

    for (Vertex vertex : vertexList) {
      List<Vertex> adjacentVertices = vertex.getAdjacentVertices();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("[");
      for (int i = 0; i < adjacentVertices.size(); i++) {
        if (i > 0) {
          stringBuilder.append(", ");
        }
        stringBuilder.append(adjacentVertices.get(i).getElement().toString());
      }
      stringBuilder.append("]");
      System.out.println("Edges from " + vertex.getElement() +
              " to : " + stringBuilder.toString() + " -> Degree: " + getDegree(vertex));
    } // end for loop
  } // end additionalDisplay method

  @Override
  public int getNumberOfVertices() {
    return numberOfVertices;
  } // end getNumberOfVertices method

  @Override
  public int getDegree(Vertex vertex) {
    int degree;

    // loop over vertices in vertex list
    List<Vertex> adjacency = vertex.getAdjacentVertices();
    Set<String> vertices = new LinkedHashSet<>();
    adjacency.forEach(item -> vertices.add(item.getElement().toString()));
    degree = (int) vertices.stream().count();

    return degree;
  } // end getDegree method

  @Override
  public void displayGraph() {
    // perform depth first search to get sorted list
    List<Vertex> dfsList = depthFirstSearch(0);

    // parenthesized list processes
    parenthesizedList.processVertex(dfsList);
    System.out.println("Parenthesized List:");
    System.out.println(parenthesizedList.stringBuilder);
    System.out.println(parenthesizedList.unreachable(dfsList));

    System.out.println("\n");

    // hierarchy processes
    hierarchy.processVertex(dfsList);
    System.out.println("Hierarchy:");
    System.out.println(hierarchy.stringBuilder);
  } // end displayGraph method

} // end DirectedGraph class
