package at.fhj.swd14.pse.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Hobby entity
 * @author Patrick Kainz
 *
 */
@Entity
@Table(name="hobby")
public class Hobby extends AbstractPersonInformation implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Hobby() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hobby(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Hobby(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}



}
