package at.fhj.swd14.pse.comment;

import java.io.Serializable;

import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.user.UserDto;

public class CommentDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String text;
    private UserDto author;
    private MessageDto parentMessage;

    public CommentDto(){
    	
    }
    public CommentDto(Long id){
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

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public MessageDto getParentMessage() {
        return parentMessage;
    }

    public void setParentMessage(MessageDto parentMessage) {
        this.parentMessage = parentMessage;
    }
    
    @Override
	public String toString(){
		return "CommentDto{" +
                "id=" + getId() +
                ", userId='" + getAuthor() + '\'' +
                ", messageId='" + getParentMessage().getId() + '\'' +
                '}';
	}
}
