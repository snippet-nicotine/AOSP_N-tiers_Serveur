package document.service.command;

import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentSuppressionException;
import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerSuppressionException;

public interface ICommand {
	
	public void execute(int idPotager) throws  DaoDocumentSuppressionException, DaoDocumentGetException;

}
