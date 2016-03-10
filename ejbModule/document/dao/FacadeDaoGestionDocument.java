package document.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentQueryException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.entity.Document;
import utilisateur.entity.Jardinier;

@Stateless
@Remote(DaoGestionDocument.class)
public class FacadeDaoGestionDocument implements DaoGestionDocument{
	
	@EJB
	DaoDocument daoDocument;

	@Override
	public Document ajouterDocument(Document document) throws DaoDocumentAjoutException {
		return daoDocument.ajouterDocument(document);		
	}
	
	@Override
	public Document getDocument(int idDocument) throws DaoDocumentGetException {
		return daoDocument.getDocument(idDocument);
	}

	@Override
	public Document modifierDocument(Document document) throws DaoDocumentModificationException {
		return daoDocument.modifierDocument(document);
	}

	@Override
	public void supprimerDocument(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException {
		daoDocument.supprimerDocument(idDocument);
		
	}

	@Override
	public List<Document> listerDocument() throws DaoDocumentQueryException {
		return daoDocument.listerDocument();
	}

	@Override
	public List<Document> listerDocument(Jardinier proprietaire) {
		return daoDocument.listerDocument(proprietaire);
	}

	@Override
	public List<Document> listerDocument(String codePostal) {
		return daoDocument.listerDocument(codePostal);
	}
	
	



}
