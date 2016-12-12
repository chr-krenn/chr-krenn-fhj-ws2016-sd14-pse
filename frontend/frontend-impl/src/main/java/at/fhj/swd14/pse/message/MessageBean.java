package at.fhj.swd14.pse.message;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

@Named
@RequestScoped
public class MessageBean implements Serializable {

	private static final long serialVersionUID = -163448660457877436L;

	private static final Logger LOGGER = LogManager.getLogger(MessageBean.class);

    @EJB(name = "ejb/MessageService")
    private MessageService messageService;
    
    @EJB(name = "ejb/PersonService")
    private PersonService personService;

    @EJB(name = "ejb/UserService")
    private UserService userService;
    
    /**
     * The message to be transmitted
     */
    private MessageDto message;
    
    /**
     * The user that posts the message
     */
    private UserDto currentUser;
	
    public MessageBean() {
    	LOGGER.debug("Create: " + MessageBean.class.getSimpleName());
    	this.message = new MessageDto();
    }
    
    public MessageDto getMessage() {
    	return this.message;
    }
    
    /**
     * Saves a message via MessageService
     */
    public void createMessage() {
    	LOGGER.info("Creating message");
        // TODO: add author and community/recipient
    	if(currentUser == null) {
            long userID =  ((at.fhj.swd14.pse.security.DatabasePrincipal) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal()).getUserId();
            currentUser = userService.find(userID); 
    	}
    	
    	if(currentUser != null) {
    		if(message.getContent() != null && message.getTitle() != null) {
    			message.setAuthor(currentUser);
    			// TODO: insert accurate community - may stay null, if not a local message!
    			
    			
    			// TODO: insert accurate recipient - may stay null, if not a private message!

    	    	final long generatedId = messageService.save(message);
    	    	LOGGER.info("Created new message with ID {}", generatedId);
    	    	//this.message = messageService.find(generatedId);
    	    	this.message = new MessageDto();
    		}
    	
    	} else {
    		LOGGER.warn("Not able to access user Id for message creation!");
    	}
    	
    }
}
