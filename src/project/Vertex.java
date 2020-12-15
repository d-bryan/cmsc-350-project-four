/*******************************************************************************
 * File: Vertex.java
 * Author: Dylan Bryan
 * Date: 12/11/20, 8:54 AM
 * Project: ProjectFour
 * Purpose: Creates a new Vertex to be used in the Directed Graph class
 ******************************************************************************/

package project;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
  private T element;
  private List<Vertex> adjacencyList = new ArrayList<>();
  private boolean visited = false;

  /**
   * Creates a new Vertex node
   * @param element
   */
  public Vertex(T element) {
    this.element = element;
  } // end Vertex constructor

  /**
   * Retrieves identifier as to whether the
   * vertex has been visited
   * @return BOOLEAN true/false
   */
  public boolean getVisited() {
    return visited;
  } // end getVisited method

  /**
   * Sets the visited property for the vertex
   * @param visited
   */
  public void setVisited(boolean visited) {
    this.visited = visited;
  } // end setVisited method

  /**
   * Get the current vertex element
   * @return T element
   */
  public T getElement() {
    return element;
  } // end getElement

  /**
   * Add edge to adjacent list
   * @param vertex
   */
  public void addEdge(Vertex vertex) {
    adjacencyList.add(vertex);
  } // end addEdge method

  /**
   * @return List<T> Adjacency List of vertices
   */
  public List<Vertex> getAdjacentVertices() {
    return adjacencyList;
  }

} // end Vertex class
