package org.uth.thoughtEngine.core.currency;

import java.util.ArrayList;
import java.util.List;

/**
 * An instance is a physical instance of an archetype. This has inherited statements
 * which can be changed.
 * @author Ian Lawson
 */
public class Instance 
{
  private String _name = null;
  private String _creator = null;
  private List<String> _archetypes = new ArrayList<>();
  private List<String> _statements = new ArrayList<>();
  
  public Instance( String name, String creator )
  {
    _name = name;
    _creator = creator;
  }
  
  public String getCreator() { return _creator; }
  
  public String getName() { return _name; }
  
  public boolean addArchetype( String archetypeName )
  {
    if( _archetypes.contains(archetypeName )) return false;
    
    _archetypes.add( archetypeName );
    return true;
  }
  
  /**
   * Boolean indicator if the instance already has this name/value statement.
   * @param inputStatement the statement to consider
   * @return true if it already exists, false otherwise
   */
  public boolean hasStatementValue( String inputStatement )
  {
    return this.nameValueExists(inputStatement);
  }

  /**
   * Private check for exclusive named statements
   * @param inputStatement statement to check
   * @return true if a statement with the name exists, false otherwise
   */
  private boolean nameExists( String inputStatement )
  {
    for( String statement : _statements )
    {
      if( Statement.getName(statement).equals(Statement.getName(inputStatement)))
      {
        return true;
      }
    }
    
    return false;
  }
  
  /**
   * Private check for existing name/value
   * @param inputStatement statement to examine
   * @return true if the name/value exists already, false otherwise
   */
  private boolean nameValueExists( String inputStatement )
  {
    for( String statement : _statements )
    {
      if( Statement.getValue(inputStatement).equals(Statement.getValue(statement)) &&
          Statement.getName(inputStatement).equals(Statement.getName(statement)))
      {
        return true;
      }
    }
    
    return false;    
  }
  
  /**
   * Add statement method. This will fail if set to exclusive and any statement
   * exists with the same name, or if the name/value already exists in the instance
   * @param statement statement to add
   * @param exclusive whether the statement name is unique
   * @return true if added, false otherwise
   */
  public boolean addStatement( String statement, boolean exclusive )
  {
    if( exclusive )
    {
      if( this.nameExists(statement)) return false;
    }
      
    if( this.nameValueExists(statement )) return false;
    
    _statements.add( statement );
    return true;
  }

  /**
   * Remove archetype method. This works with soft labels, i.e. not the archetype itself
   * but simply a label to it. This does not maintain consistency within a Cell, i.e.
   * if the archetype is deleted in the Cell the name remains in the Instance
   * @param archetypeName name to delete
   * @return true if the name existed and was deleted, false otherwise
   */
  public boolean removeArchetype( String archetypeName )
  {
    if( !_archetypes.contains( archetypeName )) return false;
    
    _archetypes.remove( archetypeName );
    return true;
  }
  
  /**
   * This method removes a given statement by name and value.
   * @param statementValue name and value to remove (format name:value)
   * @return true if it has been deleted
   */
  public boolean removeStatement( String statementValue )
  {
    for( String statement : _statements )
    {
      if( statement.startsWith( statementValue ))
      {
        _statements.remove(statement);
        return true;
      }
    }
    
    return false;
  }
  
  /**
   * Archetypes soft label list for this instance.
   * @return the set of soft labels.
   */
  public List<String> getArchetypes()
  {
    return _archetypes;
  }

  /**
   * Statements exported as-is - this is to allow filtering by the AI itself.
   * @return the current statements for this Instance.
   */
  public List<String> getStatements()
  {
    return _statements;
  }
}
