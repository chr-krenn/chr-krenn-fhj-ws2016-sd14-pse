package at.fhj.swd14.pse.person;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Status entity for Person
 * @author Patrick Kainz
 *
 */
@Entity
public class Status implements Serializable {

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

	public Status(Long id) {
		this.id = id;
	}
	
	public Status()
	{
		
	}
	
}
