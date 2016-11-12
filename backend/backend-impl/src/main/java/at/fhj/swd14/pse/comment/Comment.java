package at.fhj.swd14.pse.comment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.message.Message;

@Entity
@Table(name = "comment")
@NamedQueries({
	@NamedQuery(name="Comment.findByAuthorId", query="SELECT c FROM Comment c WHERE c.author.id = :authorUserId"),
	@NamedQuery(name="Comment.findByMessageId", query="SELECt c FROM Comment c WHERE c.parentMessage.id = :messageId"),
})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Column
	private String text;

	@ManyToOne
	private User author;

	@ManyToOne
	private Message parentMessage;

	public Comment() {

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
	
	@Override
	public String toString(){
		return "Comment{" +
                "id=" + getId() +
                ", userId='" + getAuthor() + '\'' +
                ", messageId='" + getParentMessage().getId() + '\'' +
                '}';
	}

}
