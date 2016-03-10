package document.service.command;

import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import document.dao.exception.DaoDocumentAjoutException;
import document.dao.exception.DaoDocumentModificationException;

@Singleton
@LocalBean
public class HistoriqueDocument {

	private LinkedList<IUndoCommand> commandes;
	
	@PostConstruct
	public void initialiser(){
		commandes = new LinkedList<IUndoCommand>();
	}

	
	public void annuler() throws DaoDocumentAjoutException, DaoDocumentModificationException{
		
		System.out.println("commandes: " + commandes);
		IUndoCommand derniereCommande = commandes.pop();
		
		if( derniereCommande != null){
			derniereCommande.undo();
		}
				
	}
	
	public void ajouter(IUndoCommand commande){
		System.out.println("commandes: " + commandes);
		commandes.push(commande);
		System.out.println("commandes size: " + commandes.size() );
	}


	public int getNbAnnulations() {
		return commandes.size();
	}
	
	
	
}
