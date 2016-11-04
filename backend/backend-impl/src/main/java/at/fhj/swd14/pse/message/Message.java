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
import at.fhj.swd14.pse.comment.Comment;

@Entity
@Table(name = "message")
@NamedQueries({
	@NamedQuery(name="Message.findByAuthorId", query="SELECT m FROM Message m WHERE m.author.id = :authorUserId"),
	

	//TODO: finish query so that only relevant messages are returned (global, own, joined Community)
	@NamedQuery(name="Message.findByCommunityId", query="SELECt m FROM Message m WHERE m.communityId = :communityId"),
	@NamedQuery(name="Message.findUserRelated", query="SELECT m FROM Message m")
})
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    
    @OneToMany
    private List<Comment> childs;
    
    @ManyToOne
    private User author;
    
    @ManyToOne
    private User recipient;
	
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
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User creator) {
		this.author = creator;
	}
	
	public List<Comment> getChilds() {
		return childs;
	}
	public void setChilds(List<Comment> childs){
		this.childs = childs;
	}
	public void addChild(Comment child){
		if(childs == null)
			childs = new ArrayList<>();
		child.setParentMessage(this);
		childs.add(child);
	}
	
	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
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
                ", userId='" + getAuthor() + '\'' +
                ", communityId='" + getCommunityId() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
	}

}
