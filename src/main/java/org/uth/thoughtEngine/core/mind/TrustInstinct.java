package org.uth.thoughtEngine.core.mind;

import java.util.Random;
import org.uth.thoughtEngine.core.memory.Cell;

/**
 * Instinct based on the trust of a cell.
 * @author Ian Lawson
 */
public class TrustInstinct implements IInstinct
{
  Random _seed = null;
  
  public TrustInstinct()
  {
    _seed = new Random();
  }
  
  public TrustInstinct( int seed )
  {
    _seed = new Random( seed );
  }
  
  /**
   * Trust based execution.
   * @param command unused
   * @param cell provides the overall trust level for execution
   * @param likelihood unused
   * @return true if the execution should take place
   */
  @Override
  public boolean execute( String command, Cell cell, int likelihood )
  {
    int target = cell.getTrust();
    
    return( _seed.nextInt(100) >= target );
  }
}
