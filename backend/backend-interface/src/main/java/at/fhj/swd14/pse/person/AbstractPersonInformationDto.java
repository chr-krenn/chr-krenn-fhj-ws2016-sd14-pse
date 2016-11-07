package at.fhj.swd14.pse.person;


import java.io.Serializable;

/**
 * Dto for classes of type AbstractPersonInformation
 * @author Patrick Kainz
 *
 */
public class AbstractPersonInformationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String value;
	private PersonDto person;
	
	public PersonDto getPerson() {
		return person;
	}
	public void setPerson(PersonDto person) {
		this.person = person;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public AbstractPersonInformationDto(Long id) {
		this.id = id;
	}
	public AbstractPersonInformationDto()
	{
		
	}
}
