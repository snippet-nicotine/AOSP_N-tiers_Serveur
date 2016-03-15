package document.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import document.clientServeur.ServiceGestionDocument;
import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentQueryException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.dao.exception.DaoLocalisationException;
import document.entity.Document;
import document.entity.Localisation;
import document.service.command.HistoriqueDocument;
import document.service.controlleurs.ControlleurDocument;
import document.service.exception.DocumentException;

@Stateless
@Remote(ServiceGestionDocument.class)
public class FacadeServiceGestionDocument implements ServiceGestionDocument{
	

	@EJB
	ControlleurDocument   controlleurDocument;
	@EJB
	HistoriqueDocument historique;

	@Override
	public Document creerDocument(String title, String descriptif, int nbExemplairesDispo, Localisation localisation) 
			throws DocumentException, DaoDocumentAjoutException 
	{
		
		return controlleurDocument.creerDocument(title, descriptif, nbExemplairesDispo, localisation);
		
	}

	@Override
	public Document getDocument(int idDocument) throws DaoDocumentGetException {	
		
		return controlleurDocument.getDocument(idDocument);
		
	}

	@Override
	public Document modifierDocument(Document document) throws DaoDocumentModificationException, DocumentException {	
		
		return controlleurDocument.modifierDocument(document);
		
	}

	@Override
	public List<Document> listerDocument() throws DaoDocumentQueryException {	
		
		return controlleurDocument.listerDocument();
		
	}

	@Override
	public void supprimerDocument(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException {
		
		controlleurDocument.supprimerDocument(idDocument);		
		
	}

	@Override
	public Document ajouterDocument(Document document)
			throws DocumentException, DaoDocumentAjoutException 
	{
		
		return controlleurDocument.creerDocument(document.getTitre(), document.getDescriptif(), document.getNbExemplairesDispo(), document.getLocalisation());
	
	}

	@Override
	public void annuler() throws DaoDocumentAjoutException, DaoDocumentModificationException {
		historique.annuler();
	}

	@Override
	public int getNombreAnnulations() {
		return controlleurDocument.getNombreAnnulations();
	}

	@Override
	public List<Localisation> listerLocalisation() throws DaoLocalisationException{
		return controlleurDocument.listerLocalisations();
	}

	
	

}
