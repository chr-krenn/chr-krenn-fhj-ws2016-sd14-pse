package at.fhj.swd14.pse.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Phonenumber entity
 * @author Patrick Kainz
 *
 */
@Entity
@Table(name="phonenumber")
@NamedQuery(name="Phonenumber.findByValue", query="SELECT i FROM Phonenumber i WHERE i.person.id = :personid AND i.value=:value")
public class Phonenumber extends AbstractPersonInformation implements Serializable {

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
