/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uth.thoughtEngine.tests;

import org.uth.thoughtEngine.core.currency.Archetype;
import org.uth.thoughtEngine.core.currency.CellResponse;
import org.uth.thoughtEngine.core.currency.Statement;
import org.uth.thoughtEngine.core.memory.Cell;
import org.uth.thoughtEngine.exceptions.CommandException;
import org.uth.thoughtEngine.exceptions.CommandSyntaxException;
import org.uth.thoughtEngine.exceptions.StatementException;
import org.uth.thoughtEngine.utils.ICommandParser;

/**
 * Test Command Parser, text based input.
 * @author Ian Lawson
 */
public class CommandParserTest1 implements ICommandParser 
{
  private static final String COMMANDS = " set get inquire score thought ";
  
  public CellResponse parse( Object commandObject, String identifier, Cell cell ) throws CommandException, CommandSyntaxException
  {
    String command = null;
    
    try
    {
      command = (String)commandObject;
      command = command.toLowerCase();
    
      if( !( this.isCoreCommand(commandObject)))
      {
        
      }
      
      // Set
      if( command.startsWith("set "))
      {
        return this.handleSet( command, identifier, cell );
      }
    }
    catch( ClassCastException exc )
    {
      throw new CommandException( this.getClass().getCanonicalName() + " only supports String based commands.");
    }
    
    return new CellResponse(identifier, command, "I'm confused.",0);
  }
  
  /**
   * Handle a set command. This can take a number of formats:
   * 
   * set statement [statement]
   * set archetype [name statement;optional,...]
   * set instance [name archetype,...|none statement,...]
   * 
   * @param command
   * @param identifier
   * @param cell
   * @return
   * @throws CommandSyntaxException 
   */
  private CellResponse handleSet( String command, String identifier, Cell cell ) throws CommandSyntaxException
  {
    String[] components = command.split(" ");
    
    if( components.length == 1 ) { throw new CommandSyntaxException( "Malformed Set command"); }
    
    // Target
    String type = components[1];
    
    if( type.equals("statement"))
    {
      if( components.length != 3 )
      {
        throw new CommandSyntaxException( "Invalid set statement format - " + command );
      }
      
      try
      {
        // Alter statement contributor information
        // Statement format name:{!}value:type:contributor:consistency
        
        Statement.checkStatement(components[2]);
        
        String[] statementComponents = components[2].split( ":" );
        
        // Does the cell know about the Contributor?
        int workingScore = cell.contributorConsistency(identifier);
        
        // Create a new Statement based on input and assumed consistency
        String newStatement = statementComponents[0] + ":" +
                              statementComponents[1] + ":" +
                              statementComponents[2] + ":" +
                              identifier + ":" +
                              workingScore;
        
        cell.addStatement(newStatement);
        
        return new CellResponse( identifier, command, "Added " + newStatement, workingScore );
      }
      catch( StatementException sexc )
      {
        throw new CommandSyntaxException( "Invalid statement for set - " + components[2] );
      }
    }
    else if( type.equals("archetype"))
    {
      // Archetype format is statementname;optional space separated
      // Must be at least four components long (must have at least one statement)
      if( components.length < 4 )
      {
        throw new CommandSyntaxException( "Invalid format for set archetype, must have at least one statementname;optionality");
      }
      
      Archetype archetype = new Archetype( components[2], identifier );
      
      // Does the cell know about the Contributor?
      int workingScore = cell.contributorConsistency(identifier);
    
      archetype.setConsistency(workingScore);
      
      for( int loop = 4; loop <= components.length; loop++ )
      {
        String archetypeComponent = components[loop - 1];
        
        // Check the format of the archetypeComponent
        String[] archetypeComponents = archetypeComponent.split(";");
        
        if( archetypeComponents.length != 2 )
        {
          throw new CommandSyntaxException( "Invalid format for archetype statement name component - " + archetypeComponent );
        }
        
        archetype.addComponent(archetypeComponents[0], archetypeComponents[1].equalsIgnoreCase("true"));
      }
      
      cell.addArchetype(archetype);
      
      return new CellResponse( identifier, command, "Added " + archetype.toString(), workingScore );
    }
    else if( type.equals("instance"))
    {
      
    }
    else if( type.equals("trust"))
    {
      try
      {
        int trustLevel = Integer.parseInt(components[2]);
        
        cell.setTrust(trustLevel);
        
        return new CellResponse( identifier, command, "Set AI base trust level to " + cell.getTrust(), 0 );
      }
      catch( NumberFormatException exc )
      {
        return new CellResponse( identifier, command, "Failed, number format exception for trust setting.",0 ); 
      }
    }
    else
    {
      throw new CommandSyntaxException( "Unknown operator type in set command - " + type );
    }
    
    return null;
  }
  
  /**
   * Checker to see if the command starts with a Core command understood by this parser
   * @param commandObject command to examine
   * @return true if the command starts with a core command, false otherwise
   * @throws CommandException if the command is non-interpretable
   */
  public boolean isCoreCommand( Object commandObject ) throws CommandException
  {
    try
    {
      String command = (String)commandObject;
      
      // Only check the start of the command
      if( command.contains(" "))
      {
        command = command.substring(0,command.indexOf(" "));
      }
    
      return COMMANDS.contains(" " + command + " ");
    }
    catch( ClassCastException exc )
    {
      throw new CommandException( this.getClass().getCanonicalName() + " (isCoreCommand) only supports String based commands.");
    }
  }
}
