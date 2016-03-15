package document.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="aosp2_document")
public class Document implements Serializable{

	private static final long serialVersionUID = 5639389737306562747L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false)
	private String titre;
	
	private String descriptif;
	private int nbExemplairesDispo;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idLocalisation", unique=true)
	private Localisation localisation;
	
	public Document(){
		
	}
	
	public Document(String title, String descriptif, int nbExemplairesDispo, Localisation localisation) {
		super();
		this.titre = title;
		this.descriptif = descriptif;
		this.nbExemplairesDispo = nbExemplairesDispo;
		
		if(localisation == null){
			this.localisation = new Localisation("lieu auto", "emplacement auto");			
		} else {
			this.localisation = localisation;
		}
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String title) {
		this.titre = title;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public int getNbExemplairesDispo() {
		return nbExemplairesDispo;
	}
	public void setNbExemplairesDispo(int nbExemplairesDispo) {
		this.nbExemplairesDispo = nbExemplairesDispo;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", titre=" + titre + ", descriptif=" + descriptif + ", nbExemplairesDispo="
				+ nbExemplairesDispo + "]";
	}

	public Localisation getLocalisation() {
		return localisation;
	}

	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}
	
	

}
