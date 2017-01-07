package at.fhj.swd14.pse.comment;

import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "comment")
@NamedQueries({
        @NamedQuery(name = "Comment.findByAuthorId", query = "SELECT c FROM Comment c WHERE c.author.id = :authorUserId"),
        @NamedQuery(name = "Comment.findByMessageId", query = "SELECt c FROM Comment c WHERE c.parentMessage.id = :messageId"),
})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    private Message parentMessage;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "like_for_comment", joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Comment() {
        // default public constructor
    }

    public Comment(Long id) {
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Message getParentMessage() {
        return parentMessage;
    }

    public void setParentMessage(Message parentMessage) {
        this.parentMessage = parentMessage;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Column(insertable = false, updatable = false)
    private Instant created;

    public Instant getCreated() {
        return created;
    }

    @Column(insertable = false, updatable = false)
    private Instant modified;

    public Instant getModified() {
        return modified;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + getId() +
                ", userId='" + getAuthor() + '\'' +
                ", messageId='" + getParentMessage().getId() + '\'' +
                '}';
    }

}
