package document.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

import document.dao.exception.DaoLocalisationException;
import document.entity.Localisation;
import utilisateur.entity.Jardinier;

public class DaoLocalisation {

	@PersistenceContext(unitName="NL_Hibernate")
	EntityManager em;

	/**
	 * 
	 * @param localisation
	 * @return
	 * @throws DaoLocalisationAjoutException si le localisation existe dejà, n'est pas valide, ou problème de transaction
	 */
	public Localisation ajouterLocalisation(Localisation localisation) throws DaoLocalisationException {
		
		try{
			em.persist(localisation);
			em.flush();
		}
		catch(EntityExistsException e){
			throw new DaoLocalisationException( "Le localisation à ajouter existe dejà." );
		}
		catch(IllegalArgumentException e){
			throw new DaoLocalisationException( "L'instance à ajouter n'est pas un localisation valide." );
		}
		catch(TransactionRequiredException e){
			throw new DaoLocalisationException( "Un problème de transction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		return localisation;
	}

	/**
	 * 
	 * @param localisation
	 * @return
	 * @throws DaoLocalisationModificationException si le localisation n'est pas valide, ou en cas de problème de transaction
	 */
	public Localisation modifierLocalisation(Localisation localisation) throws DaoLocalisationException {
		
		try{
			System.out.println("******** " + localisation);
			em.merge(localisation);	
			em.flush();			
		}
		catch(IllegalArgumentException  e){
			throw new DaoLocalisationException( "Le localisation à modifier n'est pas valide, ou a été supprimé." );
		}
		catch(TransactionRequiredException e){
			throw new DaoLocalisationException( "Un problème de transction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		
		return localisation;
		
	}

	/**
	 * 
	 * @param idLocalisation
	 * @throws DaoLocalisationSuppressionException
	 * @throws DaoLocalisationGetException 
	 */
	public void supprimerLocalisation(int idLocalisation) throws DaoLocalisationException{
		
		try{
			em.remove( getLocalisation(idLocalisation) );			
		}
		catch(IllegalArgumentException  e){
			throw new DaoLocalisationException( "Le localisation à supprimer n'existe pas." );
		}
		catch(TransactionRequiredException e){
			throw new DaoLocalisationException( "Un problème de transaction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		
	}

	/**
	 * Renvoie une List de localisation ordonnée par id
	 * @return
	 * @throws DaoLocalisationQueryException si la requête n'est pas valide
	 */
	public List<Localisation> listerLocalisation() throws DaoLocalisationException {
		
		try{
			return em.createQuery("SELECT p FROM Localisation p ORDER BY p.lieu").getResultList();			
		}
		catch(IllegalArgumentException e){
			throw new DaoLocalisationException("La requête: " + "SELECT p FROM Localisation p ORDER BY p.lieu" + " n'est pas valide");
		}
	}

	public Localisation getLocalisation(int idLocalisation) throws DaoLocalisationException {
		
		try{
			return em.find(Localisation.class, idLocalisation);			
		}
		catch(IllegalArgumentException  e){
			throw new DaoLocalisationException("Impossible de trouver le localisation demandé.");
		}
	}
	
}
