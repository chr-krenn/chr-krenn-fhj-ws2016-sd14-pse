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

		return findByUserId("Message.findByAuthorId", "author", authorUserId);
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

		return findByUserId("Message.findUserRelated", "user-related", userId);
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

		return findByUserId("Message.findUsersPrivateMessage", "private", userId);
	}

	private List<MessageDto> findByUserId(String namedQuery, String messageType, Long userId) {
		try {
			if (userId == null) {
				LOGGER.error("Can not find " + messageType + " messages by user-id NULL");
				throw new VerificationException("Can not find " + messageType + " messages by user-id NULL");
			}

			Map<String, Object> parameter = new HashMap<>();
			parameter.put("userId", userId);
			LOGGER.trace("Finding " + messageType + " messages by user-id '" + userId + "'");
			return executeNamedQuery(namedQuery, parameter);

		} catch (VerificationException e) {
			LOGGER.error(ERR_INVALID_INPUT + e.getMessage(), e);
			throw new MessageServiceException(ERR_INVALID_INPUT + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("An error occurred while finding "+ messageType + " messages by user-id '" + userId + "'", e);
			throw new MessageServiceException(messageType + " messages could not be found by user");
		}
	}
	
	private List<MessageDto> executeNamedQuery(String name, Map<String, Object> parameter) {
		return new ArrayList<>(MessageConverter.convertToDtoList(messageRepository.executeNamedQuery(name, parameter)));
	}

	private List<MessageDto> executeNamedQuery(String name) {
		return new ArrayList<>(MessageConverter.convertToDtoList(messageRepository.executeNamedQuery(name)));
	}

	
}
