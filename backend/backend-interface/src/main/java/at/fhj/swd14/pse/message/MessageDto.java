package at.fhj.swd14.pse.message;

import java.io.Serializable;
import java.util.Date;

public class MessageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long parentId; 
	private Long creatorUserId;
	private Long communityId;

	private String title;
	private String content;
	private Date creationDate;
	
	public MessageDto() {
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(Long creatorUserId) {
		this.creatorUserId = creatorUserId;
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
                "parentId=" + getParentId() +
                ", userId='" + getCreatorUserId() + '\'' +
                ", communityId='" + getCommunityId() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
	}

}
