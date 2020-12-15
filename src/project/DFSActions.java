/*******************************************************************************
 * File: DFSActions.java
 * Author: Dylan Bryan
 * Date: 12/10/20, 9:43 AM
 * Project: ProjectFour
 * Purpose: Methods used by Hierarchy and ParenthesizedList classes
 ******************************************************************************/

package project;

import java.util.List;

public interface DFSActions<T> {

  /**
   * Determines whether or not there the
   * graph has returned to a specific vertex
   * @param vertex Point on the graph
   * @return true/false
   */
  boolean cycledDetected(Vertex vertex);

  /**
   * Process a specific list of Vertices
   * to deliver a modified output
   * @param list List of Vertices
   */
  void processVertex(List<Vertex> list);

  /**
   * Descends into the adjacent list of vertices
   * to process them further
   * @param parent Parent vertex
   * @param child Child vertex
   * @param depth Depth of recursion thus far
   */
  void descend(Vertex parent, Vertex child, int depth);

  /**
   * Break statement to ensure recursion can stop
   * @param vertex
   */
  void ascend(Vertex vertex);

} // end DFSActions Interface
