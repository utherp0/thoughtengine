package org.uth.thoughtEngine.exceptions;

/**
 * Memory exception - this is when a request to update the TTE memory is invalid.
 * @author Ian Lawson
 */
public class TTEMemoryException extends Exception
{
  public TTEMemoryException()
  {
    
  }
  
  public TTEMemoryException( String message )
  {
    super( message );
  }
}
