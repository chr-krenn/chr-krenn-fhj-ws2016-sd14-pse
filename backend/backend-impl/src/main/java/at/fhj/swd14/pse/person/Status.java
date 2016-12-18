package at.fhj.swd14.pse.person;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Status entity for Person
 * @author Patrick Kainz
 *
 */
@Entity
@Table(name="person_status")
@NamedQuery(name="PersonStatus.findByName", query="SELECT s FROM Status s WHERE s.name = :status_name")
public class Status implements Serializable {

	private static final long serialVersionUID = 1L;

	public Status(String name) {
		setName(name);
	}
	
	public Status()
	{
		//just here for instantiation
	}
	
	@Id
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
