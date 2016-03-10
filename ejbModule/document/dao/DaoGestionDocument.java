package document.dao;

import java.util.List;

import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentQueryException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.entity.Document;
import utilisateur.entity.Jardinier;


public interface DaoGestionDocument {
	
	public Document ajouterDocument(Document document) throws DaoDocumentAjoutException;
	public Document getDocument(int idDocument) throws DaoDocumentGetException;
	public Document modifierDocument(Document document) throws DaoDocumentModificationException;
	public void    supprimerDocument(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException;
	
	public List<Document> listerDocument() throws DaoDocumentQueryException;
	public List<Document> listerDocument(Jardinier proprietaire);
	public List<Document> listerDocument(String codePostal);
	
}
