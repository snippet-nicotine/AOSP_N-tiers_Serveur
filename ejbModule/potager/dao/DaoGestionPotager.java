package potager.dao;

import java.util.List;

import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerModificationException;
import potager.dao.exception.DaoPotagerQueryException;
import potager.dao.exception.DaoPotagerSuppressionException;
import potager.entity.Potager;
import utilisateur.entity.Jardinier;


public interface DaoGestionPotager {
	
	public Potager ajouterPotager(Potager potager) throws DaoPotagerAjoutException;
	public Potager getPotager(int idPotager) throws DaoPotagerGetException;
	public Potager modifierPotager(Potager potager) throws DaoPotagerModificationException;
	public void    supprimerPotager(int idPotager) throws DaoPotagerSuppressionException, DaoPotagerGetException;
	
	public List<Potager> listerPotager() throws DaoPotagerQueryException;
	public List<Potager> listerPotager(Jardinier proprietaire);
	public List<Potager> listerPotager(String codePostal);
	
}
