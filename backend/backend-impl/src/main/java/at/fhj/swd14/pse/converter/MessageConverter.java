package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.message.Message;
import at.fhj.swd14.pse.message.MessageDto;

public class MessageConverter {

	
	public MessageConverter(){
		
	}
	
	public static MessageDto convert(Message message) {
        if (message == null) {
            return null;
        }
        
        MessageDto dto = new MessageDto(message.getId());
        dto.setParent(MessageConverter.convert(message.getParent()));
        dto.setCreator(UserConverter.convert(message.getCreator()));
        dto.setCommunityId(message.getCommunityId());
        dto.setTitle(message.getTitle());
        dto.setContent(message.getContent());
        dto.setCreationDate(message.getCreationDate());
        return dto;
    }

    public static Message convert(MessageDto dto) {
        if (dto == null) {
            return null;
        }
        Message message = new Message(dto.getId());
        message.setParent(MessageConverter.convert(dto.getParent()));
        message.setCreator(UserConverter.convert(dto.getCreator()));
        message.setCommunityId(dto.getCommunityId());
        message.setTitle(dto.getTitle());
        message.setContent(dto.getContent());
        message.setCreationDate(dto.getCreationDate());
        
        return message;
    }

    public static Collection<MessageDto> convertToDtoList(Collection<Message> messages) {
        if (messages == null) {
            return null;
        }
        return messages.stream().map(MessageConverter::convert).collect(Collectors.toList());
    }

    public static Collection<Message> convertToDoList(Collection<MessageDto> messages) {
        if (messages == null) {
            return null;
        }
        return messages.stream().map(MessageConverter::convert).collect(Collectors.toList());
    }
}
