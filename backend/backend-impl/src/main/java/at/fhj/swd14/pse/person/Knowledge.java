package at.fhj.swd14.pse.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Knowledge Entity
 * @author Patrick Kainz
 *
 */
@Entity
@Table(name="knowledge")
@NamedQuery(name="Knowledge.findByValue", query="SELECT i FROM Knowledge i WHERE i.person.id = :personid AND i.value=:value")
public class Knowledge extends AbstractPersonInformation implements Serializable{

	private static final long serialVersionUID = 1L;

	public Knowledge() {
		super();
	}

	public Knowledge(Long id) {
		super(id);
	}

	public Knowledge(String value) {
		super(value);
	}
	
}
