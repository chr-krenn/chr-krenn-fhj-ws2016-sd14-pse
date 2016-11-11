package at.fhj.swd14.pse.community;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    
    private Date createTime= new Date(0);
    public Date getCreateTime(){
    	return createTime;
    }
    
    private List<UserDto> allowedUsers = new ArrayList<UserDto>();
    
    public List<UserDto> getAllowedUsers(){
    	
    	return this.allowedUsers;
    }
    
    public void setAllowedUsers(List<UserDto> allowedUsers){
    	this.allowedUsers = allowedUsers;
    }
    
    public void addUser(UserDto user){    	
    	this.allowedUsers.add(user);
    }
    
    public void deleteUser(Long userId){
    	this.allowedUsers.removeIf(user -> user.getId() == userId);
    }
	
	@Override
	public String toString(){
		return "CommunityDto{" +
                "id=" + getId() +
                ", userId='" + getAuthor() + '\'' +               
                '}';
	}
}
