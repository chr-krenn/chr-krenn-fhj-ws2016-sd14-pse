package at.fhj.swd14.pse.like;

import at.fhj.swd14.pse.exception.VerificationException;
import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageConverter;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.message.MessageRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MessageLikeServiceImpl implements MessageLikeService {

    private static final Logger LOGGER = LogManager.getLogger(MessageLikeServiceImpl.class);
    private static final String TEXT = " could not be retrieved.";

    @EJB
    private MessageRepository messageRepository;
    @EJB
    private UserRepository userRepository;

    @Override
    public void save(MessageLikeDto messageLike) {
        LOGGER.trace("Method save in MessageLikeService invoked.");
        try {
            if (messageLike == null) {
                LOGGER.error("Can not process message like NULL");
                throw new VerificationException("Can not process NULL as message like");
            }
            MessageDto messageDTO = messageLike.getLikedMessage();
            long messageId = MessageConverter.convert(messageDTO).getId();
            Message message = messageRepository.find(messageId);
            UserDto userDTO = messageLike.getLiker();
            long id = userDTO.getId();
            if (message != null) {
            	List<User> users = message.getUsers();
            	LikeServiceHelper helper = new LikeServiceHelper(users, userRepository);
            	users = helper.processUser(id);
            	message.setUsers(users);
            	messageRepository.save(message);
            }
            else
            {
            	throw new VerificationException("Message for given id not found in database.");
            }
        } catch (VerificationException e) {
            LOGGER.error("Illegal input from frontend." + e.getMessage(), e);
            throw new MessageLikeServiceException("Illegal input from frontend." + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An error occurred while saving message like.", e);
            throw new MessageLikeServiceException("Message like could not be saved.");
        }
    }

    @Override
    public MessageLikeDto getMessageLike(long userId, long messageId) {
        LOGGER.trace("Method getMessageLike invoked.");
        try {
            MessageDto messageDTO = MessageConverter.convert(messageRepository.find(messageId));
            Message message = messageRepository.find(messageId);
            UserDto userDTO = null;
            List<User> users = message.getUsers();
            User userAlreadyLiked = null;
            for (User user : users) {
                long id = user.getId();
                if (id == userId) {
                    userAlreadyLiked = user;
                }
            }
            if (userAlreadyLiked != null) {
                userDTO = UserConverter.convert(userAlreadyLiked);
            }

            return new MessageLikeDto(userDTO, messageDTO);
        } catch (Exception e) {
            LOGGER.error("An error occured while searching for message like: " + userId + " " + messageId, e);
            throw new MessageLikeServiceException("Message like for: " + userId + " " + messageId + TEXT);
        }
    }

    @Override
    public List<MessageLikeDto> getMessageLikes(long messageId) {
        LOGGER.trace("Method getMessageLikes invoked.");
        try {
            MessageDto messageDTO = MessageConverter.convert(messageRepository.find(messageId));
            Message message = messageRepository.find(messageId);
            List<User> users = message.getUsers();
            List<MessageLikeDto> messageLikes = new ArrayList<>();
            for (User user : users) {
                UserDto userDTO = UserConverter.convert(user);
                MessageLikeDto messageLike = new MessageLikeDto(userDTO, messageDTO);
                messageLikes.add(messageLike);

            }

            return messageLikes;
        } catch (Exception e) {
            LOGGER.error("An error occured while searching for message likes: " + messageId, e);
            throw new MessageLikeServiceException("Message likes for: " + messageId + TEXT);
        }
    }

    @Override
    public int getLikeCountForMessage(long messageId) {
        LOGGER.trace("Method getLikeCountForMessage invoked.");
        try {
            Message message = messageRepository.find(messageId);
            List<User> users = message.getUsers();

            return users.size();
        } catch (Exception e) {
            LOGGER.error("An error occured while searching for like count for message: " + messageId, e);
            throw new MessageLikeServiceException("Message like count for message: " + messageId + TEXT);
        }
    }

    @Override
    public int getLikeCountForUserForMessages(long userId) {
        LOGGER.trace("Method getLikeCountForUserForMessages invoked.");
        try {
            User user = userRepository.find(userId);
            List<Message> messages = user.getMessages();

            return messages.size();
        } catch (Exception e) {
            LOGGER.error("An error occured while searching for like count for messages for user: " + userId, e);
            throw new MessageLikeServiceException("Like count for user: " + userId + " for messages" + TEXT);
        }
    }

}
