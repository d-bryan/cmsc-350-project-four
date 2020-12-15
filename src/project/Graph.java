/*******************************************************************************
 * File: Graph.java
 * Author: Dylan Bryan
 * Date: 12/12/20, 9:18 AM
 * Project: ProjectFour
 * Purpose: Methods used by the Directed Graph class
 ******************************************************************************/

package project;

public interface Graph<T> {

  /**
   * Adds an edge to the graph
   * @param vertexOne
   * @param vertexTwo
   */
  void addEdge(String vertexOne, String vertexTwo);

  /**
   * @return INTEGER number of vertices
   */
  int getNumberOfVertices();

  /**
   * Get the nth degree of incoming edges
   * @param vertex
   * @return INTEGER degree of vertex
   */
  int getDegree(Vertex vertex);

  /**
   * Display the graph in console output
   */
  void displayGraph();

} // end Graph interface
