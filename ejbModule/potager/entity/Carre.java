package potager.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

//TODO: A Implementer
@Entity
public class Carre implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8695011214229733217L;
	@Id
	private int idCarre;
	
	public Carre(){
		
	}

}
