
package org.uth.thoughtEngine.core.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.uth.thoughtEngine.core.currency.Archetype;
import org.uth.thoughtEngine.core.currency.Contributor;
import org.uth.thoughtEngine.core.currency.Instance;
import org.uth.thoughtEngine.core.currency.Statement;
import org.uth.thoughtEngine.exceptions.ExportFormatException;
import org.uth.thoughtEngine.exceptions.StatementException;

/**
 * Memory cell object. This consists of a pool of statement, a pool of types, a pool of instances and a pool of contributors
 * @author Ian Lawson
 */
public class Cell 
{
  private String _name = null;
  private List<String> _statements = null;
  private List<Archetype> _archetypes = null;
  private Map<String,Instance> _instances = null;
  private Map<String,Contributor> _contributors = null;
  private int _trust = 100;
  private boolean _trustLocked = false;
  
  public Cell( String name )
  {
    _name = name;
    _statements = new ArrayList<>();
    _archetypes = new ArrayList<>();
    _instances = new HashMap<>();
    _contributors = new HashMap<>();
  }
  
  public Cell( String name, int defaultTrust )
  {
    _name = name;
    _statements = new ArrayList<>();
    _archetypes = new ArrayList<>();
    _instances = new HashMap<>();
    _contributors = new HashMap<>();
    
    _trust = defaultTrust;
  }
  
  /**
   * Dump the Cell contents out for storage.
   * @return the entire Cell contents in a String format
   */
  public String export( String mode ) throws ExportFormatException
  {
    StringBuilder output = new StringBuilder();
    
    if( mode.toLowerCase().equals("xml"))
    {
      output.append( "<cell name=\"" + _name + "\" trust=\"" + _trust + "\" trustLocked=\"" + _trustLocked + "\">\n");
      
      for( String statement : _statements )
      {
        output.append( "  <statement value=\"" + statement + "\"/>\n");
      }
      
      for( Archetype archetype : _archetypes )
      {
        output.append( "  <archetype>\n");
        output.append( "    <name>" + archetype.getName() + "</name>\n");
        output.append( "    <consistency>" + archetype.getConsistency() + "</consistency>\n");
        output.append( "    <originator>" + archetype.getOriginator() + "</originator>\n");
        
        for( String key : archetype.getComponents())
        {
          output.append( "    <component name=\"" + key + "\" required=\"" + !archetype.isOptional(key) + "\"/>\n");
        }
        output.append( "  </archetype>\n");
      }
      
      for( String name : _instances.keySet())
      {
        Instance instance = _instances.get(name);
        
        output.append( "  <instance name=\"" + name + "\" creator=\"" + instance.getCreator() + "\">\n");
        
        for( String archetype : instance.getArchetypes() )
        {
          output.append( "    <archetype name=\"" + archetype + "\"/>\n");
        }
        
        for( String statement : instance.getStatements() )
        {
          output.append( "    <statement value=\"" + statement + "\"/>\n");
        }
        
        output.append( "  </instance>\n");
      }
      
      for( String name : _contributors.keySet() )
      {
        Contributor contributor = _contributors.get(name);
        
        output.append( "  <contributor name=\"" + contributor.getName() + "\" description=\"" + contributor.getDescription() + "\" consistency=\"" + contributor.getConsistency() + "\"/>\n");
      }
      
      output.append( "</cell>\n");
      
      return output.toString();
    }
    else
    {
      throw new ExportFormatException( "Unknown export format " + mode );
    }
  }
  
  /**
   * Administrator command - mutator for the trust locked key. This allows
   * the cell to have it's trust reset, setting it to true means that only the
   * cell can change it's default trust level.
   * @param locked whether the trust value is locked
   */
  public void setTrustLocked( boolean locked )
  {
    _trustLocked = locked;
  }
  
  public String getName() { return _name; }
  
  public List<String> getStatements() { return _statements; }
  public List<Archetype> getArchetypes() { return _archetypes; }
  public Map<String,Instance> getInstances() { return _instances; }
  public int getTrust() { return _trust; }
  
  /**
   * Instance lookup. This is used in order to make safe direct calls to getInstance.
   * @param name instance to check
   * @return true if the named instance exists, false if it does not
   */
  public boolean hasInstance( String name )
  {
    return _instances.containsKey(name);
  }

  /**
   * Instance accessor - this is for altering an existing instance.
   * @param name instance to return
   * @return the instance or null if it doesn't exist
   */
  public Instance getInstance( String name )
  {
    return( _instances.containsKey(name) ? _instances.get(name) : null );
  }
  
  public void bulkLoadStatements( List<String> statements )
  {
    for( String statement : statements )
    {
      _statements.add(statement);
    }
  }
  
  public boolean bulkLoadArchetypes( List<Archetype> archetypes, boolean overwrite )
  {
    for( Archetype archetype : archetypes )
    {
      if( Archetype.containsArchetypes(archetype.getName(), _archetypes))
      {
        if( overwrite )
        {
          _archetypes = Archetype.removeArchetypes(archetype.getName(), _archetypes);
          _archetypes.add( archetype );
        }
        else
        {
          return false;
        }
      }
      else
      {
        _archetypes.add( archetype );
      }
    }
    
    return true;
  }
  
  public void bulkLoadContributors( List<Contributor> contributors, boolean aggregateConsistencies )
  {
    for( Contributor contributor : contributors )
    {
      if( _contributors.containsKey(contributor.getName()))
      {
        if( aggregateConsistencies )
        {
          int consistency = _contributors.get(contributor.getName()).getConsistency();
          
          contributor.setConsistency((contributor.getConsistency() + consistency) / 2 );
        }
        
        _contributors.remove(contributor.getName());
        _contributors.put(contributor.getName(), contributor);
      }
      else
      {
        _contributors.put(contributor.getName(),contributor);
      }
    }    
  }
  
  public boolean addInstance( Instance instance )
  {
    if( _instances.containsKey(instance.getName())) return false;
    
    _instances.put(instance.getName(), instance);
    return true;
  }
  
  public boolean removeInstance( String name )
  {
    if( !_instances.containsKey(name)) return false;
    
    _instances.remove(name);
    return true;
  }
  
  public boolean addContributor( Contributor contributor )
  {
    if( _contributors.containsKey(contributor.getName()))
    {
      return false;
    }
    
    _contributors.put( contributor.getName(), contributor);
    return true;
  }
  
  public boolean removeContributor( String name )
  {
    if( !_contributors.containsKey(name)) return false;
    
    _contributors.remove(name);
    return true;
  }

  /**
   * Private contributor check method. This adds the contributor with the current trust
   * if not known.
   * @param contributor contributor name to check 
   */
  private void checkContributor( String contributor, String description  )
  {
    if( !this.isContributor(contributor))
    {
      Contributor newContributor = new Contributor( contributor, description );
      newContributor.setConsistency(_trust);

      this.addContributor(newContributor);
    }
  }
  
  public boolean addArchetype( Archetype archetype )
  {
    if( Archetype.containsArchetypes(archetype.getName(), _archetypes)) return false;
    
    // Handle Contributor
    String contributor = archetype.getOriginator();
    this.checkContributor(contributor, "CellChecked");
    
    _archetypes.add(archetype);
    return true;
  }
  
  public boolean removeArchetype( String name )
  {
    if( !( Archetype.containsArchetypes(name, _archetypes))) return false;
    
    Archetype toDelete = null;
    
    for( Archetype archetype : _archetypes )
    {
      if( archetype.getName().equals( name ) ) toDelete = archetype;
    }
    
    _archetypes.remove(toDelete);
    return true;
  }
  
  /**
   * Intelligent addStatement method. This adds the Contributor if it doesn't exist with
   * the trust level for the Cell.
   * @param statement statement to add
   * @throws StatementException if the statement is malformed
   */
  public void addStatement( String statement ) throws StatementException
  {
    Statement.checkStatement(statement);
    
    // Intelligence - add the contributor if he doesn't exist
    String contributor = Statement.getContributor(statement);
    
    this.checkContributor(contributor, "CellChecked");
    
    _statements.add(statement);
  }
  
  public boolean removeExactStatement( String statement )
  {
    if( !_statements.contains(statement)) return false;
    
    _statements.remove(statement);
    return true;
  }
  
  public boolean removeStatementNameValue( String nameValue )
  {
    for( String statement : _statements )
    {
      if( statement.startsWith( nameValue ))
      {
        _statements.remove(statement);
        return true;
      }
    }
    
    return false;
  }
  
  public void removeNamedStatements( String name )
  {
    List<String> toRemove = new ArrayList<>();
    
    for( String statement : _statements )
    {
      if( Statement.getName(statement).equals(name) )
      {
        toRemove.add(statement);
      }
    }
    
    for( String statement : toRemove )
    {
      _statements.remove(statement);
    }
  }
  
  /**
   * Accessor to see if a contributor is known by the cell
   * @param contributor contributor to check
   * @return true if cell is aware, false otherwise
   */
  public boolean isContributor( String contributor )
  {
    return _contributors.containsKey(contributor);
  }
  
  /**
   * When polled for a contributor this method either returns the
   * consistency, if that contributor is known, or the current trust level.
   * @param contributor contributor to check
   * @return contributor consistency or cell trust level
   */
  public int contributorConsistency( String contributor )
  {
    if( _contributors.containsKey(contributor))
    {
      return _contributors.get(contributor).getConsistency();
    }
    else
    {
      return _trust;
    }
  }
  
  /**
   * Administrator method - accessor for the default trust level. Does not change the
   * value if the trust is locked.
   * @param trust value for default trust
   */
  public void setTrust( int trust )
  {
    if( _trustLocked ) return;
    
    if( trust < 0 ) trust = 0;
    if( trust > 100 ) trust = 100;
    
    _trust = trust;
  }
  
  /**
   * Human readable cell output (may be very long).
   * @return human readable version of the cell contents
   */
  @Override
  public String toString()
  {
    String output = "Cell: " + _name + "\n";
    output += "[Contributors]" + "\n";
    
    if( _contributors.isEmpty())
    {
      output += "  None.";
    }
    else
    {
      for( String contributor : _contributors.keySet())
      {
        output += "  " + contributor + ":" + _contributors.get(contributor).getConsistency();
      }
    }
    
    output += "\n[Statements]\n";
    
    if( _statements.isEmpty())
    {
      output += "  None.\n";
    }
    else
    {
      for( String statement : _statements )
      {
        try
        {
          output += "  " + Statement.toHumanReadable(statement) + "\n";
        }
        catch( Exception exc )
        {
          output += "  (Invalid Statement) " + statement + "\n";
        }
      }
    }
    
    return output;
  }
}
