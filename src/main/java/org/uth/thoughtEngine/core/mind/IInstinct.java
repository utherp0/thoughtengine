package org.uth.thoughtEngine.core.mind;

import org.uth.thoughtEngine.core.memory.Cell;

/**
 * Interface for defining the instinctiveness of an impulse. This is basically whether an
 * impulse is executed or not, and at its most simple comprises an engine that decides 
 * whether or not to apply the impulse.
 * @author Ian Lawson
 */
public interface IInstinct 
{
  // This is the interface for deciding whether an impulse should be acted upon or not.
  // Making this an interface allows for the use of external mechanisms for decision.
  // This all-encompassing method prototype allows for the use of Cell contents if
  // required to make the decision.
  public boolean execute( String command, Cell cell, int likelihood );  
}
