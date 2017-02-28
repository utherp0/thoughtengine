package org.uth.thoughtEngine.tests;

import java.util.Scanner;
import org.uth.thoughtEngine.core.currency.CellResponse;
import org.uth.thoughtEngine.core.memory.Cell;

/**
 * Console test 1, simple command parser.
 * @author Ian Lawson
 */
public class ConsoleTest1 
{
  private Cell _memory = null;
  
  public static void main( String[] args )
  {
    if( args.length != 2 )
    {
      System.out.println( "Usage: java ConsoleTest1 user_identifier memory_identifier");
      System.exit(0);
    }
    
    new ConsoleTest1( args[0], args[1] );
  }
  
  public ConsoleTest1( String identifier, String cell_identifier )
  {
    System.out.println( "Welcome " + identifier + "." );
    
    _memory = new Cell( cell_identifier );
    
    CommandParserTest1 parser = new CommandParserTest1();
    
    Scanner input = new Scanner( System.in );
    
    String command = "initialise";
    
    while( !( command.toLowerCase().equals( "quit" )))
    {
      System.out.print( "Command: " );
      command = input.nextLine().toLowerCase();
      
      //System.out.println( "[Echo] " + command );
      
      if( command.startsWith("help"))
      {
        this.showHelp();
      }
      else if( command.equals("quit"))
      {
        // Quit
      }
      else if( command.startsWith("whoami"))
      {
        System.out.println( "[whoami] " + identifier );
      }
      else if( command.equals("describe"))
      {
        System.out.println( _memory.toString());
      }
      else if( command.startsWith("setid"))
      {
        String[] components = command.split(" ");
        
        if( components.length != 2 )
        {
          System.out.println( "[Error] You must provide an alternative ID");
        }
        else
        {
          identifier = components[1];
          System.out.println( "[whoami changed] " + identifier );
        }
      }
      else
      {
        long cellStart = System.currentTimeMillis();
        
        try
        {
          CellResponse response = parser.parse(command, identifier, _memory);
          
          long cellEnd = System.currentTimeMillis();
          
          System.out.println( "[Cell Response] " + response.getSimpleResponse());
          System.out.println( "[Cell Response Time] " + (cellEnd - cellStart) + "ms.");
        }
        catch( Exception exc )
        {
          System.out.println( "[Exception] " + exc.toString());
        }
      }
    }
  }
  
  public void showHelp()
  {
    System.out.println( "" );
    System.out.println( "Command List supported by ConsoleTest1:");
    System.out.println( "(All commands are case-insensitive, all entered data is case-lowered)");
    System.out.println( "" );
    
    System.out.println( "[whoami] Shows current identifier.");
    System.out.println( "[setid id] Sets the current identifier.");
    System.out.println( "set archetype name (statementName;optionality..)");
    System.out.println( "set statement name:(!)value:type (consistency auto-generated");
    System.out.println( "set trust (0..100)" );
    System.out.println( "describe");
  }
}
