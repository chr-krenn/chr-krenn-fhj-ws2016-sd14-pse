package at.fhj.swd14.pse.department;


/**
 * Dto for Department Entity
 * @author Patrick Kainz
 *
 */
public class DepartmentDto {
	
	public DepartmentDto(long id)
	{
		setId(id);
	}
	
	private long id;
	
	private String name;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
