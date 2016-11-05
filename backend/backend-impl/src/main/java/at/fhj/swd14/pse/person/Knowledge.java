package at.fhj.swd14.pse.person;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Knowledge Entity
 * @author Patrick Kainz
 *
 */
@Entity
public class Knowledge extends AbstractPersonInformation implements Serializable{
	private static final long serialVersionUID = 1L;

	public Knowledge() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Knowledge(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public Knowledge(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}
	
}
