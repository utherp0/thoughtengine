package org.uth.thoughtEngine.core.mind;

/**
 * Abstract Impulse implementation with members.
 * @author Ian Lawson
 */
public abstract class Impulse implements IImpulse
{
  private static int _likelihood = 0;
  
  public int getLikelihood() { return _likelihood; }
  public void setLikelihood( int likelihood ) { _likelihood = likelihood; }
}
