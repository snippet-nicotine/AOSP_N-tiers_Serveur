package potager.service.controlleurs;

import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import potager.config.Configuration;
import potager.dao.DaoGestionPotager;
import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerModificationException;
import potager.dao.exception.DaoPotagerQueryException;
import potager.dao.exception.DaoPotagerSuppressionException;
import potager.entity.Potager;
import potager.service.command.CommandeSupprimer;
import potager.service.command.Historique;
import potager.service.command.IUndoCommand;
import potager.service.exception.CPPotagerException;
import potager.service.exception.DimensionPotagerException;
import potager.service.exception.NomPotagerException;
import potager.service.exception.ProprietairePotagerException;
import utilisateur.entity.Jardinier;

@Stateful
@LocalBean
public class ControlleurPotager {
	
	@EJB
	DaoGestionPotager daoGestionPotager;
	@EJB
	Historique historique;
	
	// TODO: Refactorer dans PotagerManager
	public Potager creerPotager(String nom, int longueur, int largeur, String codePostal, Jardinier proprietaire) 
			throws NomPotagerException, CPPotagerException, ProprietairePotagerException, DimensionPotagerException, DaoPotagerAjoutException{
		
		Potager potager = null;
		potager = new Potager(nom, longueur, largeur, codePostal, proprietaire);
		checkPotager(potager);
		
		return daoGestionPotager.ajouterPotager(potager);
				
	}

	// TODO: regexp de check du codepostal
	private boolean checkCodePostal(String codePostal) {
		
		String  regex = "^((0[1-9])|([1-8][0-9])|(9[0-8])|(2A)|(2B))[0-9]{3}$";		 
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(codePostal).matches();
	}
	
	public Potager modifierPotager(Potager potager) throws DaoPotagerModificationException, NomPotagerException, CPPotagerException, ProprietairePotagerException, DimensionPotagerException{
		
		checkPotager(potager);
		return daoGestionPotager.modifierPotager(potager);
		
	}
	
	public void supprimerPotager(int idPotager) throws DaoPotagerSuppressionException, DaoPotagerGetException {
		
		try {
			IUndoCommand supprimer = new CommandeSupprimer();
			supprimer.execute(idPotager);
			historique.ajouter( supprimer );
			
		} catch (DaoPotagerSuppressionException | DaoPotagerGetException e) {
			throw e;
		}
		//daoGestionPotager.supprimerPotager(idPotager);
		
	}
	
	public List<Potager> listerPotager() throws DaoPotagerQueryException{
		
		return daoGestionPotager.listerPotager();
		
	}

	public Potager getPotager(int idPotager) throws DaoPotagerGetException {
		return daoGestionPotager.getPotager(idPotager);
	}
	
	private void checkPotager(Potager potager) throws NomPotagerException, CPPotagerException, ProprietairePotagerException, DimensionPotagerException{
		
		// Control de la validité de données
		// RG 19.1 Nom, codePostal et le jardinier sont obligatoire
		if( potager.getNom().isEmpty() )                         throw new NomPotagerException("Le nom du potager doit être renseigné.");
		if( !checkCodePostal(potager.getCodePostal() ) )         throw new CPPotagerException("Le code postal n'est pas valide. (ex: 13100, 65200, 13000, ...)");
		if( potager.getProprietaire() == null )                  throw new ProprietairePotagerException("Le potager a obligatoirement un propriétaire.");
		
		// RG 19.3 La largeur et la longueur du potager >= 50cm
		if( potager.getLongueur() < Configuration.TAILLE_CARRE ) throw new DimensionPotagerException("La longueur doit être supérieur à " + Configuration.TAILLE_CARRE + "cm");
		if( potager.getLargeur()  < Configuration.TAILLE_CARRE ) throw new DimensionPotagerException("La largeur doit être supérieur à " + Configuration.TAILLE_CARRE + "cm");
				
	}
	
	public void annuler() throws DaoPotagerAjoutException, DaoPotagerModificationException{
		historique.annuler();
	}

	public int getNombreAnnulations() {
		return historique.getNbAnnulations();
	}
	
}
