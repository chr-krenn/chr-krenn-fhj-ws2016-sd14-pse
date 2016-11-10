package at.fhj.swd14.pse.message;

//TODO: Change CommunityDtoStub to CommunityDto in this file
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.community.CommunityDtoStub;
import at.fhj.swd14.pse.user.UserDto;

@Named
@Stateful
@ViewScoped
public class MessageStreamBean implements Serializable {
	// ---------- Member including Get/Set ------------
	private static final long serialVersionUID = 1L;

	/**
	 * The LOGGER to use
	 */
	private static final Logger LOGGER = LogManager.getLogger(MessageStreamBean.class);

	@EJB(name = "ejb/MessageService")
	private MessageService messageService;
	
	/**
	 * MessageDto for new messages
	 */
	private MessageDto message;
	
	public MessageDto getMessage() {
		return this.message;
	}
	
	public void setMessage(MessageDto message) {
		this.message = message;
	}

	/**
	 * The CommunityDto to use
	 */
	private CommunityDtoStub currentCommunity;

	/**
	 * Gets the CommunityDto
	 *
	 * @return The current communities DTO
	 */
	public CommunityDtoStub getCurrentCommunity() {
		return this.currentCommunity;
	}

	/**
	 * Sets the current communities DTO
	 *
	 * @param currentCommunity
	 *            The community to set as current community
	 */
	public void setCurrentCommunity(CommunityDtoStub currentCommunity) {
		this.currentCommunity = currentCommunity;
	}

	/**
	 * List of available communities
	 */
	private List<CommunityDtoStub> availableCommunities;

	/**
	 * Gets the available communities
	 *
	 * @return The available communities
	 */
	public List<CommunityDtoStub> getAvailableCommunities() {
		return availableCommunities;
	}

	/**
	 * Sets the available communities
	 *
	 * @param availableCommunities
	 *            The available communities to set
	 */
	public void setAvailableCommunities(List<CommunityDtoStub> availableCommunities) {
		this.availableCommunities = availableCommunities;
	}

	/**
	 * The Messages to display in the view
	 */
	private Map<Long, MessageDto> messages;

	/**
	 * Gets the messages to display in the view
	 *
	 * @return
	 */
	public Map<Long, MessageDto> getMessages() {
		return this.messages;
	}

	/**
	 * Sets the messages to display in the view
	 *
	 * @param messages
	 *            the messages to display in the view
	 */
	public void setMessages(Map<Long, MessageDto> messages) {
		this.messages = messages;
	}

	/**
	 * The Id of the currently logged-in User
	 */
	private long currentUserId;

	/**
	 * Gets the Id of the currently logged-in User
	 *
	 * @return
	 */
	public long getCurrentUserId() {
		return this.currentUserId;
	}

	/**
	 * Sets the Id of the currently logged-in User
	 *
	 * @param id
	 *            the Id of the currently logged-in User
	 */
	public void setCurrentUserId(long id) {
		this.currentUserId = id;
	}
	// ---------- Constructor  ------------
	
	/*
	 *
	*/
	public MessageStreamBean() {
    	LOGGER.debug("Create: " + MessageStreamBean.class.getSimpleName());
    	this.message = new MessageDto();
	}
	
	/**
	 * Initialises the bean for the view
	 */
	@PostConstruct
	public void init() {
		LOGGER.debug("Initialising the MessageStreamBean");
		this.initAvailableCommunities();
		
//		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//	    int userId = Integer.parseInt(params.get("userId"));
//		setCurrentUserId(userId);
	    this.setCurrentUserId(1L);
	}

	// ---------- Public Methods ------------
	/**
	 * Gets all Messages the current user may see at the time including private,
	 * global and community messages
	 *
	 * @return
	 */
	public Map<Long, MessageDto> getAllMessages() {
		LOGGER.debug("Getting all Messages");
		return mapMessages(this.messageService.findUserRelated(this.getCurrentUserId()));
	}

	/**
	 * Gets the comments of a message to display in the view
	 *
	 * @param messageId
	 *            The ID of the message to get the comments for
	 * @return A list of the messages comments
	 */
	public List<CommentDto> getComments(Long messageId) {
		MessageDto message = messages.get(messageId);
		if (message != null) {
			return message.getChilds();
		}

		return new ArrayList<CommentDto>();
	}

	/**
	 * Creates a new Message via MessageService
	 *
	 * @param title
	 *            The Messages title
	 * @param content
	 *            The Messages content
	 */
	public void createMessage() {
		LOGGER.debug("Creating message");
    	
		if(currentCommunity != null) {
			this.message.setCommunityId(currentCommunity.getId());
		}
		// TODO: add author and community/recipient - check which of those applies
 
		final long generatedId = messageService.save(new MessageDto());
		LOGGER.debug("Created new message with ID {}", generatedId);

		this.message = messageService.find(generatedId);
	}

	/**
	 * Creates a new Comment (called by Server)
	 *
	 * @param messageId
	 *            The id of the message the comment belongs to
	 * @param text
	 *            the text for the new comment
	 */
	public void createComment(Long messageId, String text) {
		LOGGER.debug("createComment: " + messageId + " - " + text);
		// TODO: Implement
	}

	/**
	 * Likes a message (called by Server)
	 *
	 * @param id
	 *            The id of the message to like
	 */
	public void likeMessage(Long id) {
		LOGGER.debug("likeMessage: " + id);
		// TODO: Implement
	}

	/**
	 * Likes a comment (called by Server)
	 *
	 * @param id
	 *            The id of the comment to like
	 */
	public void likeComment(Long id) {
		LOGGER.debug("likeComment: " + id);
		// TODO: Implement
	}

	/**
	 * Changes the currentCommunity to the selected value (called by Server)
	 */
	public void onCommunityChange() {
		LOGGER.debug("MessageStreamBean Community Changed: " + currentCommunity);
		if (this.currentCommunity.getId() <= -3) { // All Messages
			this.messages = this.getAllMessages();
		} else if (this.currentCommunity.getId() == -2) { // Private Messages
			this.messages = this.getPrivateMessages();
		} else if (this.currentCommunity.getId() == -1) { // Global Messages
			this.messages = this.getGlobalMessages();
		} else if (this.currentCommunity.getId() == 0) { // All Messages
			this.messages = this.getAllMessages();
		} else if (this.currentCommunity.getId() > 0){ // Specific Community Message
			this.messages = this.getCommunityMessages(this.currentCommunity.getId());
		} else {
			this.messages = this.getAllMessages(); // Default -> here should be some ErrorHandling
		}
	}

	// ---------- Private Methods ------------

	/**
	 * Initialises the availableCommunities (called by Server)
	 */
	private void initAvailableCommunities() {
		LOGGER.debug("Initialising the Communities for the Selectbox");
		// TODO: Implement in CommunityService or MessageService with correct
		// return type.
		// must not only return Communities in DB but also following
		// Dummy-Communities
		// ID -3 => Name "Alle"
		// ID -2 => Name "Private"
		// ID -1 => Name "Globale"
		// this.availableCommunities = communityService.findAllUserRelated
		this.availableCommunities = new ArrayList<CommunityDtoStub>();
		
		CommunityDtoStub all = new CommunityDtoStub();
		all.setId(-3L);
		all.setName("ALLE");
		this.availableCommunities.add(all);
		
		CommunityDtoStub privateC = new CommunityDtoStub();
		privateC.setId(-2L);
		privateC.setName("Private");
		this.availableCommunities.add(privateC);
		
		CommunityDtoStub global = new CommunityDtoStub();
		global.setId(-1L);
		global.setName("Globale");
		this.availableCommunities.add(global);
		
		for (int i = 1; i < 4; i++) {
			CommunityDtoStub c = new CommunityDtoStub();
			c.setId((long) i);
			c.setName("Community_" + c.getId());
			this.availableCommunities.add(c);
		}
		
		this.setCurrentCommunity(availableCommunities.get(0));
		
		onCommunityChange();
	}

	/**
	 * Gets Messages only for the given community id
	 *
	 * @param id
	 *            The id of the community
	 * @return A map of Messages
	 */
	private Map<Long, MessageDto> getCommunityMessages(Long id) {
		return mapMessages(this.messageService.findByCommunityId(id));
	}

	/**
	 * Gets only global Messages
	 *
	 * @return A map of Messages
	 */
	private Map<Long, MessageDto> getGlobalMessages() {
		return mapMessages(this.messageService.findGlobalMesssages());
	}

	/**
	 * Gets only private Messages
	 *
	 * @return A map of Messages
	 */
	private Map<Long, MessageDto> getPrivateMessages() {
		return mapMessages(this.messageService.findUsersPrivateMessages(this.getCurrentUserId()));
	}
	
	/**
	 * Maps a List of Messages
	 * Key: Id of Message
	 * Value: Message
	 * 
	 * @param messageList
	 * 				List of messages which should be mapped
	 * 
	 * @return A map of Messages
	 */
	private Map<Long, MessageDto> mapMessages(List<MessageDto> messageList){
		Map<Long, MessageDto> mappedMessages = new HashMap<Long, MessageDto>();
		for (MessageDto message : messageList) {
			mappedMessages.put(message.getId(), message);
		}
		return mappedMessages;
	}
}
