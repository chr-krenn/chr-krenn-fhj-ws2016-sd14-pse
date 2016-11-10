package at.fhj.swd14.pse.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.comment.CommentDto;
import at.fhj.swd14.pse.converter.MessageConverter;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.message.MessageService;
import at.fhj.swd14.pse.repository.MessageRepository;
import at.fhj.swd14.pse.user.UserDto;

@Stateless
public class MessageServiceStub implements MessageService {

	@EJB
    private MessageRepository messageRepository;
	
	@Override
	public long save(MessageDto message) {
		return 666L;
	}

	@Override
	public MessageDto find(long id) {
		UserDto author = new UserDto();
		author.setId(id);
		author.setMail("user_" + author.getId() + "@userdto.com");
		author.setPassword("pw_" + author.getId());
		author.setSalt("salt_" + author.getId());
		
		MessageDto message = new MessageDto();
		message.setId(id);
		message.setAuthor(author);
		message.setCommunityId(1L);
		message.setContent("FindById_" + id + "_Content_" + message.getId());
		message.setTitle("Title_" + message.getId());
		return new MessageDto(id);
	}

	@Override
	public List<MessageDto> findByAuthorId(Long authorUserId) {
		return getTestMessages("ByAuthor", authorUserId);
	}

	@Override
	public List<MessageDto> findByCommunityId(Long communityId) {
		return getTestMessages("ByCommunity", communityId);
	}

	@Override
	public List<MessageDto> findUserRelated(Long userId) {
		return getTestMessages("UserRelated", userId);
	}

	@Override
	public List<MessageDto> findGlobalMesssages() {
		return getTestMessages("Global", -1L);
	}

	@Override
	public List<MessageDto> findUsersPrivateMessages(Long userId) {
		return getTestMessages("Private", userId);
	}
	
	private List<MessageDto> getTestMessages(String prefix, long id){
		List<MessageDto> allMessages = new ArrayList<MessageDto>();
		
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
			message.setContent(prefix + "_" + id + "_Content_" + message.getId());
			message.setTitle("Title_" + message.getId());

			CommentDto comment = new CommentDto();
			comment.setAuthor(author);
			comment.setId((long) i);
			comment.setText("Comment_" + comment.getId());
			message.addChild(comment);
			allMessages.add(message);
		}
		return allMessages;
	}
}
