package at.fhj.swd14.pse.like;

import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageConverter;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.message.MessageRepository;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class MessageLikeServiceImpl implements MessageLikeService {

    @EJB
    private MessageRepository messageRepository;
    @EJB
    private UserRepository userRepository;

    @Override
    public void save(MessageLikeDto messageLike) {
        MessageDto messageDTO = messageLike.getLikedMessage();
        long messageId = MessageConverter.convert(messageDTO).getId();
        Message message = messageRepository.find(messageId);
        UserDto userDTO = messageLike.getLiker();
        long id = userDTO.getId();
        List<User> users = message.getUsers();
        boolean isUserInList = false;
        int positionInList = 0;
        for (int i = 0; i < users.size(); i++)
        {
        	User user = users.get(i);
        	long userId = user.getId();
        	if (id == userId)
        	{
        		isUserInList = true; // user has already liked the message
        		positionInList = i;
        	}
        }
        if (isUserInList == true) // remove user from list
        {
        	users.remove(positionInList);
        }
        else // insert user in list
        {
        	User user = userRepository.find(id); // find user in database
        	users.add(user);
        }
        message.setUsers(users);
        messageRepository.save(message);
    }

    @Override
    public MessageLikeDto getMessageLike(long userId, long messageId) {
        MessageDto messageDTO = MessageConverter.convert(messageRepository.find(messageId));
        Message message = messageRepository.find(messageId);
        UserDto userDTO = null;
        List<User> users = message.getUsers();
        User userAlreadyLiked = null;
        for (int i = 0; i < users.size(); i++)
        {
        	User user = users.get(i);
        	long id = user.getId();
        	if (id == userId)
        	{
        		userAlreadyLiked = user;
        	}
        }
        if (userAlreadyLiked != null)
        {
        	userDTO = UserConverter.convert(userAlreadyLiked);
        }
        MessageLikeDto messageLike = new MessageLikeDto(userDTO, messageDTO);

        return messageLike;
    }

    @Override
    public List<MessageLikeDto> getMessageLikes(long messageId) {
        MessageDto messageDTO = MessageConverter.convert(messageRepository.find(messageId));
        Message message = messageRepository.find(messageId);
        List<User> users = message.getUsers();
        List<MessageLikeDto> messageLikes = new ArrayList<MessageLikeDto>();
        for (int i = 0; i < users.size(); i++) {
            UserDto userDTO = UserConverter.convert(users.get(i));
            MessageLikeDto messageLike = new MessageLikeDto(userDTO, messageDTO);
            messageLikes.add(messageLike);

        }

        return messageLikes;
    }

    @Override
    public int getLikeCountForMessage(long messageId) {
        Message message = messageRepository.find(messageId);
        List<User> users = message.getUsers();

        return users.size();
    }

    @Override
    public int getLikeCountForUserForMessages(long userId) {
        User user = userRepository.find(userId);
        List<Message> messages = user.getMessages();

        return messages.size();
    }

}
