package potager.service.command;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import potager.dao.DaoGestionPotager;
import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerModificationException;
import potager.dao.exception.DaoPotagerSuppressionException;
import potager.entity.Potager;


public class CommandeSupprimer implements IUndoCommand{
	
	DaoGestionPotager daoGestionPotager;
	private int idPotager;
	private Potager potager;
	
	public CommandeSupprimer(){
		
		try {
			
			daoGestionPotager = (DaoGestionPotager) new InitialContext().lookup("java:global/AOSP_N-tiers_Serveur/FacadeDaoGestionPotager!potager.dao.DaoGestionPotager");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void execute(int idPotager) throws DaoPotagerSuppressionException, DaoPotagerGetException {
		
		System.out.println("je supprime " + idPotager);
		this.idPotager = idPotager;
		potager = daoGestionPotager.getPotager(idPotager);
		daoGestionPotager.supprimerPotager(idPotager);
	}

	@Override
	public void undo() throws DaoPotagerAjoutException, DaoPotagerModificationException {
		System.out.println("j'annule la suppression de " + idPotager);
		daoGestionPotager.modifierPotager(potager);
		
	}

}
