package org.uth.thoughtEngine.core.currency;

import org.uth.thoughtEngine.exceptions.StatementException;

/**
 * Basic Statement definition. This consists of the following information:
 * 
 * name:{!}value:type:contributor:consistency
 * 
 * @author Ian Lawson
 */
public class Statement 
{
  public static final String typeString = "string";
  public static final String typeNumber = "number";
  
  private String _statement = null;
  
  public Statement( String statement )
  {
    _statement = statement;
  }
  
  public String getStatement() { return _statement; } 
  
  public static void checkStatement( String statement ) throws StatementException
  {
    String[] contents = statement.split(":");
    
    if( contents.length != 5 ) throw new StatementException( "Invalid number of components.");
    
    if( !contents[2].equals( Statement.typeNumber) && !contents[2].equals( Statement.typeString)) throw new StatementException( "Type must be Number or String.");
  }
  
  public static String getName( String statement )
  {
    String[] contents = statement.split( ":" );
    return contents[0];
  }
  
  public static String getType( String statement )
  {
    String[] contents = statement.split( ":" );
    return contents[2];
  }
  
  public static boolean contributes( String statement )
  {
    String[] contents = statement.split( ":" );
    
    return !( contents[1].startsWith("!") );
  }
  
  public static Object getValue( String statement )
  {
    String[] contents = statement.split( ":" );
    
    String value = contents[1];
    
    if( value.startsWith("!"))
    {
      value = value.substring(1);
    }
    
    if( contents[2].equals( Statement.typeNumber)) return new Float( value );
    if( contents[2].equals( Statement.typeString)) return new String( value );
    
    return null;
  }
  
  public static String getContributor( String statement )
  {
    String[] contents = statement.split( ":" );
    
    return contents[3];
  }
  
  public static int getConsistency( String statement )
  {
    String[] contents = statement.split( ":" );
    
    return Integer.parseInt(contents[4]);
  }
  
  public static String toHumanReadable( String statement ) throws StatementException
  {
    Statement.checkStatement(statement);
    
    String output = "";
    
    output += "Name: " + Statement.getName(statement);
    
    output += ( Statement.contributes(statement) ? "[IS] " : " [IS NOT] ");
    
    if( Statement.getType(statement).equals( Statement.typeNumber))
    {
      output += " Value(number): " + Integer.toString((Integer)Statement.getValue(statement));
    }
    else
    {
      output += " Value(string): " + (String)Statement.getValue(statement);
    }
    
    output += " [" + Statement.getContributor(statement) + ":";
    output += Statement.getConsistency(statement) + "]";

    return output;
  }
}
