package at.fhj.swd14.pse.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhj.swd14.pse.user.UserDto;

public class MessageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private MessageDto parent;
	private List<MessageDto> childs;
	private UserDto creator;
	
	private Long communityId; //TODO: add object-composition as soon as the communityDto is implemented 

	private String title;
	private String content;
	private Date creationDate;
	
	public MessageDto() {
		childs = new ArrayList<>();
	}

	public MessageDto(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MessageDto getParent() {
		return parent;
	}

	public void setParent(MessageDto parent) {
		parent.addChild(this);
		this.parent = parent;
	}

	public UserDto getCreator() {
		return creator;
	}

	public void setCreator(UserDto creator) {
		this.creator = creator;
	}

	public List<MessageDto> getChilds() {
		return childs;
	}
	public void setChilds(List<MessageDto> childs){
		this.childs = childs;
	}
	
	public void addChild(MessageDto message){
		if(childs == null)
			childs = new ArrayList<>();
		childs.add(message);
	}

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long communityId) {
		this.communityId = communityId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString(){
		return "MessageDto{" +
                "id=" + getId() +
                "parentId=" + getParent().getId() +
                ", userId='" + getCreator().getId() + '\'' +
                ", communityId='" + getCommunityId() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
	}

}
