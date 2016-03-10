package document.service.command;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import document.dao.DaoGestionDocument;
import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentGetException;
import document.dao.exception.DaoDocumentModificationException;
import document.dao.exception.DaoDocumentSuppressionException;
import document.entity.Document;


public class CommandeSupprimer implements IUndoCommand{
	
	DaoGestionDocument daoGestionDocument;
	private int idDocument;
	private Document document;
	
	public CommandeSupprimer(){
		
		try {
			
			daoGestionDocument = (DaoGestionDocument) new InitialContext().lookup("java:global/AOSP_N-tiers_Serveur/FacadeDaoGestionDocument!document.dao.DaoGestionDocument");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void execute(int idDocument) throws DaoDocumentSuppressionException, DaoDocumentGetException {
		
		System.out.println("je supprime " + idDocument);
		this.idDocument = idDocument;
		document = daoGestionDocument.getDocument(idDocument);
		daoGestionDocument.supprimerDocument(idDocument);
	}

	@Override
	public void undo() throws DaoDocumentAjoutException, DaoDocumentModificationException {
		System.out.println("j'annule la suppression de " + idDocument);
		daoGestionDocument.modifierDocument(document);
		
	}

}
