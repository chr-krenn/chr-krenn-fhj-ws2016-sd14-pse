package at.fhj.swd14.pse.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Hobby entity
 * @author Patrick Kainz
 *
 */
@Entity
@Table(name="hobby")
@NamedQuery(name="Hobby.findByValue", query="SELECT i FROM Hobby i WHERE i.person.id = :personid AND i.value=:value")
public class Hobby extends AbstractPersonInformation implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Hobby() {
		super();
	}

	public Hobby(Long id) {
		super(id);
	}

	public Hobby(String value) {
		super(value);
	}



}
