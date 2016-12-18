package at.fhj.swd14.pse.department;


import java.io.Serializable;

/**
 * Dto for Department Entity
 * @author Patrick Kainz
 *
 */
public class DepartmentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;

	public DepartmentDto(Long id)
	{
		setId(id);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
