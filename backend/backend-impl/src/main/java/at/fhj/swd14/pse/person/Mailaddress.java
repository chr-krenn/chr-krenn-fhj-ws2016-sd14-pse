package at.fhj.swd14.pse.person;

import javax.persistence.Entity;

/**
 * Mailaddress entity
 * @author Patrick Kainz
 *
 */
@Entity
public class Mailaddress extends AbstractPersonInformation {
	private static final long serialVersionUID = 1L;

	public Mailaddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mailaddress(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Mailaddress(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
}
