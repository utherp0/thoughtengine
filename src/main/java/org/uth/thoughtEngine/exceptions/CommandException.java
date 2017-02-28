/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uth.thoughtEngine.exceptions;

/**
 * Command exception (format, contents etc).
 * @author Ian Lawson
 */
public class CommandException extends Exception
{
  public CommandException()
  {
    
  }
  
  public CommandException( String message )
  {
    super( message );
  }
}