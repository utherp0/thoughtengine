package org.uth.thoughtEngine.exceptions;

/**
 * Export Format Exception (unsupported mode).
 * @author Ian Lawson
 */
public class ExportFormatException extends Exception
{
  public ExportFormatException()
  {
    
  }
  
  public ExportFormatException( String message )
  {
    super( message );
  }
}