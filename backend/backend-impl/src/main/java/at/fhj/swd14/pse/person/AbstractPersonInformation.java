package at.fhj.swd14.pse.person;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Abstract Super Class for ManyToOne Text Information Entitys for the Person class
 * @author Patrick Kainz
 *
 */
@MappedSuperclass
public abstract class AbstractPersonInformation {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	@Column
	private String value;
	
	@ManyToOne(optional=false)
	private Person person;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractPersonInformation(Long id) {
		this.id = id;
	}
	
	public AbstractPersonInformation(String value)
	{
		setValue(value);
	}
	
	public AbstractPersonInformation()
	{
		
	}
	
}
