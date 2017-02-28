package org.uth.thoughtEngine.core.mind;

import java.util.Random;
import org.uth.thoughtEngine.core.memory.Cell;

/**
 * Pure random instinct.
 * @author Ian Lawson
 */
public class RandomInstinct implements IInstinct
{
  Random _seed = null;
  
  public RandomInstinct()
  {
    _seed = new Random();
  }
  
  public RandomInstinct( int seed )
  {
    _seed = new Random( seed );
  }
  
  /**
   * Simple random implementation - uses the likelihood as the percentage required for 
   * execution.
   * @param command unused
   * @param cell unused
   * @param likelihood percentage likelihood (this value or below) for execution
   * @return true if the execution should take place
   */
  @Override
  public boolean execute( String command, Cell cell, int likelihood )
  {
    return( _seed.nextInt(100) >= likelihood );
  }
}
