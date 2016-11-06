package at.fhj.swd14.pse.person;

import javax.persistence.Entity;

/**
 * Hobby entity
 * @author Patrick Kainz
 *
 */
@Entity
public class Hobby extends AbstractPersonInformation {

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
