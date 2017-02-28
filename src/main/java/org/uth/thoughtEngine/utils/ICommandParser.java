package org.uth.thoughtEngine.utils;

import org.uth.thoughtEngine.core.currency.CellResponse;
import org.uth.thoughtEngine.core.memory.Cell;
import org.uth.thoughtEngine.exceptions.CommandException;
import org.uth.thoughtEngine.exceptions.CommandSyntaxException;

/**
 * Interface for all command parsers for the Thought Engine.
 * @author Ian Lawson
 */
public interface ICommandParser 
{
  public abstract CellResponse parse( Object command, String identifier, Cell cell ) throws CommandException, CommandSyntaxException;
  public abstract boolean isCoreCommand( Object command ) throws CommandException;
}
