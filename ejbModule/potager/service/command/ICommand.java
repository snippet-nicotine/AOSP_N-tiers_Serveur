package potager.service.command;

import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerSuppressionException;

public interface ICommand {
	
	public void execute(int idPotager) throws DaoPotagerSuppressionException, DaoPotagerGetException;

}
