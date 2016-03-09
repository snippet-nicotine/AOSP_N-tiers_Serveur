package potager.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerModificationException;
import potager.dao.exception.DaoPotagerQueryException;
import potager.dao.exception.DaoPotagerSuppressionException;
import potager.entity.Potager;
import utilisateur.entity.Jardinier;

@Stateless
@Remote(DaoGestionPotager.class)
public class FacadeDaoGestionPotager implements DaoGestionPotager{
	
	@EJB
	DaoPotager daoPotager;

	@Override
	public Potager ajouterPotager(Potager potager) throws DaoPotagerAjoutException {
		return daoPotager.ajouterPotager(potager);		
	}
	
	@Override
	public Potager getPotager(int idPotager) throws DaoPotagerGetException {
		return daoPotager.getPotager(idPotager);
	}

	@Override
	public Potager modifierPotager(Potager potager) throws DaoPotagerModificationException {
		return daoPotager.modifierPotager(potager);
	}

	@Override
	public void supprimerPotager(int idPotager) throws DaoPotagerSuppressionException, DaoPotagerGetException {
		daoPotager.supprimerPotager(idPotager);
		
	}

	@Override
	public List<Potager> listerPotager() throws DaoPotagerQueryException {
		return daoPotager.listerPotager();
	}

	@Override
	public List<Potager> listerPotager(Jardinier proprietaire) {
		return daoPotager.listerPotager(proprietaire);
	}

	@Override
	public List<Potager> listerPotager(String codePostal) {
		return daoPotager.listerPotager(codePostal);
	}
	
	



}
