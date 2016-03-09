package potager.service.command;

import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerModificationException;

public interface IUndoCommand extends ICommand{
	
	public void undo() throws DaoPotagerAjoutException, DaoPotagerModificationException;

}
