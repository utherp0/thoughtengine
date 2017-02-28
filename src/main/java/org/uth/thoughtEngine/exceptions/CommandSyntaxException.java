/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uth.thoughtEngine.exceptions;

/**
 * Command Syntax exception (format, contents etc).
 * @author Ian Lawson
 */
public class CommandSyntaxException extends Exception
{
  public CommandSyntaxException()
  {
    
  }
  
  public CommandSyntaxException( String message )
  {
    super( message );
  }
}
