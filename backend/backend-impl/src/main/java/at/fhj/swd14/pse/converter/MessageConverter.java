package at.fhj.swd14.pse.converter;

import java.util.ArrayList;
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
        dto.setAuthor(UserConverter.convert(message.getAuthor()));
        dto.setRecipient(UserConverter.convert(message.getRecipient()));
        dto.setCommunity(CommunityConverter.convert(message.getCommunity()));
        dto.setTitle(message.getTitle());
        dto.setContent(message.getContent());
        dto.setCreated(message.getCreated());
        dto.setModified(message.getModified());
        dto.setChilds(new ArrayList<>(CommentConverter.convertToDtoList(message.getChilds())));
        return dto;
    }

    public static Message convert(MessageDto dto) {
        if (dto == null) {
            return null;
        }
        Message message = new Message(dto.getId());
        message.setAuthor(UserConverter.convert(dto.getAuthor()));
        message.setRecipient(UserConverter.convert(dto.getRecipient()));
        message.setCommunity(CommunityConverter.convert(dto.getCommunity()));
        message.setTitle(dto.getTitle());
        message.setContent(dto.getContent());
        message.setChilds(new ArrayList<>(CommentConverter.convertToDoList(dto.getChilds())));
        //no need to set creation or updating timestamp --> automatically set on DB-interaction
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
