package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.community.CommunityDto;
import at.fhj.swd14.pse.community.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class MessageStreamBean implements Serializable {
	// ---------- Member including Get/Set ------------
	private static final long serialVersionUID = 1L;

	/**
	 * The LOGGER to use
	 */
	private static final Logger LOGGER = LogManager.getLogger(MessageStreamBean.class);

	@EJB(name = "ejb/MessageService")
	private transient MessageService messageService;

	@EJB(name = "ejb/CommunityService")
	private transient CommunityService communityService;

	/**
	 * Maps to a GET Parameter of the xhtml file
	 */
	private Long parameterCommunityId;

	public Long getParameterCommunityId() {
		return parameterCommunityId;
	}

	public void setParameterCommunityId(Long parameterCommunityId) {
		this.parameterCommunityId = parameterCommunityId;
	}

	/**
	 * The CommunityDto to use
	 */
	private CommunityDto currentCommunity;

	/**
	 * Gets the CommunityDto
	 *
	 * @return The current communities DTO
	 */
	public CommunityDto getCurrentCommunity() {
		return currentCommunity;
	}

	/**
	 * Sets the current communities DTO
	 *
	 * @param currentCommunity
	 *            The community to set as current community
	 */
	public void setCurrentCommunity(CommunityDto currentCommunity) {
		this.currentCommunity = currentCommunity;
	}

	/**
	 * List of available communities
	 */
	private List<CommunityDto> availableCommunities;

	/**
	 * Gets the available communities
	 *
	 * @return The available communities
	 */
	public List<CommunityDto> getAvailableCommunities() {
		return availableCommunities;
	}

	/**
	 * Sets the available communities
	 *
	 * @param availableCommunities
	 *            The available communities to set
	 */
	public void setAvailableCommunities(List<CommunityDto> availableCommunities) {
		this.availableCommunities = availableCommunities;
	}

	/**
	 * The Messages to display in the view
	 */
	private Map<Long, MessageDto> messageMap;

	/**
	 * Gets the messages to display in the view
	 *
	 * @return
	 */
	public Map<Long, MessageDto> getMessageMap() {
		return messageMap;
	}

	/**
	 * Sets the messages to display in the view
	 *
	 * @param messageMap
	 *            the messages to display in the view
	 */
	public void setMessageMap(Map<Long, MessageDto> messageMap) {
		this.messageMap = messageMap;
	}

	/**
	 * The Messages to display in the view
	 */
	private List<MessageDto> messages;

	public List<MessageDto> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
		Collections.sort(this.messages);
		this.messageMap = mapMessages(this.messages);
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
		return currentUserId;
	}

	/**
	 * Sets the Id of the currently logged-in User
	 *
	 * @param id
	 *            the Id of the currently logged-in User
	 */
	public void setCurrentUserId(long id) {
		currentUserId = id;
	}
	// ---------- Constructor ------------

	/**
	 * Constructor
	 */
	public MessageStreamBean() {
		LOGGER.debug("Create: " + MessageStreamBean.class.getSimpleName());
	}

	/**
	 * Initialises the bean for the view
	 */
	@PostConstruct
	public void init() {
		LOGGER.debug("Initialising the MessageStreamBean");
		FacesContext context = FacesContext.getCurrentInstance();
		setCurrentUserId(((at.fhj.swd14.pse.security.DatabasePrincipal) context.getExternalContext().getUserPrincipal()).getUserId());
		
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String communityId = requestMap.get("parameterCommunityId");
		if(communityId != null) {
			this.setParameterCommunityId(Long.parseLong(communityId));
		}
		
		initAvailableCommunities();
	}

	// ---------- Public Methods ------------
	/**
	 * Gets all Messages the current user may see at the time including private,
	 * global and community messages
	 *
	 * @return
	 */
	public List<MessageDto> getAllMessages() {
		LOGGER.debug("Getting all Messages");
		return messageService.findUserRelated(getCurrentUserId());
	}

	/**
	 * Gets the comments of a message to display in the view
	 *
	 * @param messageId
	 *            The ID of the message to get the comments for
	 * @return A list of the messages comments
	 */
	public List<CommentDto> getComments(Long messageId) {
		MessageDto message = messageMap.get(messageId);
		if (message != null) {
			return message.getChilds();
		}

		return new ArrayList<>();
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
		if (currentCommunity.getId() <= -3) { // All Messages
			setMessages(getAllMessages());
		} else if (currentCommunity.getId() == -2) { // Private Messages
			setMessages(getPrivateMessages());
		} else if (currentCommunity.getId() == -1) { // Global Messages
			setMessages(getGlobalMessages());
		} else if (currentCommunity.getId() == 0) { // All Messages
			setMessages(getAllMessages());
		} else if (currentCommunity.getId() > 0) { // Community Messages
			setMessages(getCommunityMessages(currentCommunity.getId()));
		} else {
			setMessages(new ArrayList<>()); // Default
		}
	}

	// ---------- Private Methods ------------

	/**
	 * Initialises the availableCommunities (called by Server)
	 */
	private void initAvailableCommunities() {
		LOGGER.debug("Initialising the Communities for the Selectbox");
		if (getParameterCommunityId() != null) {
			availableCommunities = new ArrayList<>();
			availableCommunities.add(communityService.find(getParameterCommunityId()));
		} else {
			availableCommunities = new ArrayList<>();
			CommunityDto c = new CommunityDto(-3L);
			c.setName("Alle");
			CommunityDto c2 = new CommunityDto(-2L);
			c2.setName("Private");
			CommunityDto c3 = new CommunityDto(-1L);
			c3.setName("Globale");
			availableCommunities.add(c);
			availableCommunities.add(c2);
			availableCommunities.add(c3);
			List<CommunityDto> dbCommunities = communityService.findUserRelated(getCurrentUserId());
			Collections.sort(dbCommunities);
			availableCommunities.addAll(dbCommunities);
		}
		
		setCurrentCommunity(availableCommunities.get(0));
		
		onCommunityChange();
	}

	/**
	 * Gets Messages only for the given community id
	 *
	 * @param id
	 *            The id of the community
	 * @return A map of Messages
	 */
	private List<MessageDto> getCommunityMessages(Long id) {
		return messageService.findByCommunityId(id);
	}

	/**
	 * Gets only global Messages
	 *
	 * @return A map of Messages
	 */
	private List<MessageDto> getGlobalMessages() {
		return messageService.findGlobalMesssages();
	}

	/**
	 * Gets only private Messages
	 *
	 * @return A map of Messages
	 */
	private List<MessageDto> getPrivateMessages() {
		return messageService.findUsersPrivateMessages(getCurrentUserId());
	}

	/**
	 * Maps a List of Messages Key: Id of Message Value: Message
	 * 
	 * @param messageList
	 *            List of messages which should be mapped
	 * 
	 * @return A map of Messages
	 */
	private Map<Long, MessageDto> mapMessages(List<MessageDto> messageList) {
		Map<Long, MessageDto> mappedMessages = new HashMap<>();
		for (MessageDto message : messageList) {
			mappedMessages.put(message.getId(), message);
		}
		return mappedMessages;
	}
}
