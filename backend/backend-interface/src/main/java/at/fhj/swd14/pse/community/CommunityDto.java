package at.fhj.swd14.pse.community;

public class CommunityDto {

	private Long id;
	public CommunityDto(Long id){
    	setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
