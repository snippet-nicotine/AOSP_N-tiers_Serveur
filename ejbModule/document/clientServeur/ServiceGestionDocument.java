package document.clientServeur;

import java.util.List;

import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentQueryException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.dao.exception.DaoLocalisationException;
import document.entity.Document;
import document.entity.Localisation;
import document.service.exception.DocumentException;

public interface ServiceGestionDocument {
	
	public Document ajouterDocument(Document document)
			throws DocumentException, DaoDocumentAjoutException;;
	
	public Document creerDocument(String titre, String descriptif, int nbExemplairesDispo, Localisation localisation) 
			throws DocumentException, DaoDocumentAjoutException;
	
	public Document getDocument(int idDocument) throws DaoDocumentGetException;
	
	public Document modifierDocument(Document document) throws DaoDocumentModificationException, DocumentException;
	
	public void    supprimerDocument(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException;
	
	public List<Localisation> listerLocalisation() throws DaoLocalisationException;
	
	public void annuler() throws DaoDocumentAjoutException, DaoDocumentModificationException;
	
	public int getNombreAnnulations();
	
	/**
	 * Renvoie tout les documents de l'application
	 * @return
	 * @throws DaoDocumentQueryException 
	 */
	public List<Document> listerDocument() throws DaoDocumentQueryException;

	
}
