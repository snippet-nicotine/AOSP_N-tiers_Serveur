package potager.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import potager.clientServeur.ServiceGestionPotager;
import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerModificationException;
import potager.dao.exception.DaoPotagerQueryException;
import potager.dao.exception.DaoPotagerSuppressionException;
import potager.entity.Potager;
import potager.service.command.Historique;
import potager.service.controlleurs.ControlleurJardinage;
import potager.service.controlleurs.ControlleurPotager;
import potager.service.exception.CPPotagerException;
import potager.service.exception.DimensionPotagerException;
import potager.service.exception.NomPotagerException;
import potager.service.exception.ProprietairePotagerException;
import utilisateur.entity.Jardinier;

@Stateless
@Remote(ServiceGestionPotager.class)
public class FacadeServiceGestionPotager implements ServiceGestionPotager{
	
	@EJB
	ControlleurJardinage controlleurJardinage;
	@EJB
	ControlleurPotager   controlleurPotager;
	@EJB
	Historique historique;

	@Override
	public Potager creerPotager(String nom, int longueur, int largeur, String codePostal, Jardinier proprietaire) 
			throws NomPotagerException, CPPotagerException, ProprietairePotagerException, 
				   DimensionPotagerException, DaoPotagerAjoutException 
	{
		
		return controlleurPotager.creerPotager(nom, longueur, largeur, codePostal, proprietaire);
		
	}

	@Override
	public Potager getPotager(int idPotager) throws DaoPotagerGetException {	
		
		return controlleurPotager.getPotager(idPotager);
		
	}

	@Override
	public Potager modifierPotager(Potager potager) throws DaoPotagerModificationException, NomPotagerException, CPPotagerException, ProprietairePotagerException, DimensionPotagerException {	
		
		return controlleurPotager.modifierPotager(potager);
		
	}

	@Override
	public List<Potager> listerPotager() throws DaoPotagerQueryException {	
		
		return controlleurPotager.listerPotager();
		
	}

	@Override
	public List<Potager> listerPotager(Jardinier proprietaire) {
		// TODO Lister les potagers par propriétaire
		return null;
	}

	@Override
	public List<Potager> listerPotager(String nomPropriete, String valeurPropriete, boolean isExact) {
		// TODO Lister les potagers par propriétaire et par la valeur d'une propriété
		return null;
	}

	@Override
	public void supprimerPotager(int idPotager) throws DaoPotagerSuppressionException, DaoPotagerGetException {
		
		controlleurPotager.supprimerPotager(idPotager);		
		
	}

	@Override
	public Potager ajouterPotager(Potager potager)
			throws NomPotagerException, CPPotagerException, ProprietairePotagerException, 
			       DimensionPotagerException, DaoPotagerAjoutException 
	{
		
		return controlleurPotager.creerPotager(potager.getNom(), potager.getLongueur(), potager.getLargeur(), potager.getCodePostal(), potager.getProprietaire() );
	
	}

	@Override
	public void annuler() throws DaoPotagerAjoutException, DaoPotagerModificationException {
		historique.annuler();
	}

	@Override
	public int getNombreAnnulations() {
		return controlleurPotager.getNombreAnnulations();
	}

	
	

}
