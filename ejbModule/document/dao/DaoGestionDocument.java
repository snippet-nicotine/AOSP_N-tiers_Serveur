package document.dao;

import java.util.List;

import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentQueryException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.dao.exception.DaoLocalisationException;
import document.entity.Document;
import document.entity.Localisation;


public interface DaoGestionDocument {
	
	public Document ajouterDocument(Document document) throws DaoDocumentAjoutException;
	public Document getDocument(int idDocument) throws DaoDocumentGetException;
	public Document modifierDocument(Document document) throws DaoDocumentModificationException;
	public void    supprimerDocument(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException;
	
	public List<Document> listerDocument() throws DaoDocumentQueryException;
	
	public Localisation ajouterLocalisation(Localisation localisation) throws DaoLocalisationException;
	public Localisation getLocalisation(int idLocalisation) throws DaoLocalisationException;
	public Localisation modifierDocument(Localisation localisation) throws DaoLocalisationException;
	public void         supprimerLocalisation(int idLocalisation) throws DaoLocalisationException;
	
	public List<Localisation> listerLocalisation() throws DaoLocalisationException;

	
}
