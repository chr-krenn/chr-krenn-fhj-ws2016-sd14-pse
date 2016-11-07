package at.fhj.swd14.pse.person;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Mailaddress entity
 * @author Patrick Kainz
 *
 */
@Entity
@Table(name="mailaddress")
public class Mailaddress extends AbstractPersonInformation {
	private static final long serialVersionUID = 1L;

	public Mailaddress() {
		super();
	}

	public Mailaddress(Long id) {
		super(id);
	}

	public Mailaddress(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
}
