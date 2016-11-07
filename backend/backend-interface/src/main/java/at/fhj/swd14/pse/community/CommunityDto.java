package at.fhj.swd14.pse.community;

import at.fhj.swd14.pse.user.UserDto;

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
	
	private UserDto author;
	public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }
	
	@Override
	public String toString(){
		return "CommunityDto{" +
                "id=" + getId() +
                ", userId='" + getAuthor() + '\'' +               
                '}';
	}
}
