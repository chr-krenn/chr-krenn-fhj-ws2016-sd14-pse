package at.fhj.swd14.pse.department;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import at.fhj.swd14.pse.person.Person;

/**
 * Department Entity
 * @author Patrick Kainz
 *
 */
@Entity
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column
	private String name;
	
	@OneToMany
	private List<Person> members;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department(Long id) {
		this.id = id;
	}
	
	public Department()
	{
		
	}
}
