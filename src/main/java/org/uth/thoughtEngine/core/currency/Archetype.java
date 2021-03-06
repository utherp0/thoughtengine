package org.uth.thoughtEngine.core.currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.uth.thoughtEngine.exceptions.StatementException;

/**
 * Archetype is a loosely bound set of statement names that define a type of object.
 * Each archetype consists of a name and a flag indicating whether or not this statement name 
 * is optional within the archetype.
 * It also contains and optional list of definition statements. These are used for
 * identifying potential instances *but* are not binding unless the Cell enforces them.
 * The definitipns can be used natively with the StatementSearch util.
 * @author Ian Lawson
 */
public class Archetype 
{
  private String _name = null;
  private Map<String,Boolean> _components = null; 
  private List<String> _definitions = null;
  private int _consistency = 0;
  private String _originator = null;
  
  public Archetype( String name, String originator )
  {
    _name = name;
    _components = new HashMap<String,Boolean>();
    _definitions = new ArrayList<String>();
    _originator = originator;
  }
  
  /**
   * Consistency mutator
   * @param consistency consistency to store
   */
  public void setConsistency( int consistency )
  {
    _consistency = consistency;
  }
  
  /**
   * Consistency accessor.
   * @return current consistency for Archetype
   */
  public int getConsistency() { return _consistency; }
  
  /**
   * Name of archetype accessor.
   * @return the name of this archetype
   */
  public String getName() { return _name; }
  
  /**
   * Human readable version of the archetype
   * @return human readable version
   */
  public String toString()
  {
    String output = "{Archetype " + _name + "}";
    
    output += " [Creator " + _originator + ":" + _consistency + "]";
    
    for( String statement : _components.keySet() )
    {
      output += " " + statement + ":" + _components.get(statement);
    }
    
    return output;
  }
  
  public List<String> getDefinitions() { return _definitions; }
  
  /**
   * Add a definition. The rules are that the definition must be a valid statement format.
   * @param statement statement to add as definition for the archetype.
   * @return true if added, false if the format was invalid
   */
  public boolean addDefinition( String statement )
  {
    try
    {
      Statement.checkStatement(statement);

      _definitions.add(statement);
      
      return true;
    }
    catch( StatementException exc )
    {
      return false;
    }
  }
  
  /**
   * Remove *exact* definition from archetype.
   * @param definition definition to remove
   * @return true if definition removed, false if it doesn't exist
   */
  public boolean removeExactDefinition( String definition )
  {
    if( _definitions.contains(definition))
    {
      _definitions.remove(definition);
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /**
   * Remove named definitions. This removes all statements that *start* with the 
   * given target. This can be name *or* name:value
   * @param name statement to remove as a definition
   * @return count of definitions removed
   */
  public int removeAllNamedDefinitions( String name )
  {
    int count = 0;
    
    for( String definition : _definitions )
    {
      if( definition.startsWith(name))
      {
        _definitions.remove(definition);
        count++;
      }
    }
    
    return count;
  }

  /**
   * Add a component to the archetype. This consists of a name (may be partial) and 
   * a flag indicating if it is optional or not for the archetype.
   * @param component component name to add to the archetype
   * @param optional whether or not this component is optional for an instance of this type
   * @return false if the component already exists, true if added
   */
  public boolean addComponent( String component, boolean optional )
  {
    if( _components.keySet().contains(component)) return false;
    
    _components.put(component, new Boolean(optional));
    return true;
  }
  
  /**
   * Remove a component from the archetype. Note that the archetype does not support
   * an overwrite, you must remove and re-add if the optionality changes for a component.
   * @param component component name to remove from archetype
   * @return true if the component name was removed
   */
  public boolean removeComponent( String component )
  {
    if( !( _components.keySet().contains(component))) return false;
    
    _components.remove(component);
    return true;
  }
  
  /**
   * Helper method to determine if a name is a component within the archetype
   * @param component the name to search for
   * @return true if present, false otherwise
   */
  public boolean hasComponent( String component )
  {
    return _components.containsKey(component);
  }

  /**
   * Optionality accessor for the component.
   * @param component name of component to look up
   * @return true if optional or non-existent, false if not optional
   */
  public boolean isOptional( String component )
  {
    if( !_components.containsKey(component)) return true;
    
    return _components.get(component);
  }

  /**
   * Returns a list of the component names within the archetype.
   * @return list of current names within the archetype
   */
  public List<String> getComponents() 
  {
    List<String> output = new ArrayList<>();
    
    for( String component : _components.keySet() )
    {
      output.add(component);
    }
    
    return output; 
  }

  /**
   * Name lookup across a list of Archetypes.
   * @param name name to compare
   * @param archetypes list of archetypes
   * @return true if the name exists in the archetype list
   */
  public static boolean containsArchetypes( String name, List<Archetype> archetypes )
  {
    for( Archetype archetype : archetypes )
    {
      if( archetype.getName().equals(name)) return true;
    }
    
    return false;
  }

  /**
   * High level archetype removal method. This takes a list of Archetypes and a name and returns the list
   * minus the target archetype.
   * @param name name to remove
   * @param archetypes archetype list
   * @return the list of archetypes minus the named one if present
   */
  public static List<Archetype> removeArchetypes( String name, List<Archetype> archetypes )
  {
    List<Archetype> output = new ArrayList<Archetype>();
    
    for( Archetype archetype : archetypes )
    {
      if( !archetype.getName().equals( name ) )
      {
        output.add(archetype);
      }
    }
    
    return output;
  }
  
  /**
   * Accessor for the originator of the Archetype.
   * @return the originator stored for this archetype
   */
  public String getOriginator()
  {
    return _originator;
  }
}
