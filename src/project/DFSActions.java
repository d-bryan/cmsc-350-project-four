/*******************************************************************************
 * File: DFSActions.java
 * Author: Dylan Bryan
 * Date: 12/10/20, 9:43 AM
 * Project: ProjectFour
 * Purpose: Lorem ipsum dolor sit amet
 ******************************************************************************/

package project;

public interface DFSActions<T> {

  // cycle detected
  boolean cycledDetected();

  // process vertex
  void processVertex();

  // descend
  void descend();

  // ascend
  void ascend();

} // end DFSActions Interface
