package document.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentQueryException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.entity.Document;
import utilisateur.entity.Jardinier;

@Stateless
@LocalBean
public class DaoDocument{
	
	@PersistenceContext(unitName="NL_Hibernate")
	EntityManager em;

	/**
	 * 
	 * @param document
	 * @return
	 * @throws DaoDocumentAjoutException si le document existe dej�, n'est pas valide, ou probl�me de transaction
	 */
	public Document ajouterDocument(Document document) throws DaoDocumentAjoutException {
		
		try{
			em.persist(document);
			em.flush();
		}
		catch(EntityExistsException e){
			throw new DaoDocumentAjoutException( "Le document � ajouter existe dej�." );
		}
		catch(IllegalArgumentException e){
			throw new DaoDocumentAjoutException( "L'instance � ajouter n'est pas un document valide." );
		}
		catch(TransactionRequiredException e){
			throw new DaoDocumentAjoutException( "Un probl�me de transction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		return document;
	}

	/**
	 * 
	 * @param document
	 * @return
	 * @throws DaoDocumentModificationException si le document n'est pas valide, ou en cas de probl�me de transaction
	 */
	public Document modifierDocument(Document document) throws DaoDocumentModificationException {
		
		try{
			System.out.println("******** " + document);
			em.merge(document);	
			em.flush();			
		}
		catch(IllegalArgumentException  e){
			throw new DaoDocumentModificationException( "Le document � modifier n'est pas valide, ou a �t� supprim�." );
		}
		catch(TransactionRequiredException e){
			throw new DaoDocumentModificationException( "Un probl�me de transction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		
		return document;
		
	}

	/**
	 * 
	 * @param idDocument
	 * @throws DaoDocumentSuppressionException
	 * @throws DaoDocumentGetException 
	 */
	public void supprimerDocument(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException{
		
		try{
			em.remove( getDocument(idDocument) );			
		}
		catch(IllegalArgumentException  e){
			throw new DaoDocumentSuppressionException( "Le document � supprimer n'existe pas." );
		}
		catch(TransactionRequiredException e){
			throw new DaoDocumentSuppressionException( "Un probl�me de transaction est survenue.Veuillez reesayer dans quellques minutes." );
		}
		
		
	}

	/**
	 * Renvoie une List de document ordonn�e par id
	 * @return
	 * @throws DaoDocumentQueryException si la requ�te n'est pas valide
	 */
	public List<Document> listerDocument() throws DaoDocumentQueryException {
		
		try{
			return em.createQuery("SELECT p FROM Document p ORDER BY p.titre").getResultList();			
		}
		catch(IllegalArgumentException e){
			throw new DaoDocumentQueryException("La requ�te: " + "SELECT p FROM Document p ORDER BY p.titre" + " n'est pas valide");
		}
	}

	public List<Document> listerDocument(Jardinier proprietaire) {
		// TODO Lister les documents par propri�taire
		return null;
	}

	public List<Document> listerDocument(String codePostal) {
		// TODO Lister les documents par code postal
		return null;
	}

	public Document getDocument(int idDocument) throws DaoDocumentGetException {
		
		try{
			return em.find(Document.class, idDocument);			
		}
		catch(IllegalArgumentException  e){
			throw new DaoDocumentGetException("Impossible de trouver le document demand�.");
		}
	}
	
}
