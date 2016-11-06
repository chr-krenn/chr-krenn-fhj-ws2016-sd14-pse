package at.fhj.swd14.pse.person;

import javax.persistence.Entity;

/**
 * Phonenumber entity
 * @author Patrick Kainz
 *
 */
@Entity
public class Phonenumber extends AbstractPersonInformation {
	private static final long serialVersionUID = 1L;

	public Phonenumber() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phonenumber(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Phonenumber(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
}
