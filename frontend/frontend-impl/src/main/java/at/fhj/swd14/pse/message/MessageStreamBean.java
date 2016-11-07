package at.fhj.swd14.pse.message;

//TODO: Change CommunityDtoStub to CommunityDto in this file
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
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
		this.messages = getAllMessages();
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
		// TODO: Implement method in MessageService with correct return type
		// return this.messageService.findUserRelated(#);
		Map<Long, MessageDto> allMessages = new HashMap<Long, MessageDto>();

		for (int i = 1; i < 100; i++) {
			UserDto author = new UserDto();
			author.setId((long) i);
			author.setMail("user_" + author.getId() + "@userdto.com");
			author.setPassword("pw_" + author.getId());
			author.setSalt("salt_" + author.getId());

			MessageDto message = new MessageDto();
			message.setId((long) i);
			message.setAuthor(author);
			message.setCommunityId(1L);
			message.setContent("Content_" + message.getId());
			message.setCreationDate(new Date());
			if (currentCommunity != null) {
				LOGGER.debug("GetAllMessages:: Community Not Null");
				message.setTitle(currentCommunity.getId() + "Title_" + message.getId());
			} else {
				message.setTitle("Title_" + message.getId());
			}

			CommentDto comment = new CommentDto();
			comment.setAuthor(author);
			comment.setId((long) i);
			comment.setText("Comment_" + comment.getId());
			message.addChild(comment);
			allMessages.put(message.getId(), message);
		}
		return allMessages;
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
    		LOGGER.info("Creating message");
    	
		if(currentCommunity != null) {
			this.message.setCommunityId(currentCommunity.getId());
		}
		// TODO: add author and community/recipient - check which of those applies

		this.message.setCreationDate(new Date());    
		final long generatedId = messageService.save(new MessageDto());
		LOGGER.info("Created new message with ID {}", generatedId);

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
		} else { // Specific Community Message
			this.messages = this.getCommunityMessages(this.currentCommunity.getId());
		}
		messages = getAllMessages();
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
		for (int i = 0; i < 4; i++) {
			CommunityDtoStub c = new CommunityDtoStub();
			c.setId((long) i);
			c.setName("Community_" + c.getId());
			this.availableCommunities.add(c);
		}
	}

	/**
	 * Gets Messages only for the given community id
	 *
	 * @param id
	 *            The id of the community
	 * @return A map of Messages
	 */
	private Map<Long, MessageDto> getCommunityMessages(Long id) {
		// TODO: Implement method in MessageService with correct return type
		// and remove "return this.getAllMessages();"!
		// return return this.messageService.findByCommunityId(id);
		return this.getAllMessages();
	}

	/**
	 * Gets only global Messages
	 *
	 * @return A map of Messages
	 */
	private Map<Long, MessageDto> getGlobalMessages() {
		// TODO: Implement method in MessageService with correct return type
		// and remove "return
		// this.getAllMessages();"!
		// return this.messageService.findGlobalMessages();
		return this.getAllMessages();
	}

	/**
	 * Gets only private Messages
	 *
	 * @return A map of Messages
	 */
	private Map<Long, MessageDto> getPrivateMessages() {
		// TODO: Implement method in MessageService with correct return type
		// and remove "return
		// this.getAllMessages();"!
		// return this.messageService.findUsersPrivateMessages();
		return this.getAllMessages();
	}
}
