package at.fhj.swd14.pse.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.converter.MessageConverter;
import at.fhj.swd14.pse.message.MessageDto;
import at.fhj.swd14.pse.message.MessageService;
import at.fhj.swd14.pse.repository.MessageRepository;

@Stateless
public class MessageServiceImpl implements MessageService {

	@EJB
    private MessageRepository messageRepository;
	
	@Override
	public long save(MessageDto message) {
		//TODO: call tag-handling function before saving the message
		//not necessary at this position, however it has to be done
		
		Message messageDo = MessageConverter.convert(message);
		messageRepository.save(messageDo);
		return messageDo.getId();
	}

	@Override
	public MessageDto find(long id) {
		return MessageConverter.convert(messageRepository.find(id));
	}

	@Override
	public List<MessageDto> findByAuthorId(Long authorUserId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("authorUserId", authorUserId);
		return executeNamedQuery("Message.findByAuthorId", parameter);
	}

	@Override
	public List<MessageDto> findByCommunityId(Long communityId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("communityId", communityId);
		return executeNamedQuery("Message.findByCommunityId", parameter);
	}

	@Override
	public List<MessageDto> findUserRelated(Long userId) {
		
		
		//TODO: query isn't implemented yet
		//Map<String, Object> parameter = new HashMap<>();
		//parameter.put("userId", userId);
		return executeNamedQuery("Message.findUserRelated");
	}

	@Override
	public List<MessageDto> findGlobalMesssages() {
		return executeNamedQuery("Message.findGlobalMessages");
	}

	@Override
	public List<MessageDto> findUsersPrivateMessages(Long userId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("userId", userId);
		return executeNamedQuery("Message.findUsersPrivateMessage", parameter);
	}

	private List<MessageDto> executeNamedQuery(String name, Map<String, Object> parameter){
		return new ArrayList<>(MessageConverter.convertToDtoList(messageRepository.executeNamedQuery(name, parameter)));
	}

	private List<MessageDto> executeNamedQuery(String name){
		return new ArrayList<>(MessageConverter.convertToDtoList(messageRepository.executeNamedQuery(name)));
	}
}
