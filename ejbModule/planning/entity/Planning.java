package planning.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Planning implements Serializable{

	private static final long serialVersionUID = -574300972300054978L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	public Planning() {
		super();
	}
	
	
	
}