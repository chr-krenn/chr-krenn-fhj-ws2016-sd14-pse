package at.fhj.swd14.pse.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import at.fhj.swd14.pse.exception.VerificationException;

@Stateless
public class MessageServiceImpl implements MessageService {

	private static final Logger LOGGER = LogManager.getLogger(MessageServiceImpl.class);
	private static final String ERR_INVALID_INPUT = "Invalid input was supplied by frontend: ";

	@EJB
	private MessageRepository messageRepository;

	@Override
	public long save(MessageDto message) {
		// TODO: call tag-handling function before saving the message
		// not necessary at this position, however it has to be done

		try {
			if (message == null) {
				LOGGER.error("Can not save NULL as message");
				throw new VerificationException("Can not save NULL as message");
			}

			final Message messageDo = MessageConverter.convert(message);
			LOGGER.trace("MessageDTO-Input converted to message-entity");
			messageRepository.save(messageDo);
			LOGGER.trace("Message-entity saved in DB");
			return messageDo.getId();
		} catch (VerificationException e) {
			LOGGER.error(ERR_INVALID_INPUT + e.getMessage(), e);
			throw new MessageServiceException(ERR_INVALID_INPUT + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An error occurred while saving message", e);
			throw new MessageServiceException("Message could not be saved");
		}
	}

	@Override
	public MessageDto find(long id) {
		try {

			LOGGER.trace("Finding message by id '" + id + "'");
			return MessageConverter.convert(messageRepository.find(id));
		} catch (Exception e) {
			LOGGER.error("An error occurred while finding a message by id '" + id + "'", e);
			throw new MessageServiceException("Message with id '" + id + "' could not be found");
		}

	}

	@Override
	public List<MessageDto> findByAuthorId(Long authorUserId) {

		try {
			if (authorUserId == null) {
				LOGGER.error("Can not find messages by author-id NULL");
				throw new VerificationException("Can not find messages by author-id NULL");
			}

			final Map<String, Object> parameter = new HashMap<>();
			parameter.put("authorUserId", authorUserId);

			LOGGER.trace("Finding messages by author-id '" + authorUserId + "'");
			return executeNamedQuery("Message.findByAuthorId", parameter);

		} catch (VerificationException e) {
			LOGGER.error(ERR_INVALID_INPUT + e.getMessage(), e);
			throw new MessageServiceException(ERR_INVALID_INPUT + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An error occurred while finding messages by author-id '" + authorUserId + "'", e);
			throw new MessageServiceException("Message could not be found by author");
		}

	}

	@Override
	public List<MessageDto> findByCommunityId(Long communityId) {
		
		try {
			if (communityId == null) {
				LOGGER.error("Can not find messages by community-id NULL");
				throw new VerificationException("Can not find messages by community-id NULL");
			}

			final Map<String, Object> parameter = new HashMap<>();
			parameter.put("communityId", communityId);
			LOGGER.trace("Finding messages by community-id '" + communityId + "'");
			return executeNamedQuery("Message.findByCommunityId", parameter);

		} catch (VerificationException e) {
			LOGGER.error(ERR_INVALID_INPUT + e.getMessage(), e);
			throw new MessageServiceException(ERR_INVALID_INPUT + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An error occurred while finding messages by community-id '" + communityId + "'", e);
			throw new MessageServiceException("Message could not be found by community");
		}
	}

	@Override
	public List<MessageDto> findUserRelated(Long userId) {
		
		
		try {
			if (userId == null) {
				LOGGER.error("Can not find user related messages by user-id NULL");
				throw new VerificationException("Can not find user related messages by user-id NULL");
			}

			Map<String, Object> parameter = new HashMap<>();
			parameter.put("userId", userId);
			LOGGER.trace("Finding user related messages by user-id '" + userId + "'");
			return executeNamedQuery("Message.findUserRelated", parameter);
			

		} catch (VerificationException e) {
			LOGGER.error(ERR_INVALID_INPUT + e.getMessage(), e);
			throw new MessageServiceException(ERR_INVALID_INPUT + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An error occurred while finding user related messages by user-id '" + userId + "'", e);
			throw new MessageServiceException("User related message could not be found by user");
		}
	}

	@Override
	public List<MessageDto> findGlobalMesssages() {
		
		try {
			LOGGER.trace("Finding global messages");
			return executeNamedQuery("Message.findGlobalMessages");
			
		} catch (Exception e) {
			LOGGER.error("An error occurred while finding global messages", e);
			throw new MessageServiceException("Global messages could not be found");
		}
	}

	@Override
	public List<MessageDto> findUsersPrivateMessages(Long userId) {
		
		try {
			if (userId == null) {
				LOGGER.error("Can not find private messages by user-id NULL");
				throw new VerificationException("Can not find private messages by user-id NULL");
			}

			Map<String, Object> parameter = new HashMap<>();
			parameter.put("userId", userId);
			LOGGER.trace("Finding private messages by user-id '" + userId + "'");
			return executeNamedQuery("Message.findUsersPrivateMessage", parameter);

		} catch (VerificationException e) {
			LOGGER.error(ERR_INVALID_INPUT + e.getMessage(), e);
			throw new MessageServiceException(ERR_INVALID_INPUT + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An error occurred while finding private messages by user-id '" + userId + "'", e);
			throw new MessageServiceException("Private message could not be found by user");
		}
	}

	private List<MessageDto> executeNamedQuery(String name, Map<String, Object> parameter) {
		return new ArrayList<>(MessageConverter.convertToDtoList(messageRepository.executeNamedQuery(name, parameter)));
	}

	private List<MessageDto> executeNamedQuery(String name) {
		return new ArrayList<>(MessageConverter.convertToDtoList(messageRepository.executeNamedQuery(name)));
	}
}
