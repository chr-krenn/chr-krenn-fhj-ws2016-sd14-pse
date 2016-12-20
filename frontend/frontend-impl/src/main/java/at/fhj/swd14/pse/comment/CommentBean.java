package at.fhj.swd14.pse.comment;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.message.MessageService;
import at.fhj.swd14.pse.comment.CommentService;

@Named
@RequestScoped
public class CommentBean
	implements Serializable {
	
	private static final long serialVersionUID = -123L;
	private final Logger LOGGER = LogManager.getLogger(CommentBean.class);
	
	@EJB(name = "ejb/MessageService")
	private MessageService messageService;
	
	@EJB(name = "ejb/UserService")
	private UserService userService;
	
	@EJB(name = "ejb/CommentService")
	private CommentService commentService;
	
	private CommentDto comment;
	
	public void setComment(CommentDto comment) {
		this.comment = comment;
	}
	
	public CommentDto getComment() {
		return comment;
	}
	
	public CommentBean() {
    	LOGGER.debug("Create: " + CommentBean.class.getSimpleName());
    	this.comment = new CommentDto();
    	this.comment.setText("");
	}

	public void createComment(Long messageId) {
		
		if(comment.getText().isEmpty()) {
			LOGGER.debug("Can't insert empty comment!");
		}

		long userID =  ((at.fhj.swd14.pse.security.DatabasePrincipal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();

		UserDto currentUser = userService.find(userID);
		MessageDto message = messageService.find(messageId);
		

		LOGGER.debug("Creating comment for message with ID " + messageId + "...");

		comment.setParentMessage(message);
		comment.setAuthor(currentUser);

		long id_inserted = commentService.save(comment);
		
		LOGGER.debug("Inserted comment with ID " + id_inserted);
		this.comment = new CommentDto();

    }
}
