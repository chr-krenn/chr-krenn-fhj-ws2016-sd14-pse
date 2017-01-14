package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.community.CommunityDto;
import at.fhj.swd14.pse.community.CommunityService;
import at.fhj.swd14.pse.like.CommentLikeDto;
import at.fhj.swd14.pse.like.CommentLikeService;
import at.fhj.swd14.pse.like.MessageLikeDto;
import at.fhj.swd14.pse.like.MessageLikeService;
import at.fhj.swd14.pse.user.UserDto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class MessageStreamBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(MessageStreamBean.class);
    private static final String ERROR_LOG_TITLE = "Error occurred";

    @EJB(name = "ejb/MessageService")
    private transient MessageService messageService;

    @EJB(name = "ejb/CommunityService")
    private transient CommunityService communityService;

    @EJB(name = "ejb/CommentLikeService")
    private transient CommentLikeService commentLikeService;

    @EJB(name = "ejb/MessageLikeService")
    private transient MessageLikeService messageLikeService;

    /**
     * Maps to a GET Parameter of the xhtml file
     */
    private Long parameterCommunityId;

    /**
     * The CommunityDto to use
     */
    private CommunityDto currentCommunity;

    /**
     * List of available communities
     */
    private List<CommunityDto> availableCommunities;

    /**
     * The Messages to display in the view
     */
    private Map<Long, MessageDto> messageMap = new HashMap<>();

    /**
     * The Messages to display in the view
     */
    private List<MessageDto> messages;

    /**
     * The Id of the currently logged-in User
     */
    private long currentUserId;

    public Long getParameterCommunityId() {
        LOGGER.trace("MessageStreamBean::getParameterCommunityId()");
        return parameterCommunityId;
    }

    public void setParameterCommunityId(Long parameterCommunityId) {
        LOGGER.trace("MessageStreamBean::setParameterCommunityId(" + parameterCommunityId + ")");
        this.parameterCommunityId = parameterCommunityId;
    }

    /**
     * Gets the CommunityDto
     *
     * @return The current communities DTO
     */
    public CommunityDto getCurrentCommunity() {
        LOGGER.trace("MessageStreamBean::getCurrentCommunity()");
        return currentCommunity;
    }

    /**
     * Sets the current communities DTO
     *
     * @param currentCommunity The community to set as current community
     */
    public void setCurrentCommunity(CommunityDto currentCommunity) {
        LOGGER.trace("MessageStreamBean::setCurrentCommunity(" + currentCommunity.toString() + ")");
        this.currentCommunity = currentCommunity;
    }

    /**
     * Gets the available communities
     *
     * @return The available communities
     */
    public List<CommunityDto> getAvailableCommunities() {
        LOGGER.trace("MessageStreamBean::getAvailableCommunities()");
        return availableCommunities;
    }

    /**
     * Sets the available communities
     *
     * @param availableCommunities The available communities to set
     */
    public void setAvailableCommunities(List<CommunityDto> availableCommunities) {
        LOGGER.trace("MessageStreamBean::setAvailableCommunities(" + availableCommunities.toString() + ")");
        this.availableCommunities = availableCommunities;
    }

    /**
     * Gets the messages to display in the view
     *
     * @return
     */
    public Map<Long, MessageDto> getMessageMap() {
        LOGGER.trace("MessageStreamBean::getMessageMap()");
        return messageMap;
    }

    /**
     * Sets the messages to display in the view
     *
     * @param messageMap the messages to display in the view
     */
    public void setMessageMap(Map<Long, MessageDto> messageMap) {
        LOGGER.trace("MessageStreamBean::setMessageMap(" + messageMap.toString() + ")");
        this.messageMap = messageMap;
    }

    public List<MessageDto> getMessages() {
        LOGGER.trace("MessageStreamBean::getMessages()");
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        LOGGER.trace("MessageStreamBean::setMessages(" + messages.toString() + ")");
        this.messages = messages;
        Collections.sort(this.messages);
        this.messageMap = mapMessages(this.messages);
    }

    /**
     * Gets the Id of the currently logged-in User
     *
     * @return
     */
    public long getCurrentUserId() {
        LOGGER.trace("MessageStreamBean::getCurrentUserId()");
        return currentUserId;
    }

    /**
     * Sets the Id of the currently logged-in User
     *
     * @param id the Id of the currently logged-in User
     */
    public void setCurrentUserId(long id) {
        LOGGER.trace("MessageStreamBean::setCurrentUserId(" + id + ")");
        currentUserId = id;
    }

    /**
     * Constructor
     */
    public MessageStreamBean() {
        LOGGER.trace("MessageStreamBean::MessageStreamBean()");
    }

    /**
     * Initialises the bean for the view
     */
    @PostConstruct
    public void init() {
        LOGGER.trace("MessageStreamBean::init()");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            setCurrentUserId(((at.fhj.swd14.pse.security.DatabasePrincipal) context.getExternalContext().getUserPrincipal()).getUserId());

            Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
            String communityId = requestMap.get("parameterCommunityId");
            if (communityId != null) {
                this.setParameterCommunityId(Long.parseLong(communityId));
            }

            initAvailableCommunities();
        } catch (Exception ex) {
            growl(ERROR_LOG_TITLE, ex);
        }
    }

    /**
     * Gets all Messages the current user may see at the time including private,
     * global and community messages
     *
     * @return
     */
    public List<MessageDto> getAllMessages() {
        LOGGER.trace("MessageStreamBean::getAllMessages()");
        try {
            return messageService.findUserRelated(getCurrentUserId());
        } catch (Exception ex) {
            growl(ERROR_LOG_TITLE, ex);
            return new ArrayList<>();
        }
    }

    /**
     * Gets the comments of a message to display in the view
     *
     * @param messageId The ID of the message to get the comments for
     * @return A list of the messages comments
     */
    public List<CommentDto> getComments(Long messageId) {
        LOGGER.trace("MessageStreamBean::createComment(" + messageId + ")");
        try {
            MessageDto message = messageMap.get(messageId);
            if (message != null) {
                return message.getChilds();
            }

            return new ArrayList<>();
        } catch (Exception ex) {
            growl(ERROR_LOG_TITLE, ex);
            return new ArrayList<>();
        }
    }

    /**
     * Creates a new Comment (called by Server)
     *
     * @param messageId The id of the message the comment belongs to
     * @param text      the text for the new comment
     */
    public void createComment(Long messageId, String text) {
        LOGGER.trace("MessageStreamBean::createComment(" + messageId + ", " + text + ")");
        // TODO: Implement
    }

    /**
     * Likes a message (called by Server)
     *
     * @param id The id of the message to like
     */
    public void likeMessage(Long id) {
        LOGGER.trace("MessageStreamBean::likeMessage(" + id + ")");
        try {
            MessageLikeDto messageLike = this.messageLikeService.getMessageLike(this.currentUserId, id);
            UserDto userDTO = messageLike.getLiker();
            if (userDTO == null) {
                userDTO = new UserDto(this.currentUserId);
            }
            messageLike.setLiker(userDTO);
            this.messageLikeService.save(messageLike);
        } catch (Exception ex) {
            growl(ERROR_LOG_TITLE, ex);
        }
    }

    /**
     * Likes a comment (called by Server)
     *
     * @param id The id of the comment to like
     */
    public void likeComment(Long id) {
        LOGGER.trace("MessageStreamBean::likeComment(" + id + ")");
        try {
            CommentLikeDto commentLike = this.commentLikeService.getCommentLike(this.currentUserId, id);
            UserDto userDTO = commentLike.getLiker();
            if (userDTO == null) {
                userDTO = new UserDto(this.currentUserId);
            }
            commentLike.setLiker(userDTO);
            this.commentLikeService.save(commentLike);
        } catch (Exception ex) {
            growl(ERROR_LOG_TITLE, ex);
        }
    }

    public int getLikeCountForMessage(Long messageId) {
        LOGGER.trace("MessageStreamBean::getLikeCountForMessage(" + messageId + ")");
        try {
            return this.messageLikeService.getLikeCountForMessage(messageId);
        } catch (Exception ex) {
            growl(ERROR_LOG_TITLE, ex);
            return 0;
        }

    }

    public int getLikeCountForComment(Long commentId) {
        LOGGER.trace("MessageStreamBean::getLikeCountForComment(" + commentId + ")");
        try {
            return this.commentLikeService.getLikeCountForComment(commentId);
        } catch (Exception ex) {
            growl(ERROR_LOG_TITLE, ex);
            return 0;
        }
    }

    /**
     * Changes the currentCommunity to the selected value (called by Server)
     */
    public void onCommunityChange() {
        LOGGER.trace("MessageStreamBean::onCommunityChange()");
        try {
            if (currentCommunity.getId() <= -3 || currentCommunity.getId() == 0) { // All
                // Messages
                setMessages(getAllMessages());
            } else if (currentCommunity.getId() == -2) { // Private Messages
                setMessages(getPrivateMessages());
            } else if (currentCommunity.getId() == -1) { // Global Messages
                setMessages(getGlobalMessages());
            } else if (currentCommunity.getId() > 0) { // Community Messages
                setMessages(getCommunityMessages(currentCommunity.getId()));
            } else {
                setMessages(new ArrayList<>()); // Default
            }
        } catch (Exception ex) {
            growl(ERROR_LOG_TITLE, ex);
        }

    }

    public void growl(String summary, Exception ex) {
        growl(summary, ex.getMessage());
    }

    public void growl(String summary, String message) {
        LOGGER.error("MessageStreamBean::growl(" + summary + ", " + message + ")");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(summary, message));
    }

    /**
     * Initialises the availableCommunities (called by Server)
     */
    private void initAvailableCommunities() {
        LOGGER.trace("MessageStreamBean::initAvailableCommunities()");
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
     * @param id The id of the community
     * @return A map of Messages
     */
    private List<MessageDto> getCommunityMessages(Long id) {
        LOGGER.trace("MessageStreamBean::getCommunityMessages(" + id + ")");
        return messageService.findByCommunityId(id);
    }

    /**
     * Gets only global Messages
     *
     * @return A map of Messages
     */
    private List<MessageDto> getGlobalMessages() {
        LOGGER.trace("MessageStreamBean::findGlobalMesssages()");
        return messageService.findGlobalMesssages();
    }

    /**
     * Gets only private Messages
     *
     * @return A map of Messages
     */
    private List<MessageDto> getPrivateMessages() {
        LOGGER.trace("MessageStreamBean::getPrivateMessages()");
        return messageService.findUsersPrivateMessages(getCurrentUserId());
    }

    /**
     * Maps a List of Messages Key: Id of Message Value: Message
     *
     * @param messageList List of messages which should be mapped
     * @return A map of Messages
     */
    private Map<Long, MessageDto> mapMessages(List<MessageDto> messageList) {
        LOGGER.trace("MessageStreamBean::mapMessages(" + messageList.toString() + ")");
        Map<Long, MessageDto> mappedMessages = new HashMap<>();
        for (MessageDto message : messageList) {
            mappedMessages.put(message.getId(), message);
        }
        return mappedMessages;
    }
}
