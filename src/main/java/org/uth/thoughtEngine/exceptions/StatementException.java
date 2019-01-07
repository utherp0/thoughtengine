package org.uth.thoughtEngine.exceptions;

/**
 * Statement exception (format, contents etc).
 * @author Ian 'Uther' Lawson
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
