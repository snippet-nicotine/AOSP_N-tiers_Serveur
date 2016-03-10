package document.clientServeur;

import java.util.List;

import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentQueryException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.entity.Document;
import document.service.exception.CPDocumentException;
import document.service.exception.DimensionDocumentException;
import document.service.exception.NomDocumentException;
import document.service.exception.ProprietaireDocumentException;
import utilisateur.entity.Jardinier;

public interface ServiceGestionDocument {
	
	public Document ajouterDocument(Document document)
			throws NomDocumentException, CPDocumentException, ProprietaireDocumentException, DimensionDocumentException, DaoDocumentAjoutException;;
	
	public Document creerDocument(String titre, String descriptif, int nbExemplairesDispo) 
			throws NomDocumentException, CPDocumentException, ProprietaireDocumentException, DimensionDocumentException, DaoDocumentAjoutException;
	
	public Document getDocument(int idDocument) throws DaoDocumentGetException;
	
	public Document modifierDocument(Document document) throws DaoDocumentModificationException, NomDocumentException, CPDocumentException, ProprietaireDocumentException, DimensionDocumentException;
	
	public void    supprimerDocument(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException;
	
	public void annuler() throws DaoDocumentAjoutException, DaoDocumentModificationException;
	
	public int getNombreAnnulations();
	
	/**
	 * Renvoie tout les documents de l'application
	 * @return
	 * @throws DaoDocumentQueryException 
	 */
	public List<Document> listerDocument() throws DaoDocumentQueryException;

	
}
