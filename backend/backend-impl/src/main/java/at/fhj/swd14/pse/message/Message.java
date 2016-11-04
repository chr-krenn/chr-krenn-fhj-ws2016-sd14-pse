package at.fhj.swd14.pse.message;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.fhj.swd14.pse.User;

@Entity
@Table(name = "message")
@NamedQueries({
	@NamedQuery(name="Message.findByCreatorId", query="SELECT m FROM Message m WHERE m.creator.id = :creatorUserId"),
	@NamedQuery(name="Message.findByCommunityId", query="SELECt m FROM Message m WHERE m.communityId = :communityId"),

	//TODO: finish query so that only relevant messages are returned (global, own, joined Community)
	@NamedQuery(name="Message.findUserRelated", query="SELECT m FROM Message m")
})
public class Message implements Serializable {

    
	private static final long serialVersionUID = 1L;

    @Id
    private Long id;

	// represents the parent of the current message
 	// if not NULL, then the current message is a comment of the parent
    @ManyToOne
    private Message parent;
    
    @OneToMany
    private List<Message> childs;
    
    @ManyToOne
    private User creator;
	
    @Column
	private Long communityId; //TODO: add community relation as soon as the community-entity is implemented

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
	
	public Message getParent() {
		return parent;
	}
	public void setParent(Message parent) {
		parent.addChild(this);
		this.parent = parent;
	}
	
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public List<Message> getChilds() {
		return childs;
	}
	public void setChilds(List<Message> childs){
		this.childs = childs;
	}
	public void addChild(Message child){
		if(childs == null)
			childs = new ArrayList<>();
		childs.add(child);
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
                "parentId=" + getParent().getId() +
                ", userId='" + getCreator().getId() + '\'' +
                ", communityId='" + getCommunityId() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
	}

}
