/*******************************************************************************
 * File: DFSActions.java
 * Author: Dylan Bryan
 * Date: 12/10/20, 9:43 AM
 * Project: ProjectFour
 * Purpose: Lorem ipsum dolor sit amet
 ******************************************************************************/

package project;

import java.util.List;

public interface DFSActions<T> {

  // cycle detected
  boolean cycledDetected(Vertex vertex);

  // process vertex
  void processVertex(List<Vertex> list);

  // descend
  void descend(Vertex parent, Vertex child, int depth);

  // ascend
  void ascend(Vertex vertex);

} // end DFSActions Interface
