package potager.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

import potager.dao.exception.DaoPotagerAjoutException;
import potager.dao.exception.DaoPotagerGetException;
import potager.dao.exception.DaoPotagerModificationException;
import potager.dao.exception.DaoPotagerQueryException;
import potager.dao.exception.DaoPotagerSuppressionException;
import potager.entity.Potager;
import utilisateur.entity.Jardinier;

@Stateless
@LocalBean
public class DaoPotager{
	
	@PersistenceContext(unitName="H2_Persistence")
	EntityManager em;

	/**
	 * 
	 * @param potager
	 * @return
	 * @throws DaoPotagerAjoutException si le potager existe dej�, n'est pas valide, ou probl�me de transaction
	 */
	public Potager ajouterPotager(Potager potager) throws DaoPotagerAjoutException {
		
		try{
			em.persist(potager);
			em.flush();
		}
		catch(EntityExistsException e){
			throw new DaoPotagerAjoutException( "Le potager � ajouter existe dej�." );
		}
		catch(IllegalArgumentException e){
			throw new DaoPotagerAjoutException( "L'instance � ajouter n'est pas un potager valide." );
		}
		catch(TransactionRequiredException e){
			throw new DaoPotagerAjoutException( "Un probl�me de transction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		return potager;
	}

	/**
	 * 
	 * @param potager
	 * @return
	 * @throws DaoPotagerModificationException si le potager n'est pas valide, ou en cas de probl�me de transaction
	 */
	public Potager modifierPotager(Potager potager) throws DaoPotagerModificationException {
		
		try{
			em.merge(potager);	
			em.flush();			
		}
		catch(IllegalArgumentException  e){
			throw new DaoPotagerModificationException( "Le potager � modifier n'est pas valide, ou a �t� supprim�." );
		}
		catch(TransactionRequiredException e){
			throw new DaoPotagerModificationException( "Un probl�me de transction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		
		return potager;
		
	}

	/**
	 * 
	 * @param idPotager
	 * @throws DaoPotagerSuppressionException
	 * @throws DaoPotagerGetException 
	 */
	public void supprimerPotager(int idPotager) throws DaoPotagerSuppressionException, DaoPotagerGetException{
		
		try{
			em.remove( getPotager(idPotager) );			
		}
		catch(IllegalArgumentException  e){
			throw new DaoPotagerSuppressionException( "Le potager � supprimer n'existe pas." );
		}
		catch(TransactionRequiredException e){
			throw new DaoPotagerSuppressionException( "Un probl�me de transaction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		
	}

	/**
	 * Renvoie une List de potager ordonn�e par id
	 * @return
	 * @throws DaoPotagerQueryException si la requ�te n'est pas valide
	 */
	public List<Potager> listerPotager() throws DaoPotagerQueryException {
		
		try{
			return em.createQuery("SELECT p FROM Potager p ORDER BY p.nom").getResultList();			
		}
		catch(IllegalArgumentException e){
			throw new DaoPotagerQueryException("La requ�te: " + "SELECT p FROM Potager p ORDER BY p.idPotager" + " n'est pas valide");
		}
	}

	public List<Potager> listerPotager(Jardinier proprietaire) {
		// TODO Lister les potagers par propri�taire
		return null;
	}

	public List<Potager> listerPotager(String codePostal) {
		// TODO Lister les potagers par code postal
		return null;
	}

	public Potager getPotager(int idPotager) throws DaoPotagerGetException {
		
		try{
			return em.find(Potager.class, idPotager);			
		}
		catch(IllegalArgumentException  e){
			throw new DaoPotagerGetException("Impossible de trouver le potager demand�.");
		}
	}
	
}
