package at.fhj.swd14.pse.message;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
        @NamedQuery(name = "Message.findByAuthorId", query = "SELECT m FROM Message m WHERE m.author.id = :userId"),
        @NamedQuery(name = "Message.findUsersPrivateMessage", query = "SELECT m FROM Message m WHERE m.recipient IS NOT NULL "
                + "AND m.recipient.id = :userId"),
        @NamedQuery(name = "Message.findByCommunityId", query = "SELECt m FROM Message m WHERE m.community.id = :communityId"),
        @NamedQuery(name = "Message.findGlobalMessages", query = "SELECT m FROM Message m WHERE m.community IS NULL AND "
                + "m.recipient IS NULL"),
        @NamedQuery(name = "Message.findUserRelated", query = "SELECT m FROM Message m "
        		+ "LEFT JOIN m.community c "
        		+ "LEFT JOIN c.userCommunities cu "
        		+ "WHERE m.recipient.id = :userId OR m.author.id = :userId OR c.author.id = :userId OR cu.user.id = :userId")
})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "parentMessage")
    private List<Comment> childs = new LinkedList<>();

    @ManyToOne
    private User author;

    @ManyToOne(optional = true)
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
    private Timestamp created;

    @Column(insertable = false, updatable = false)
    private Timestamp modified;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "like_for_message", joinColumns = @JoinColumn(name = "message_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Message() {
    }

    public Message(Long id) {
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

    public void setChilds(List<Comment> childs) {
        this.childs = childs;
    }

    public void addChild(Comment child) {
        if (childs == null) {
            childs = new LinkedList<>();
        }
        child.setParentMessage(this);
        childs.add(child);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
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
    
    public void setCreated(Timestamp created){
    	this.created = created;
    }
    public Timestamp getCreated() {
        return created;
    }
    
    public void setModified(Timestamp modified){
    	this.modified = modified;
    }
    public Timestamp getModified() {
        return modified;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                '}';
    }

}
