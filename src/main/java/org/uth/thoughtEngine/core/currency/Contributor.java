package org.uth.thoughtEngine.core.currency;

/**
 * Contributor object. This is the contributor of a statement, be it Object or State. This 
 * object stores the threshold consistency for the Contributor, i.e. how trustworthy it is.
 * @author Ian Lawson
 */
public class Contributor
{
  private String _name = null;
  private String _description = null;
  private int _consistency = 100; // TTE defaults to trusting.
  
  public Contributor( String name, String description )
  {
    _name = name;
    _description = description;
  }
  
  /**
   * Accessor for contributor name.
   * @return the name of the contributor
   */
  public String getName()
  {
    return _name;
  }
  
  /**
   * Accessor for description.
   * @return the current description
   */
  public String getDescription()
  {
    return _description;
  }
  
  /**
   * Accessor for the current contributor consistency.
   * @return the consistency for the contributor
   */
  public int getConsistency()
  {
    return _consistency;
  }
  
  /**
   * Mutator for consistency of contributor.
   * @param consistency consistency to store
   */
  public void setConsistency( int consistency )
  {
    _consistency = consistency;
  }
}
