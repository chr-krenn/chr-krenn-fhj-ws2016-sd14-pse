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
		Message messageDo = MessageConverter.convert(message);
		messageRepository.save(messageDo);
		return messageDo.getId();
	}

	@Override
	public MessageDto find(long id) {
		return MessageConverter.convert(messageRepository.find(id));
	}

	@Override
	public List<MessageDto> findByCreatorId(Long creatorUserId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("creatorUserId", creatorUserId);
		return executeNamedQuery("Message.findByCreatorId", parameter);
	}

	@Override
	public List<MessageDto> findByCommunityId(Long communityId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("communityId", communityId);
		return executeNamedQuery("Message.findByCommunityId", parameter);
	}

	@Override
	public List<MessageDto> findUserRelated(Long userId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("userId", userId);
		
		//TODO: query isn't implemented yet
		return executeNamedQuery("Message.findUserRelated", parameter);
	}
	
	private List<MessageDto> executeNamedQuery(String name, Map<String, Object> parameter){
		return new ArrayList<>(MessageConverter.convertToDtoList(messageRepository.executeNamedQuery(name, parameter)));
	}

}
