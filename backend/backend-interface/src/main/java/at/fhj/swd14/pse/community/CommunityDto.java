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
    
    private String description;
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private boolean publicState = false;
    public boolean getPublicState(){
    	return this.publicState;
    }    
    public void setPublicState(boolean publicState){
    	this.publicState = publicState;
    }
    
    private boolean activeState = false;
    public boolean getActiveState(){
    	return this.activeState;
    }    
    public void setActiveState(boolean activeState){
    	this.activeState = activeState;
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
    
    public void setCreateTime(Date createDate){
    	this.createTime = createDate;
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
    
    private List<UserDto> pendingUsers = new ArrayList<UserDto>();
	
	public List<UserDto> getPendingUsers() {
		return pendingUsers;
	}

	public void setPendingUsers(List<UserDto> pendingUsers) {
		this.pendingUsers = pendingUsers;
	}
	
	public void addPendingUser(UserDto user){    	
    	this.pendingUsers.add(user);
    }
    
    public void deletePendingUser(Long userId){
    	this.pendingUsers.removeIf(user -> user.getId() == userId);
    }

	@Override
	public String toString(){
		return "CommunityDto{" +
                "id=" + getId() +
                ", userId='" + getAuthor() + '\'' +               
                '}';
	}
}
