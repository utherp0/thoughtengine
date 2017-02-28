package org.uth.thoughtEngine.core.mind;

import org.uth.thoughtEngine.core.memory.Cell;

/**
 * Interface for impulsive thought. This is a random action that changes the cell
 * memories. It is provided as a defined interface so that Memes may be provided across
 * Cells.
 * @author Ian Lawson
 */
public interface IImpulse 
{
  // This is the interface for doing a consistency updating operation. This involves
  // examining the Cell for a given consistency (i.e. a contributor, a statement etc. and
  // then updating the cell consistency through an operation on the data.
  public int impulseConsistency( String operation, Cell cell, boolean execute );
  
  // This is the interface for doing a trust updating operation. This involves 
  // examining the Cell for a given trust (i.e. the totalling of consistency for a 
  // contributor) and adjusting the trust appropriately.
  public int impulseTrust( String operation, Cell cell, boolean execute );
  
  // This is the interface for Cell data changing operations. This involves
  // doing some inference based actions on the existing data to create
  // new data or alter existing data.
  public boolean impulseData( String operation, Cell cell, boolean execute );
}
