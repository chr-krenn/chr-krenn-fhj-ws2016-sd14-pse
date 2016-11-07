package at.fhj.swd14.pse.person;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Status entity for Person
 * @author Patrick Kainz
 *
 */
@Entity
@Table(name="person_status")
public class Status implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	
	@OneToMany
	private List<Person> members;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status(String name) {
		setName(name);
	}
	
	public Status()
	{
		
	}
	
}
