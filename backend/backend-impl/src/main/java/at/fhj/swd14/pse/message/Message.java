package at.fhj.swd14.pse.message;


import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.user.User;

@Entity
@Table(name = "message")
@NamedQueries({
	@NamedQuery(name="Message.findByAuthorId", query="SELECT m FROM Message m WHERE m.author.id = :authorUserId"),
	@NamedQuery(name="Message.findUsersPrivateMessage",query="SELECT m FROM Message m WHERE m.recipient IS NOT NULL "
			+ "AND m.recipient.id = :userId"),
	@NamedQuery(name="Message.findByCommunityId", query="SELECt m FROM Message m WHERE m.community.id = :communityId"),
	@NamedQuery(name="Message.findGlobalMessages", query="SELECT m FROM Message m WHERE m.community IS NULL AND "
			+ "m.recipient IS NULL"),
	
	//TODO: finish query so that only relevant messages are returned (global, own, joined Community)
	@NamedQuery(name="Message.findUserRelated", query="SELECT m FROM Message m WHERE m.recipient.id = :userId OR m.author.id = :userId")
})
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy="parentMessage")
    private List<Comment> childs = new LinkedList<>();
    
    @ManyToOne
    private User author;
    
    @ManyToOne(optional=true)
    private User recipient;

    @ManyToOne(optional = true)
    private Community community;

    @ManyToMany
	private List<Tag> tags;

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

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }
    public void addTag(Tag tag){
        if(tags == null)
            tags = new ArrayList<Tag>();
        tags.add(tag);
    }
	
	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}
	
	public Community getCommunity() {
		return community;
	}
	public void setCommunity(Community community) {
		this.community = community;
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
                ", communityId='" + getCommunity() + '\'' +
                ", title='" + getTitle() + '\'' +
                '}';
	}

}
