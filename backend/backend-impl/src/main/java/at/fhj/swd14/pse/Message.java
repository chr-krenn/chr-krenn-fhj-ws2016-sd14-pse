package at.fhj.swd14.pse;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "message")
@NamedQueries({
	@NamedQuery(name="Message.findByCreatorId", query="SELECT m FROM Message m WHERE m.creatorUserId = :creatorUserId"),
	@NamedQuery(name="Message.findByCommunityId", query="SELECt m FROM Message m WHERE m.communityId = :communityId"),
	
	//TODO: finish query so that only relevant messages are returned (global, own, joined Community)
	@NamedQuery(name="Message.findUserRelated", query="SELECT m FROM Message") 
})
public class Message implements Serializable {

    
	private static final long serialVersionUID = 1L;

    @Id
    private Long id;
	
	// represents the parent of the current message
	// if not NULL, then the current message is a comment of the parent
    @Column
	private Long parentId;
    @Column
	private Long creatorUserId;
    @Column
	private Long communityId;

    @Column
	private String title;
    @Column
	private String content;
    @Column
	private Date creationDate;
    
    public Message(){
    	
    }
    public Message(Long id){
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
		return "Message{" +
                "id=" + getId() +
                "parentId=" + getParentId() +
                ", userId='" + getCreatorUserId() + '\'' +
                ", communityId='" + getCommunityId() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
	}

}
