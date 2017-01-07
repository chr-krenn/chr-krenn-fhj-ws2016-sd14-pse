package at.fhj.swd14.pse.user;

import at.fhj.swd14.pse.comment.Comment;
import at.fhj.swd14.pse.community.UserCommunity;
import at.fhj.swd14.pse.message.Message;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String salt;

    @OneToMany(mappedBy = "user")
    private List<UserCommunity> userCommunities;

    @ManyToMany(mappedBy = "users")
    private List<Comment> comments;

    @ManyToMany(mappedBy = "users")
    private List<Message> messages;

    public User(Long id) {
        this.id = id;
    }

    public User() {
        // default public constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserCommunity> getCommunities() {
        return userCommunities;
    }

    public String getMail() {
        return username;
    }

    public void setMail(String mail) {
        this.username = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + username + '\'' +
                '}';
    }
}
