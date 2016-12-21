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
import java.util.ArrayList;
import java.util.List;

public class MessageLikeServiceImpl implements MessageLikeService {

    @EJB
    private MessageRepository messageRepository;
    @EJB
    private UserRepository userRepository;

    @Override
    public void save(MessageLikeDto messageLike) {
        MessageDto messageDTO = messageLike.getLikedMessage();
        UserDto userDTO = messageLike.getLiker();
        messageRepository.save(MessageConverter.convert(messageDTO));
        userRepository.save(UserConverter.convert(userDTO));

    }

    @Override
    public MessageLikeDto getMessageLike(long userId, long messageId) {
        MessageDto messageDTO = MessageConverter.convert(messageRepository.find(messageId));
        UserDto userDTO = UserConverter.convert(userRepository.find(userId));
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
