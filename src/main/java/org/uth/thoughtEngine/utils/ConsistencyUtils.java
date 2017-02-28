package org.uth.thoughtEngine.utils;

import java.util.List;
import org.uth.thoughtEngine.core.currency.Statement;

/**
 * Utilities for calculating consistencies across the data types for a Cell.
 * @author Ian Lawson
 */
public class ConsistencyUtils 
{
  private ConsistencyUtils() {}
  
  public static int contributorConsistency( List<String> statements )
  {
    int count = 0;
    int total = 0;
    
    for( String statement : statements )
    {
      count++;
      
      try
      {
        Statement.checkStatement(statement);
        
        if( statement.equals( Statement.getContributor(statement)))
        {
          total += Statement.getConsistency(statement);
        }
      }
      catch( Exception exception )
      {
        // Zero commit to total but still increments
      }
    }
    
    if( total == 0 ) return 0;
    
    return( total == 0 ? 0 : ( total/count ) );
  }
}
