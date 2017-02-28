package org.uth.thoughtEngine.tests;

import java.util.List;
import java.util.Map;
import org.uth.thoughtEngine.core.currency.Archetype;
import org.uth.thoughtEngine.core.currency.Instance;
import org.uth.thoughtEngine.core.currency.Statement;
import org.uth.thoughtEngine.core.memory.Cell;
import org.uth.thoughtEngine.exceptions.StatementException;

/**
 * Simple Cell test for examining how it would work.
 * @author Ian Lawson
 */
public class CellTest1 
{
  private Cell _memory = null;
  
  public static void main( String[] args )
  {
    if( args.length != 1 )
    {
      System.out.println( "Usage: java CellTest1 cellName");
      System.exit(0);
    }
    
    new CellTest1(args[0]);
  }
  
  public CellTest1( String name )
  {
    _memory = new Cell( name );
    
    this.fillMemory();
    
    // Output the XML
    try
    {
      String xml = _memory.export("xml");
      
      System.out.println( xml );
    }
    catch( Exception exc ) {}
    
  }
  
  public void fillMemory()
  {
    // Fragment statements
    try
    {
      _memory.addStatement("colour:white:string:Uther:100");
      _memory.addStatement("colour:!black:string:Uther:100");
      _memory.addStatement("legs:4:number:Uther:100");
      _memory.addStatement("name:Rex:string:Uther:100");
    
      _memory.addStatement("colour:black:string:Uther:100");
      _memory.addStatement("colour:!white:string:Uther:100");
      _memory.addStatement("legs:4:number:Uther:100");
      _memory.addStatement("name:Puss:string:Uther:100");
    
      _memory.addStatement("colour:black:string:Uther:100");
      _memory.addStatement("legs:8:number:Uther:100");
    }
    catch( StatementException exc )
    {
      System.out.println( "Failure - cell reports statement exception.");
    }
    
    // Instances
    Instance dog = new Instance( "Dog", "Uther" );
    dog.addStatement("name:Rex:string:Uther:100", true);
    dog.addStatement("colour:brown:string:Uther:100", false);
    
    _memory.addInstance(dog);
    
    Instance cat = new Instance( "Cat", "Uther" );
    cat.addStatement("name:Puss:string:uther:100", true);
    cat.addStatement("colour:black:string:Uther:100", false);
    
    _memory.addInstance( cat );
    
    Instance spider = new Instance("Spider", "Uther");
    spider.addStatement("legs:8:number:Uther:100", true);
    spider.addStatement("web:yes:string:Uther:100", true);
    
    _memory.addInstance( spider );
    
    // Add some archetypes
    Archetype dogType = new Archetype( "canine", "Uther" );
    
    dogType.addComponent("colour", true);
    dogType.addComponent("legs", true);
    dogType.addComponent("name", false);
    
    _memory.addArchetype(dogType);
    System.out.println( dogType.toString());
    
    Archetype catType = new Archetype( "feline", "Uther" );
    
    catType.addComponent("colour", true);
    catType.addComponent("legs", true);
    catType.addComponent("name", false);
    
    _memory.addArchetype(catType);
    System.out.println( catType.toString());
    
    Archetype spiderType = new Archetype("arachnid", "Uther");
    
    spiderType.addComponent("legs", true);
    
    _memory.addArchetype(spiderType);
    System.out.println( spiderType.toString());
    
    // Pair the instances with archetypes.
    _memory.getInstance("Spider").addArchetype("arachnid");
    _memory.getInstance("Dog").addArchetype("canine");
    _memory.getInstance("Cat").addArchetype("feline");
    
    // Test 1 - statements
    System.out.println( "Statements:");
    
    List<String> statements = _memory.getStatements();
    
    for( String statement : statements )
    {
      System.out.println( "  " + Statement.getName(statement) + ":" + Statement.getValue(statement));
    }
    
    Map<String,Instance> instances = _memory.getInstances();
    
    for( String name : instances.keySet())
    {
      System.out.println( "  [" + name + "]" );
      for( String statement : instances.get(name).getStatements())
      {
        System.out.println( "  " + Statement.getName(statement) + ":" + Statement.getValue(statement));
      }
    }
    
    // Test 2 - archetypes
    System.out.println("");
    
    System.out.println( "Archetypes:");
    
    for( Archetype archetype : _memory.getArchetypes())
    {
      System.out.println( "  [" + archetype.getName() + "]");
      
      for( String name : archetype.getComponents())
      {
        System.out.println( "    " + name + (archetype.isOptional(name) ? " {optional}" : ""));
      }
      
      System.out.println("");
    }
  }
}
