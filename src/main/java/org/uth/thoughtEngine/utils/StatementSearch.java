package org.uth.thoughtEngine.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.uth.thoughtEngine.core.currency.Statement;

/**
 * Utilities for searching Statements across multiple lists.
 * Note that the methods are designed to be combined rather than putting all the functionality into single
 * methods. For instance, a wildcard search of statement names would generate a list of statements that could
 * then be used to provide input to the numerical search.
 * 
 * Also it is expected that the Cells themselves will pre-filter the Statement lists depending on need.
 * @author Ian Lawson
 */
public class StatementSearch 
{
  private StatementSearch() {}
  
  /**
   * Numerical search capabilities.
   * @param input Set of lists of statement to search
   * @param value value for comparison
   * @param equals whether to return statements for which the value equals the test value
   * @param greaterThan whether to return statements for which the value is greater than the test value
   * @param lessThan whether to return statements for which the value is less than the test value
   * @return list of fully qualified statements that match required input
   */
  public static List<String> numericalValueSearch( Set<List<String>> input, float value, boolean equals, boolean greaterThan, boolean lessThan )
  {
    List<String> output = new ArrayList<>();
    
    for( List<String> workingSet : input )
    {
      for( String statement : workingSet )
      {
        // Do we need to search this statement?
        if( Statement.getType(statement).equals( Statement.typeNumber))
        {
          // Extract the number
          Float comparitor = (Float)Statement.getValue(statement);
          
          // Test cases appropriate to the required search
          boolean matches = false;
          
          if( equals )
          {
            matches = ( value == comparitor );
          }
          
          if( greaterThan )
          {
            matches = ( matches || comparitor > value );
          }
          
          if( lessThan )
          {
            matches = ( matches || comparitor < value );
          }
          
          if( matches )
          {
            output.add(statement);
          }
        }
      }
    }
    
    return output;
  }
  
  /**
   * String value search.
   * @param input set of lists of statements to search
   * @param value value to search for
   * @param exactMatch whether to match completely or within the value
   * @param ignoreCase whether to ignore the case of the value/comparitor
   * @return list of statements that match the required search
   */
  public static List<String> stringValueSearch( Set<List<String>> input, String value, boolean exactMatch, boolean ignoreCase )
  {
    List<String> output = new ArrayList<>();
    
    // Adjust the search terms depending on case requirements
    if( ignoreCase ) value = value.toLowerCase();
    
    for( List<String> workingSet : input )
    {
      for( String statement : workingSet )
      {
        // Only compare String based values
        if( Statement.getType(statement).equals( Statement.typeString))
        {
          String comparitor = (String)Statement.getValue(statement);
          
          // Adjust depending on case requirements
          if( ignoreCase ) comparitor = comparitor.toLowerCase();
          
          // Perform approprate matching
          if( exactMatch )
          {
            if( comparitor.equals(value))
            {
              output.add(statement);
            }
          }
          else
          {
            if( comparitor.contains(value) )
            {
              output.add( statement );
            }
          }
        }
      }
    }
    
    return output;
  }
  
  /**
   * Simple wrapper to allow searches on Lists.
   * @param input list of statements to search
   * @param value value to compare
   * @param exactMatch whether to match exactly the value
   * @param ignoreCase whether to ignore the case of the match
   * @return list of statements that match
   */
  public static List<String> stringValueListSearch( List<String> input, String value, boolean exactMatch, boolean ignoreCase )
  {
    Set<List<String>> pushThrough = new HashSet<>();  
    
    pushThrough.add(input);
    
    return StatementSearch.stringValueSearch(pushThrough, value, exactMatch, ignoreCase);
  }

  /**
   * Simple wrapper to allow searches on Lists.
   * @param input list of statements to search
   * @param value numerical value to compare
   * @param equals whether to match equals
   * @param greaterThan whether to match greater than
   * @param lessThan whether to match less than
   * @return list of statements that match
   */
  public static List<String> numericalValueListSearch( List<String> input, float value, boolean equals, boolean greaterThan, boolean lessThan )
  {
    Set<List<String>> pushThrough = new HashSet<>();
    
    pushThrough.add(input);
    
    return StatementSearch.numericalValueSearch(pushThrough, value, equals, greaterThan, lessThan);
  }
  
  /**
   * Name search method.
   * @param input Set of Lists of Statements to search
   * @param name name to search for
   * @param exactMatch whether to exact match
   * @param ignoreCase whether to ignore case
   * @return list of matched Statements
   */
  public static List<String> nameSearch( Set<List<String>> input, String name, boolean exactMatch, boolean ignoreCase )
  {
    List<String> output = new ArrayList<>();
    
    if( ignoreCase ) name = name.toLowerCase();
    
    for( List<String> workingSet : input )
    {
      for( String statement : workingSet )
      {
        String comparitor = Statement.getName(statement);
        
        if( ignoreCase ) comparitor = comparitor.toLowerCase();
        
        if( exactMatch )
        {
          if( comparitor.equals(name))
          {
            output.add( statement );
          }
        }
        else
        {
          if( comparitor.contains(name))
          {
            output.add( statement );
          }
        }
      }
    }
    
    return output;
  }
  
  /**
   * Simple wrapper to allow searches on Lists of statements for Names
   * @param input List to search
   * @param name name to search for
   * @param exactMatch whether to exact match
   * @param ignoreCase whether to ignore case
   * @return list of matched statements
   */
  public static List<String> nameListSearch( List<String> input, String name, boolean exactMatch, boolean ignoreCase )
  {
    Set<List<String>> pushThrough = new HashSet<>();
    
    pushThrough.add(input);
    
    return StatementSearch.nameSearch(pushThrough, name, exactMatch, ignoreCase);
  }
}
