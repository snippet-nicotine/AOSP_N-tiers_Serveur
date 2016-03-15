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
	 * @throws DaoLocalisationAjoutException si le localisation existe dej�, n'est pas valide, ou probl�me de transaction
	 */
	public Localisation ajouterLocalisation(Localisation localisation) throws DaoLocalisationException {
		
		try{
			em.persist(localisation);
			em.flush();
		}
		catch(EntityExistsException e){
			throw new DaoLocalisationException( "Le localisation � ajouter existe dej�." );
		}
		catch(IllegalArgumentException e){
			throw new DaoLocalisationException( "L'instance � ajouter n'est pas un localisation valide." );
		}
		catch(TransactionRequiredException e){
			throw new DaoLocalisationException( "Un probl�me de transction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		return localisation;
	}

	/**
	 * 
	 * @param localisation
	 * @return
	 * @throws DaoLocalisationModificationException si le localisation n'est pas valide, ou en cas de probl�me de transaction
	 */
	public Localisation modifierLocalisation(Localisation localisation) throws DaoLocalisationException {
		
		try{
			System.out.println("******** " + localisation);
			em.merge(localisation);	
			em.flush();			
		}
		catch(IllegalArgumentException  e){
			throw new DaoLocalisationException( "Le localisation � modifier n'est pas valide, ou a �t� supprim�." );
		}
		catch(TransactionRequiredException e){
			throw new DaoLocalisationException( "Un probl�me de transction est survenue.Veuillez reesayer dans quellques minutes." );
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
			throw new DaoLocalisationException( "Le localisation � supprimer n'existe pas." );
		}
		catch(TransactionRequiredException e){
			throw new DaoLocalisationException( "Un probl�me de transaction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		
	}

	/**
	 * Renvoie une List de localisation ordonn�e par id
	 * @return
	 * @throws DaoLocalisationQueryException si la requ�te n'est pas valide
	 */
	public List<Localisation> listerLocalisation() throws DaoLocalisationException {
		
		try{
			return em.createQuery("SELECT p FROM Localisation p ORDER BY p.lieu").getResultList();			
		}
		catch(IllegalArgumentException e){
			throw new DaoLocalisationException("La requ�te: " + "SELECT p FROM Localisation p ORDER BY p.lieu" + " n'est pas valide");
		}
	}

	public Localisation getLocalisation(int idLocalisation) throws DaoLocalisationException {
		
		try{
			return em.find(Localisation.class, idLocalisation);			
		}
		catch(IllegalArgumentException  e){
			throw new DaoLocalisationException("Impossible de trouver le localisation demand�.");
		}
	}
	
}
