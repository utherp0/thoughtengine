package org.uth.thoughtEngine.core.mind;

import org.uth.thoughtEngine.core.memory.Cell;

/**
 * Recurrent Instinct. This fires off on a periodic basis, where the period is
 * defined as the number of 'visits' before it fires off, allowing for a 
 * guaranteed execution every x 'ticks'.
 * @author Ian Lawson
 */
public class RecurrentInstinct implements IInstinct
{
  int _lifetimeCount = 0;
  int _hitRate = 0;
  
  public RecurrentInstinct( int hitRate )
  {
    _hitRate = hitRate;
  }
  
  public void resetCount() { _lifetimeCount = 0; }
    
  /**
   * Recurrent execution
   * @param command unused
   * @param cell unused
   * @param likelihood unused
   * @return true if the execution should take place
   */
  @Override
  public boolean execute( String command, Cell cell, int likelihood )
  {
    _lifetimeCount++;
    
    return( _lifetimeCount % _hitRate == 0 );
  }
}
