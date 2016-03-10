package document.service.controlleurs;

import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import document.dao.DaoGestionDocument;
import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentQueryException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.entity.Document;
import document.service.command.CommandeSupprimer;
import document.service.command.HistoriqueDocument;
import document.service.command.IUndoCommand;
import document.service.exception.CPDocumentException;
import document.service.exception.DimensionDocumentException;
import document.service.exception.NomDocumentException;
import document.service.exception.ProprietaireDocumentException;

@Stateful
@LocalBean
public class ControlleurDocument {
	
	@EJB
	DaoGestionDocument daoGestionDocument;
	@EJB
	HistoriqueDocument historique;
	
	// TODO: Refactorer dans DocumentManager
	public Document creerDocument(String title, String descriptif, int nbExemplairesDispo) 
			throws NomDocumentException, CPDocumentException, ProprietaireDocumentException, DimensionDocumentException, DaoDocumentAjoutException{
		
		Document document = null;
		document = new Document(title, descriptif, nbExemplairesDispo);
		checkDocument(document);
		
		return daoGestionDocument.ajouterDocument(document);
				
	}

	// TODO: regexp de check du codepostal
	private boolean checkCodePostal(String codePostal) {
		
		String  regex = "^((0[1-9])|([1-8][0-9])|(9[0-8])|(2A)|(2B))[0-9]{3}$";		 
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(codePostal).matches();
	}
	
	public Document modifierDocument(Document document) throws DaoDocumentModificationException, NomDocumentException, CPDocumentException, ProprietaireDocumentException, DimensionDocumentException{
		
		System.out.println("******** service.modifier: " + document);
		checkDocument(document);
		return daoGestionDocument.modifierDocument(document);
		
	}
	
	public void supprimerDocument(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException, DaoDocumentSuppressionException {

		IUndoCommand supprimer = new CommandeSupprimer();
		supprimer.execute(idDocument);
		historique.ajouter( supprimer );

		//daoGestionDocument.supprimerDocument(idDocument);
		
	}
	
	public List<Document> listerDocument() throws DaoDocumentQueryException{
		
		return daoGestionDocument.listerDocument();
		
	}

	public Document getDocument(int idDocument) throws DaoDocumentGetException {
		return daoGestionDocument.getDocument(idDocument);
	}
	
	private void checkDocument(Document document) throws NomDocumentException, CPDocumentException, DimensionDocumentException{
		
		//TODO: Implementer le check
		
	}
	
	public void annuler() throws DaoDocumentAjoutException, DaoDocumentModificationException, DaoDocumentAjoutException, DaoDocumentModificationException{
		historique.annuler();
	}
	public int getNombreAnnulations() {
		return historique.getNbAnnulations();
	}
	
}