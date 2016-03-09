package potager.clientServeur;

import java.util.List;

import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerModificationException;
import potager.dao.exception.DaoPotagerQueryException;
import potager.dao.exception.DaoPotagerSuppressionException;
import potager.entity.Potager;
import potager.service.exception.CPPotagerException;
import potager.service.exception.DimensionPotagerException;
import potager.service.exception.NomPotagerException;
import potager.service.exception.ProprietairePotagerException;
import utilisateur.entity.Jardinier;

public interface ServiceGestionPotager {
	
	public Potager ajouterPotager(Potager potager)
			throws NomPotagerException, CPPotagerException, ProprietairePotagerException, DimensionPotagerException, DaoPotagerAjoutException;;
	
	public Potager creerPotager(String nom, int longueur, int largeur, String codePostal, Jardinier proprietaire) 
			throws NomPotagerException, CPPotagerException, ProprietairePotagerException, DimensionPotagerException, DaoPotagerAjoutException;
	
	public Potager getPotager(int idPotager) throws DaoPotagerGetException;
	
	public Potager modifierPotager(Potager potager) throws DaoPotagerModificationException, NomPotagerException, CPPotagerException, ProprietairePotagerException, DimensionPotagerException;
	
	public void    supprimerPotager(int idPotager) throws DaoPotagerSuppressionException, DaoPotagerGetException;
	
	public void annuler() throws DaoPotagerAjoutException, DaoPotagerModificationException;
	
	public int getNombreAnnulations();
	
	/**
	 * Renvoie tout les potagers de l'application
	 * @return
	 * @throws DaoPotagerQueryException 
	 */
	public List<Potager> listerPotager() throws DaoPotagerQueryException;
	
	/**
	 * Renvoie tout les potagers d'un jardinier
	 * @param proprietaire
	 * @return
	 */
	public List<Potager> listerPotager(Jardinier proprietaire);
		
	/**
	 * Renvoie une liste de potager, en recherchant par une propriï¿½tï¿½ du potager
	 * Ex: listerPotager("nom", "mon Potager", false)
	 * 
	 * @param nomPropriete     Nom de la propriété recherchï¿½e ex: "nom"
	 * @param valeurPropriete  Valeur recherchée ex: "mon potager"
	 * @param isExact          Si true, la valeur recherchï¿½e doit correspondre exactement
	 * @return
	 */
	public List<Potager> listerPotager(String nomPropriete, String valeurPropriete, boolean isExact);
	
}
