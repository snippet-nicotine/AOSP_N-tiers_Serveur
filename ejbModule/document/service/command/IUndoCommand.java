package document.service.command;

import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentModificationException;
import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerModificationException;

public interface IUndoCommand extends ICommand{
	
	public void undo() throws DaoDocumentAjoutException, DaoDocumentModificationException;

}
