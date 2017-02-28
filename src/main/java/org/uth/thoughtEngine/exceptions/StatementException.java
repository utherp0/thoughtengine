package org.uth.thoughtEngine.exceptions;

/**
 * Statement exception (format, contents etc).
 * @author Ian Lawson
 */
public class StatementException extends Exception
{
  public StatementException()
  {
    
  }
  
  public StatementException( String message )
  {
    super( message );
  }
}
