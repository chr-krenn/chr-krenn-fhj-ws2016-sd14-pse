package at.fhj.swd14.pse.message;


import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.user.User;

@Entity
@Table(name = "message")
@NamedQueries({
	@NamedQuery(name="Message.findByAuthorId", query="SELECT m FROM Message m WHERE m.author.id = :authorUserId"),
	@NamedQuery(name="Message.findUsersPrivateMessages",query="SELECT m FROM Message m WHERE m.recipient != NULL "
			+ "AND m.recipient.id = :userId"),
	

	//TODO: finish query so that only relevant messages are returned (global, own, joined Community)
	// especially: change to community entity as soon as it's implemented!!
	@NamedQuery(name="Message.findByCommunityId", query="SELECt m FROM Message m WHERE m.communityId = :communityId"),
	@NamedQuery(name="Message.findUserRelated", query="SELECT m FROM Message m"),
	@NamedQuery(name="Message.findGlobalMessages", query="SELECT m FROM Message m WHERE m.communityId = NULL AND "
			+ "m.recipient = NULL")
	
	
})
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy="parentMessage")
    private List<Comment> childs;
    
    @ManyToOne
    private User author;
    
    @ManyToOne
    private User recipient;
	
    @Column
	private Long communityId; //TODO: add community relation as soon as the community-entity is implemented

    @Size(max = 256)
    @NotNull
    @Column
	private String title;
    
    @Size(max = 1024)
    @NotNull
    @Column
	private String content;
    
    @Column(insertable = false, updatable = false)
    private Instant created;

    @Column(insertable = false, updatable = false)
    private Instant modified;
    
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
    public Instant getCreated() {
        return created;
    }

    public Instant getModified() {
        return modified;
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
